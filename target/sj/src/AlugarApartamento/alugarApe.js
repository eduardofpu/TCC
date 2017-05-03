'use strict';

angular.module('mutrack')
  .controller('AlugarApeCtrl', function($scope, ngNotify, RestSrv, SERVICE_PATH) {


    $scope.alugarApe = {};
    $scope.alugarApes = [];
    $scope.showAddAlugarApe = false;

    // Show the form used to edit or add packagees.
    $scope.show = function() {
      $scope.showAddAlugarApe= true;
    };

    // Hide the form used to edit or add packagees.
    $scope.hide = function() {
      $scope.showAddAlugarApe = false;
      $scope.alugarApe = {};
    };

    // Manage CRUD operations.
    var alugarApeUrl = SERVICE_PATH.PRIVATE_PATH + '/alugarApartamento';

    $scope.editAlugarApe = function(alugarApe) {
      $scope.alugarApe = angular.copy(alugarApe);
      $scope.show();
    };



$scope.deleteAlugarApe = function(alugarApe) {

$('#confirmDelete').find('.modal-footer #confirm').on('click', function(){

      RestSrv.delete(alugarApeUrl,alugarApe, function() {
        $scope.alugarApes.splice($scope.alugarApes.indexOf(alugarApe), 1);
        ngNotify.set('AlugarApartamento \'' + alugarApe.valor + '\' deleted.', 'success');
      });


      $(this).data('form').submit();

  });


};

    $scope.saveAlugarApe = function(alugarApe) {
      if (alugarApe.id) {
        RestSrv.edit(alugarApeUrl, alugarApe, function() {
          for (var i = 0; i < $scope.alugarApes.length; i++) {
            if ($scope.alugarApes[i].id === alugarApe.id) {
              $scope.alugarApes[i] = alugarApe;
            }
          }

          $scope.hide();
          ngNotify.set('AlugarApartamento \'' + alugarApe.valor + '\' updated.', 'success');
        });
      } else {
        RestSrv.add(alugarApeUrl, alugarApe, function(newAlugarApe) {
          $scope.alugarApes.push(newAlugarApe);
          $scope.hide();
          ngNotify.set('AlugarApartamento\'' + alugarApe.valor + '\' added.', 'success');
        });
      }
    };

    // Request all data (packagees).
    RestSrv.find(alugarApeUrl, function(data) {
      $scope.alugarApes = data;
      ngNotify.set('success.', 'modular');
    });

  });
