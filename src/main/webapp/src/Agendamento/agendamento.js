'use strict';

angular.module('mutrack')
  .controller('AgendaCtrl', function($scope, ngNotify, RestSrv, SERVICE_PATH) {


    $scope.ag = {};
    $scope.ags = [];
    $scope.showAddEditAgenda = false;




    // Show the form used to edit or add packagees.
    $scope.show = function() {
      $scope.showAddEditAgenda= true;
    };

    // Hide the form used to edit or add packagees.
    $scope.hide = function() {
      $scope.showAddEditAgenda = false;
      $scope.ag = {};
    };


    // Manage CRUD operations.
    var agendaUrl = SERVICE_PATH.PRIVATE_PATH + '/agendamento';

    $scope.editAgendamento = function(ag) {
      $scope.ag = angular.copy(ag);
      $scope.show();
    };


  $scope.deleteAgendamento = function(ag) {

  $('#confirmDelete').find('.modal-footer #confirm').on('click', function(){

        RestSrv.delete(agendaUrl,ag, function() {
          $scope.ags.splice($scope.ags.indexOf(ag), 1);
          ngNotify.set('Agendamento \'' + ag.empresa + '\' deleted.', 'success');
        });


        $(this).data('form').submit();

    });


  };
    //ag.hora = ag.horaObj.getHours() + ':' + ag.horaObj.getMinutes() ;

    $scope.saveAgendamento = function(ag) {


      if (ag.id) {
        RestSrv.edit(agendaUrl, ag, function() {
          for (var i = 0; i < $scope.ags.length; i++) {
            if ($scope.ags[i].id === ag.id) {
              $scope.ags[i] = ag;
            }
          }

          $scope.hide();
          ngNotify.set('Agendamento \'' + ag.empresa + '\' updated.', 'Modular');
        });
      } else {
        RestSrv.add(agendaUrl, ag, function(newEmpresa) {
          $scope.ags.push(newEmpresa);
          $scope.hide();
          ngNotify.set('Agendamento\'' + ag.empresa + '\' added.', 'Modular');
        });
      }


    };

    // Request all data (packagees).
    RestSrv.find(agendaUrl, function(data) {
        $scope.ags = data;

      ngNotify.set('success.', 'Modular');
    });







  });
