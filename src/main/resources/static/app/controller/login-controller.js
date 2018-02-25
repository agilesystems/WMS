/* global app, server, $localStorage, $scope, $routeProviderReference, $route */

app.controller('login-controller', function ($http, $scope,$rootScope, AuthService, $state) {

    $scope.test = function () {

        console.log('test test');
    }

    // method for login
    $scope.login = function () {
        // requesting the token by usename and passoword
        $http({
            url: 'authenticate',
            method: "POST",
            params: {
                username: $scope.username,
                password: $scope.password
            }
        }).then(function (res) {
            $scope.password = null;
            // checking if the token is available in the response
            if (res.data.token) {
                $scope.message = '';
                // setting the Authorization Bearer token with JWT token
                $http.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
                AuthService.user = res.data.user;
                $rootScope.currentUser = AuthService.user;

                // configure the routes based on the user menus
//                AuthService.user.menus.forEach(function (m) {
//                    addState(m.title, m.url);
//                });

                //go to home page
                $state.go('home');
//                $window.location.href = '/index.html';
//                 $location.path('/index.html');


            } else {
                // if the token is not present in the response then the
                // authentication was not successful. Setting the error message.
                $scope.message = 'Authetication Failed !';
                console.log('Authetication Failed !');
            }
        }, function (error) {
            console.log(error);
        });
    };


});