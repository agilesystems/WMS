/* global app, server, $localStorage, $scope, $routeProviderReference, $route */

app.controller('navbar-controller', function ($http, $scope, $rootScope, $state) {

$scope.parentMenuFilter = function (item) { 
    console.log(item);
    return item.userMenuCollection.length>0 || item.url!==null; 
};
});