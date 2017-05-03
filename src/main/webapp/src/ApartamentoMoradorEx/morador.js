'use strict';

angular.module('mutrack')
  .controller('ExMrCtrl', function($scope, ngNotify, RestSrv, SERVICE_PATH , MensagensRedirect) {




    $scope.m = {};
    $scope.ms = [];

    $scope.permissao = {};
    $scope.showAddEditUser = false;


    ngNotify.config({
      theme: 'pastel'
    });



    // Show the form used to edit or add users.
    $scope.show = function() {
      $scope.showAddEditUser = true;
      $scope.fields = [];
      $scope.showAddEditUser = true;
    };

    // Hide the form used to edit or add users.
    $scope.hide = function() {
      $scope.showAddEditUser = false;
      $scope.m = {};
    };

    // Manage CRUD operations.
      var mUrl =  SERVICE_PATH.PRIVATE_PATH + '/moradorex';



    $scope.editMorador = function(m) {
      $scope.fields = [];
      $scope.statusError = {};
      $scope.m = angular.copy(m);
      $scope.show();
    };



  $scope.deleteMorador = function(m) {

  $('#confirmDelete').find('.modal-footer #confirm').on('click', function(){


        RestSrv.delete(mUrl, m, function() {
          $scope.ms.splice($scope.ms.indexOf(m), 1);
          ngNotify.set('Morador \'' + m.nome + '\' deleted.', 'success');
        });


        $(this).data('form').submit();

    });


  };




    $scope.saveMorador = function(m) {

      if (m.id ) {

        RestSrv.edit(mUrl, m, function() {



          for (var i = 0; i < $scope.ms.length; i++) {
            if ($scope.ms[i].id === m.id) { //=== verifica id e o objeto
              $scope.ms[i] = m;
            }
          }

          $scope.hide();// para esconder o forme
          ngNotify.set('Morador \'' + m.nome + '\' updated.', 'success');


        });


      } else {


        RestSrv.add(mUrl, m, function(newMorador) {

          $scope.ms.push(newMorador);
          $scope.hide();
          ngNotify.set('Morador \'' + m.nome + '\' added.', 'success');


        });
      }
    };


    RestSrv.find(mUrl,function(data) {
      $scope.ms = data;
      ngNotify.set('Loaded ms with success.', 'success');

      $scope.m = MensagensRedirect.getMensagem();
      ngNotify.set($scope.m, 'error');
      MensagensRedirect.setMensagem('');


    });


    // Request all data (permission and user).



   var permissionUrl =  SERVICE_PATH.PRIVATE_PATH + '/permission';

     RestSrv.find(permissionUrl, function(data) {


         $scope.permissions = data;


  }); // Fim


  });
