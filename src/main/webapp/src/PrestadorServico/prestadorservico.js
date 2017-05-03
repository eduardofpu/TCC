
angular.module('mutrack')
  .controller('prestadorservicoCtrl', function($scope,$http, ngNotify, RestSrv, SERVICE_PATH) {

    $scope.p = {};
    $scope.ps = [];
    $scope.permissions = [];
    $scope.showAddEditUser = false;


            ngNotify.config({
              theme: 'pastel'
            });



    $scope.show = function() {
      $scope.showAddEditUser = true;
    };


    // Hide the form used to edit or add users.
    $scope.hide = function() {
      $scope.showAddEditUser = false;
      $scope.p = {};
    };

    // Manage CRUD operations.
var fileUrl = SERVICE_PATH.PRIVATE_PATH + '/prestadorservico';


$scope.buscarCep = function(cep){

	              $.getJSON('https://viacep.com.br/ws/'+cep+'/json/').success(function(data){
                    $scope.p = data;
                   	$scope.resCep= false;
                  }).error(function(){
              			$scope.resCep= true;
              		});

      };



    $scope.editFile = function(p) {
      $scope.p = angular.copy(p);
      $scope.show();
    };


$scope.deleteFile = function(p) {

$('#confirmDelete').find('.modal-footer #confirm').on('click', function(){

 RestSrv.delete(fileUrl, p, function() {
        $scope.ps.splice($scope.ps.indexOf(p), 1);
        ngNotify.set('Prestador\'' + p.prestador + '\' deleted.', 'success');
      });




      $(this).data('form').submit();

  });


};

    $scope.updateLinkImageEdit = function(file){
        var arq = file.split(',');
        $scope.fileuploadEdit.mimeType = arq[0];
        $scope.fileuploadEdit.file = arq[1];
    };




    $scope.updateLinkImage = function(file){
        var arq = file.split(',');
        $scope.p.mimeType = arq[0];
        $scope.p.file = arq[1];
    };


    $scope.saveFile = function(p) {



      if (p.id) {
        RestSrv.edit(fileUrl, p, function() {


          for (var i = 0; i < $scope.ps.length; i++) {
            if ($scope.ps[i].id === p.id) { //=== verifica id e o objeto
              $scope.ps[i] = p;
            }
          }


          $scope.hide();// para esconder o forme
          ngNotify.set('Prestador \'' + p.prestador + '\' updated.', 'success');


  });


      } else {


        RestSrv.add(fileUrl, p, function(newPrestadorServico) {

          $scope.ps.push(newPrestadorServico);
          $scope.hide();
          ngNotify.set('Prestador \'' + p.prestador + '\' added.', 'success');

        });

      }
    };






    RestSrv.find(fileUrl, function(data) {


      $scope.ps = data;

    //     console.log($scope.fileuploads);

       $scope.fileuploadEdit =  $scope.ps[0];
      ngNotify.set('Loaded ps with success.', 'success');
    });



           var permissionUrl =  SERVICE_PATH.PRIVATE_PATH + '/permission';

             RestSrv.find(permissionUrl, function(data) {


                 $scope.permissions = data;


  });



  });
