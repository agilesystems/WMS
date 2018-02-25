/* global app, server, $localStorage, $scope, $routeProviderReference, $route */

app.controller('home-controller', function ($http, $scope, AuthService, $state, $location, $window) {

    console.log('print user afterlogin'+AuthService.user);
    if(!AuthService.user){
        $state.go("login");
    }

});