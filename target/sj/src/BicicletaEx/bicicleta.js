'use strict';

angular.module('mutrack')
  .controller('ExbicicletaCtrl', function($scope, ngNotify, RestSrv, SERVICE_PATH) {

    $scope.b = {};
    $scope.bs = [];

    $scope.permissao = {};
    $scope.showAddEditUser = false;


    ngNotify.config({
      theme: 'pastel'
    });



    // Show the form used to edit or add users.
    $scope.show = function() {
      $scope.showAddEditUser = true;
    };

    // Hide the form used to edit or add users.
    $scope.hide = function() {
      $scope.showAddEditUser = false;
      $scope.v = {};
    };

    // Manage CRUD operations.
    var bUrl =  SERVICE_PATH.PRIVATE_PATH + '/bicicletaex';




    $scope.editBicicleta = function(b) {
      $scope.b = angular.copy(b);
      $scope.show();
    };



$scope.deleteBicicleta = function(b) {

$('#confirmDelete').find('.modal-footer #confirm').on('click', function(){



      RestSrv.delete(bUrl, b, function() {
        $scope.bs.splice($scope.bs.indexOf(b), 1);
        ngNotify.set('Bicicleta \'' + b.modelo + '\' deleted.', 'success');
      });


      $(this).data('form').submit();

  });


};


    $scope.saveBicicleta = function(b) {
      if (b.id ) {
        RestSrv.edit(bUrl,b, function() {
          for (var i = 0; i < $scope.bs.length; i++) {
            if ($scope.bs[i].id === b.id) { //=== verifica id e o objeto
              $scope.bs[i] = b;
            }
          }
          $scope.hide();// para esconder o forme
          ngNotify.set('Bicicleta \'' + b.modelo+ '\' updated.', 'success');
        });
      } else {
        RestSrv.add(bUrl, b, function(newBicicleta) {
          $scope.bs.push(newBicicleta);
          $scope.hide();
          ngNotify.set('Bicicleta \'' + b.modelo+ '\' added.', 'success');
        });
      }
    };


    RestSrv.find(bUrl,function(data) {
    $scope.bs = data;
      ngNotify.set('Loaded bs with success.', 'success');
    });


    // Request all data (permission and user).



   var permissionUrl =  SERVICE_PATH.PRIVATE_PATH + '/permission';

     RestSrv.find(permissionUrl, function(data) {


         $scope.permissions = data;


  }); // Fim










  });
