angular.module('MainController', []).controller('MainController', function ($scope, service) {
	$scope.listaProdutos = [];
	$scope.produto = {};
	
    $scope.listar = () => {
        let fn = null;
        
        service.getCompras(function(resp){
        	$scope.listaProdutos = resp.data;
        	$scope.totalizadores();
        });
    }
    
    $scope.formatar = function(valor){
    	return (valor.toFixed(2));
    }
    
    $scope.totalizadores = function(){
    	let soma = 0;
    	let contador = 0;
    	
    	for (item in $scope.listaProdutos){
    		soma += $scope.listaProdutos[item].valorTotal;
    		contador++;
    	}
    	$scope.soma = $scope.formatar(soma);
    	$scope.contador = contador;
    }
    
    $scope.editar = function(id) {
    	service.getComprasId(id, function(resp) {
    		$scope.produto = resp.data
    	});
    }
    
    $scope.excluirProduto = function(id) {
    	service.excluirProduto(id, function(resp) {
    		$scope.listar();
    	});
    }
    
    $scope.salvar = function(){
    	service.addCompras($scope.produto,function(resp){
    		limpar();
        	$scope.listar();
    	});
    }
    
    $scope.calculaValorTotal = function() {
    	$scope.produto.valorTotal = $scope.produto.quantidade * $scope.produto.valorUnitario
    }
   
    $scope.onClickNovo = function () {
    	limpar();
    }
    
    function limpar() {
    	$scope.produto = {};
    }
    
    $scope.listar();
});