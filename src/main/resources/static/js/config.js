/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */

var addState = function (name, url) {
    console.log(name + ' ' + url);
    if (name === null || name === '' || url === null || url === '') {
        return;
    }
    $stateProviderRefrence.state(name, {
        url: '/' + name,
        templateUrl: 'views/' + url,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        files: ['css/plugins/iCheck/custom.css', 'js/plugins/iCheck/icheck.min.js']
                    }
                ]);
            }
        }
    }
    );
};

function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, KeepaliveProvider) {

    // Configure Idle settings
    IdleProvider.idle(1); // in seconds
    IdleProvider.timeout(120); // in seconds

    $urlRouterProvider.otherwise("/dashboards/dashboard_1");
    $stateProviderRefrence = $stateProvider;

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: true
    });

    $stateProvider

            .state('dashboards', {
                abstract: true,
                url: "/dashboards",
                templateUrl: "views/common/content.html"
            })
            .state('dashboards.dashboard_1', {
                url: "/dashboard_1",
                templateUrl: "views/dashboard_1.html",
                resolve: {
                    loadPlugin: function ($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            {

                                serie: true,
                                name: 'angular-flot',
                                files: ['js/plugins/flot/jquery.flot.js', 'js/plugins/flot/jquery.flot.time.js', 'js/plugins/flot/jquery.flot.tooltip.min.js', 'js/plugins/flot/jquery.flot.spline.js', 'js/plugins/flot/jquery.flot.resize.js', 'js/plugins/flot/jquery.flot.pie.js', 'js/plugins/flot/curvedLines.js', 'js/plugins/flot/angular-flot.js', ]
                            },
                            {
                                name: 'angles',
                                files: ['js/plugins/chartJs/angles.js', 'js/plugins/chartJs/Chart.min.js']
                            },
                            {
                                name: 'angular-peity',
                                files: ['js/plugins/peity/jquery.peity.min.js', 'js/plugins/peity/angular-peity.js']
                            }
                        ]);
                    }
                }
            }).state('logins', {
        url: "/logins",
        templateUrl: "views/login.html",
        data: {pageTitle: 'Login', specialClass: 'gray-bg'}
    }).state('forms', {
        abstract: true,
        url: "/forms",
        templateUrl: "views/common/content.html"
    }).state('invoice', {
        abstract: true,
        url: "",
        templateUrl: "views/common/content.html"
        }).state('invoice.type', {
            url: "/invoice/:type",
            templateUrl: "views/invoice/add-invoice.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/iCheck/custom.css', 'js/plugins/iCheck/icheck.min.js']
                        }
                    ]);
                }
            }
    });
}

app
        .config(config)
        .run(function ($rootScope, $state) {
            $rootScope.$state = $state;

            $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                console.log("State Changed >>>>> event:" + event + " | from:" + fromState.url + "  | to:" + toState.url);

                if (toState.name === 'logins') {
                    return;
                }
                // checking the user is logged in or not
                if (!$rootScope.currentUser) {
                    // To avoiding the infinite looping of state change we have to add a
                    // if condition.

                    event.preventDefault();

                    $state.go('logins');

                }
            }
            );
//            $http.get(server + 'menu/all').then(function (res) {
//                if (res.data) {
//                    res.data.forEach(function (m) {
//                        addState('forms.form_' + m.id, m.url);
//
//                    });
//                }
//            });
        });
