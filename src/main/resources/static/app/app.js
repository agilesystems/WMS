
var app = angular.module('wms', ['ui.router', 'ngMessages']);
var $routeProviderReference;
var server='http://localhost:8080/';
// Creating the Angular Service for storing logged user details
//app.service('AuthService', function () {
//    return {
//        user: null
//    };
//});

app.config(function ($stateProvider, $urlRouterProvider, $qProvider) {
    
    $qProvider.errorOnUnhandledRejections(false);
    $stateProviderRefrence = $stateProvider;
    $urlRouterProvider.otherwise('/');    
    addState('login', 'login.html');
    addState('home', 'dashboard.html');
    
    
    $stateProviderRefrence.state('nav', {
        abstract: true,
        url: '',
        views: {
            'nav@': {
                templateUrl: '/views/nav.html'
            }
        }
    });

});

var addState = function (name, url) {
     console.log(name+' '+ url);
    if (name === null || name === '' || url === null || url === '') {
        return;
    }
   
    $stateProviderRefrence.state(name, {
        url: '/' + name,
        views: {
            'content@': {
                templateUrl: 'views/' + url         
            }
        }
    });
};

app.run(function ( $rootScope, $state, $http) {

    // For implementing the authentication with ui-router we need to listen the
    // state change. For every state change the ui-router module will broadcast
    // the '$stateChangeStart'.
    // 
    $http.get(server+'menu/all').then(function (res) {
        if (res.data) {
            res.data.forEach(function (m) {
                addState('state_'+m.id, m.url);
                
            });
        }
    });

    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        console.log("state changed!!!!!!");
        // checking the user is logged in or not
        if (!$rootScope.currentUser) {
            // To avoiding the infinite looping of state change we have to add a
            // if condition.
            if (toState.name !== 'login' && toState.name !== 'register') {
                event.preventDefault();
                $state.go('login');
            }
        }

//                else {
//			// checking the user is authorized to view the states
//			if (toState.name && toState.name!='access-denied' && toState.name!='home' ) {
//				var hasAccess = false;
//				for (var i = 0; i < AuthService.user.menus.length; i++) {
//					var menu = AuthService.user.menus[i];
//					if (toState.name == menu.form.name) {
//						hasAccess = true;
//						break;
//					}
//				}
//				if (!hasAccess) {
//					event.preventDefault();
//					$state.go('access-denied');
//				}
//
//			}
//		}
    });
});