
var app = angular.module('wms', ['ngRoute']);
var $routeProviderReference;  
var server = "http://localhost:8080";
// Creating the Angular Service for storing logged user details
app.service('AuthService', function () {
    return {
        user: null
    };
});

app.config(function ($routeProvider) {
    $routeProviderReference = $routeProvider;
});