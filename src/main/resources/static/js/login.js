var loginApp = angular.module('loginApp',[]);

loginApp.controller('loginController',function ($scope, $http) {
  $scope.submit = function () {
    $http.get("http://localhost:8080/test/login?username="+$scope.username+"&password="+$scope.password)
    .then(function () {
      alert('Welcome: '+ $scope.username);
      document.location.replace('index.html');
    });
  }
});
