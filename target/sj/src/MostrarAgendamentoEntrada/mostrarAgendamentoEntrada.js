'use strict';

angular.module('mutrack')
  .controller('AgEntradaCtrl', function($scope, ngNotify, RestSrv, SERVICE_PATH) {


    $scope.age = {};
    $scope.ages = [];

    $scope.showAddEditAgenda = false;

    // Show the form used to edit or add packagees.
    $scope.show = function() {
      $scope.showAddEditAgenda= true;
    };

    // Hide the form used to edit or add packagees.
    $scope.hide = function() {
      $scope.showAddEditAgenda = false;
      $scope.age = {};
    };

    // Chama serviço separado do agendamento para editar os dados
    //var mostrarAgendamentoUrl = SERVICE_PATH.PRIVATE_PATH + '/mostrarAgendamento/mostrarodernado';
      //var mostrarUpdateAgendamentoUrl = SERVICE_PATH.PRIVATE_PATH + '/mostrarAgendamento/update';


    // Chama serviço separado para ordenar os dados
     var mostrarAgendamentoUrl = SERVICE_PATH.PRIVATE_PATH + '/agendamento/mostrar';


    // Chama serviço separado do agendamento para editar os dados
    var mostrarUpdateAgendamentoUrl = SERVICE_PATH.PRIVATE_PATH + '/agendamento/update';


    // Chama metodo de busca
    var bucarAgendamentoUrl = SERVICE_PATH.PRIVATE_PATH + '/entradaSaida';

    $scope.editAgendamento = function(age) {
      $scope.age= angular.copy(age);
      $scope.show();
    };

    $scope.deleteAgendamento = function(age) {
      RestSrv.delete(mostrarAgendamentoUrl,age, function() {
        $scope.ages.splice($scope.ages.indexOf(age), 1);
        ngNotify.set('MostrarAgendamento \'' + age.empresa + '\' deleted.', 'success');
      });
    };

    //ag.hora = ag.horaObj.getHours() + ':' + ag.horaObj.getMinutes() ;


    // Busca a Entrada pela descricao
/*
    $scope.buscaEntrada = function(age) {

        RestSrv.findPesqFuncionario(bucarAgendamentoUrl, age, function(status,data) {
            $scope.ages = data.data;

              ngNotify.set('Empresa: \'' + age.empresa + '\' Encontrado.', 'success');


          });

    };
*/



    $scope.saveAgendamento = function(age) {


      if (age.id) {
        RestSrv.edit(mostrarUpdateAgendamentoUrl, age, function() {
          for (var i = 0; i < $scope.ages.length; i++) {
            if ($scope.ages[i].id === age.id) {
              $scope.ages[i] = age;
            }
          }

          $scope.hide();
          ngNotify.set('MostrarAgendamento \'' + age.empresa + '\' updated.', 'Modular');
        });
      } else {
        RestSrv.add(mostrarAgendamentoUrl, age, function(newMostrarAgendamentoEntrada) {
          $scope.ages.push(newMostrarAgendamentoEntrada);
          $scope.hide();
          ngNotify.set('MostrarAgendamento\'' + age.empresa + '\' added.', 'Modular');
        });
      }
    };



        RestSrv.find(mostrarAgendamentoUrl,function(data) {
          $scope.ages = data;
          ngNotify.set(' Entrada ages with success.', 'success');
        });







});
