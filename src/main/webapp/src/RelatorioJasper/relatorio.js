'use strict';

angular.module('mutrack')
  .controller('relatorioCtrl', function($scope,$http,SERVICE_PATH) {
	  
	  if (!window.location.origin) {
		    window.location.origin = window.location.protocol + '//' + window.location.hostname +(window.location.port ? ':' +
		    window.location.port: '');
		    console.log(window.location.origin);
		  }

		var Url = window.location.origin + '/api/private/relatorio';

  //var Url = SERVICE_PATH.PRIVATE_PATH + '/relatorio';

    $scope.geraRelatorio = function() {
      $http.get(Url,{responseType: 'arraybuffer'})
       .success(function (data) {
           var file = new Blob([data], {type: 'application/pdf'});
           var fileURL = URL.createObjectURL(file);
           window.open(fileURL);
         });
    };




});
