/* global app, server, $localStorage, $scope, $routeProviderReference, $route */

app.controller('navbar-controller', function ($scope) {

    $scope.parentMenuFilter = function (item) {
        return item.menuCollection.length > 0 ;
    };
    $scope.subMenuFilter = function (item) {

        return  item.url !== null;
    };

});