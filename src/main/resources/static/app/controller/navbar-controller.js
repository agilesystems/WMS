/* global app, server, $localStorage, $scope, $routeProviderReference, $route */

app.controller('navbar-controller', function () {

$scope.parentMenuFilter = function (item) { 
    console.log(item);
    return item.menuCollection.length>0 || item.url!==null; 
};
});