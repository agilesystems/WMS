/* global app, server, $localStorage, $scope */

app.controller('login-controller', function ($http, $scope,$rootScope,$stateProvider) {

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
                $rootScope.user = res.data.user;
                $rootScope.user.menus.forEach(function (m) {
                    
                    app.config(function ($stateProvider, $urlRouterProvider) {

                        $stateProvider.state(m.title, {
                            parent: 'nav',
                            url: '/'+m.url,
                            views: {
                                'content@': {
                                    templateUrl: 'views/'+m.url
                                }
                            }
                        });
                    });


              });


                console.log('user>>>>' + $scope.user);
//                        $rootScope.$broadcast('LoginSuccessful');


                // going to the home page
//                        $state.go('home');
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