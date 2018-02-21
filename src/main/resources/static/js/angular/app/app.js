
var app = angular.module('wms', ['ui.router']);
var server = "http://localhost:8080";
// Creating the Angular Service for storing logged user details
app.service('AuthService', function () {
    return {
        user: null
    };
});

app.config(function ($stateProvider, $urlRouterProvider) {
});