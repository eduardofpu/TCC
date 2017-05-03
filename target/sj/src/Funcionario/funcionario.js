
angular.module('mutrack')
  .controller('funcionarioCtrl', function($scope,$http, ngNotify, RestSrv, SERVICE_PATH) {

    $scope.f = {};
    $scope.fs = [];
    $scope.permissions = [];
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
      $scope.f = {};
    };


    // Manage CRUD operations.
    var fileUrl = SERVICE_PATH.PRIVATE_PATH + '/funcionario';
    var Url  = 'https://viacep.com.br/ws/';

    


    $scope.buscarCep = function(cep){

                $.getJSON(Url+cep+'/json/').success(function(data){
                  //   $.getJSON('https://viacep.com.br/ws/'+cep+'/json/').success(function(data){
                       $scope.f = data;
                       $scope.resCep= false;
                     }).error(function(){
                       $scope.resCep= true;
                     });

         };







    $scope.editFile = function(f) {
      $scope.f = angular.copy(f);
      $scope.show();
    };

$scope.deleteFile = function(f) {

$('#confirmDelete').find('.modal-footer #confirm').on('click', function(){


      RestSrv.delete(fileUrl, f, function() {
        $scope.fs.splice($scope.fs.indexOf(f), 1);
        ngNotify.set('F \'' + f.name + '\' deleted.', 'success');
      });




      $(this).data('form').submit();

  });


};



    $scope.updateLinkImageEdit = function(f){
        var arq = f.split(',');
        $scope.fileuploadEdit.mimeType = arq[0];
        $scope.fileuploadEdit.file = arq[1];
    };




    $scope.updateLinkImage = function(f){
        var arq = f.split(',');
        $scope.f.mimeType = arq[0];
        $scope.f.file = arq[1];
    };






    $scope.saveFile = function(f) {
      if (f.id) {
        RestSrv.edit(fileUrl, f, function() {


          for (var i = 0; i < $scope.fs.length; i++) {
            if ($scope.fs[i].id === f.id) { //=== verifica id e o objeto
              $scope.fs[i] = f;
            }

          }

          $scope.hide();// para esconder o forme
          ngNotify.set('F\'' + f.cpf + '\' updated.', 'success');

        });


      } else {
        RestSrv.add(fileUrl, f, function(newFuncionario) {

          $scope.fs.push(newFuncionario);
          $scope.statusError = 'success';
          $scope.message = data.atributeMessage.MENSAGEM;

          $scope.hide();
          ngNotify.set('F \'' + f.cpf + '\' added.', 'success');


      });
    }
  };






    // Request all data (formularios).
    RestSrv.find(fileUrl, function(data) {
      $scope.fs = data;

        // console.log($scope.fileuploads);

       $scope.fileuploadEdit =  $scope.fs[0];
      ngNotify.set('Loaded fs with success.', 'success');
    });






       var permissionUrl =  SERVICE_PATH.PRIVATE_PATH + '/permission';

         RestSrv.find(permissionUrl, function(data) {


             $scope.permissions = data;


      });





  });
