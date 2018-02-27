/* global app, server, $localStorage, $scope, $routeProviderReference, $route */

app.controller('index-controller', function ($http, $scope, $rootScope, $state) {
    $rootScope.currentUser = null;

    if (!$rootScope.currentUser) {
        $state.go("login");
    }
    ;
//    $scope.parentMenuFilter = function (item) {
//        console.log(item);
//        return item.MenuCollection.length > 0 || item.url !== null;
//    };
});