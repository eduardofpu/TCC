
angular.module('mutrack')
  .controller('EditarFuncionarioCtrl', function($scope,$http, ngNotify, RestSrv, SERVICE_PATH) {

    $scope.fd = {};
    $scope.fds = [];
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
      $scope.fd = {};
    };


    // Manage CRUD operations.
    var fileUrl = SERVICE_PATH.PRIVATE_PATH + '/funcionario';
    var Url  = 'https://viacep.com.br/ws/';





    $scope.buscarCep = function(cep){

                $.getJSON(Url+cep+'/json/').success(function(data){
                  //   $.getJSON('https://viacep.com.br/ws/'+cep+'/json/').success(function(data){
                       $scope.fd = data;
                       $scope.resCep= false;
                     }).error(function(){
                       $scope.resCep= true;
                     });

         };






    $scope.editFile = function(fd) {
      $scope.fd = angular.copy(fd);
      $scope.show();
    };

$scope.deleteFile = function(fd) {

$('#confirmDelete').find('.modal-footer #confirm').on('click', function(){


      RestSrv.delete(fileUrl, fd, function() {
        $scope.fds.splice($scope.fds.indexOf(f), 1);
        ngNotify.set('F \'' + fd.name + '\' deleted.', 'success');
      });




      $(this).data('form').submit();

  });


};



    $scope.updateLinkImageEdit = function(fd){
        var arq = fd.split(',');
        $scope.fileuploadEdit.mimeType = arq[0];
        $scope.fileuploadEdit.file = arq[1];
    };




    $scope.updateLinkImage = function(fd){
        var arq = fd.split(',');
        $scope.fd.mimeType = arq[0];
        $scope.fd.file = arq[1];
    };






    $scope.saveFile = function(fd) {
      if (fd.id) {
        RestSrv.edit(fileUrl, fd, function() {


          for (var i = 0; i < $scope.fds.length; i++) {
            if ($scope.fds[i].id === fd.id) { //=== verifica id e o objeto
              $scope.fds[i] = f;
            }

          }

          $scope.hide();// para esconder o forme
          ngNotify.set('F\'' + fd.cpf + '\' updated.', 'success');

        });


      } else {
        RestSrv.add(fileUrl, fd, function(newFuncionario) {

          $scope.fds.push(newFuncionario);


          $scope.hide();
          ngNotify.set('F \'' + fd.cpf + '\' added.', 'success');


      });
    }
  };






    // Request all data (formularios).
    RestSrv.find(fileUrl, function(data) {
      $scope.fds = data;

        // console.log($scope.fileuploads);

       $scope.fileuploadEdit =  $scope.fds[0];
      ngNotify.set('Loaded fds with success.', 'success');
    });






       var permissionUrl =  SERVICE_PATH.PRIVATE_PATH + '/permission';

         RestSrv.find(permissionUrl, function(data) {


             $scope.permissions = data;


      });





  });
