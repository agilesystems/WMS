var regApp = angular.module('regApp',[]);

regApp.controller('regController',function ($scope, $http) {
    $scope.insertData = function () {
      var data = {};
      data.firstname = $scope.fname;
      data.lastname = $scope.lname;
      data.address = $scope.address;
      data.username = $scope.username;
      data.password = $scope.password;
      data.phone = $scope.phone;

      var postUser = $http({
        method: "POST",
        url: "http://localhost:8080/test/add",
        data:data,
        headers: "content-Type : application/json"
      });

      postUser.then(function (data,Status) {

        alert('Done');
      });
    }
});
