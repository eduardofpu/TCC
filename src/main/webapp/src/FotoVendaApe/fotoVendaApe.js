'use strict';

angular.module('mutrack')
  .controller('fotoVendaApeCtrl', function($scope, ngNotify, RestSrv, SERVICE_PATH) {




//   Tras os campos dos apartamentos cadastrados


        $scope.ape = {};
        $scope.apes = [];

        var agUrl =  SERVICE_PATH.PRIVATE_PATH + '/venderApartamento';


        RestSrv.find(agUrl,function(data) {
          $scope.apes = data;
          ngNotify.set('Loaded apes with success.', 'success');
        });






    $scope.ft = {};
    $scope.fts = [];

    $scope.permissao = {};
    $scope.showAddFotoApe = false;


    ngNotify.config({
      theme: 'pastel'
    });



    // Show the form used to edit or add users.
    $scope.show = function() {
      $scope.showAddFotoApe= true;
    };

    // Hide the form used to edit or add users.
    $scope.hide = function() {
      $scope.showAddFotoApe = false;
      $scope.ft = {};
    };

    // Manage CRUD operations.
    var fotoApeUrl =  SERVICE_PATH.PRIVATE_PATH + '/fotosVenderApe';




        $scope.updateLinkImage = function(file){
            var arq = file.split(',');
            $scope.ft.mimeType = arq[0];
            $scope.ft.file = arq[1];
        };




    $scope.editFotoVendaApe  = function(ft ) {
      $scope.ft  = angular.copy(ft );
      $scope.show();
    };



   $scope.deleteFotoVendaApe  = function(ft ) {

  $('#confirmDelete').find('.modal-footer #confirm').on('click', function(){


         RestSrv.delete(vendaApeUrl, ft , function() {
          $scope.fts.splice($scope.fts.indexOf(ft), 1);
          ngNotify.set('Fotos \'' + ft .comodo + '\' deleted.', 'success');
        });



        $(this).data('form').submit();

    });


  };



    $scope.saveFotoVendaApe  = function(ft ) {
      if (ft.id ) {
        RestSrv.edit(fotoApeUrl,ft , function() {
          for (var i = 0; i < $scope.fts.length; i++) {
            if ($scope.fts[i].id === ft.id) { //=== verifica id e o objeto
              $scope.fts[i] = ft;
            }
          }
          $scope.hide();// para esconder o forme
          ngNotify.set('Fotos \'' + ft.comodo+ '\' updated.', 'success');
        });
      } else {
        RestSrv.add(fotoApeUrl, ft , function(newFotoVendaApe) {
          $scope.fts.push(newFotoVendaApe);
          $scope.hide();
          ngNotify.set('Fotos \'' + ft.comodo+ '\' added.', 'success');
        });
      }
    };
    RestSrv.find(fotoApeUrl,function(data) {
    $scope.fts = data;
      ngNotify.set('Loaded fts with success.', 'success');
    });


    // Request all data (permission and user).



   var permissionUrl =  SERVICE_PATH.PRIVATE_PATH + '/permission';

     RestSrv.find(permissionUrl, function(data) {


         $scope.permissions = data;


  }); // Fim










  });
