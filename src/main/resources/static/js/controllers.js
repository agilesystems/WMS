/**
 * INSPINIA - Responsive Admin Theme
 *
 * Main controller.js file
 * Define controllers with data used in Inspinia theme
 *
 *
 * Functions (controllers)
 *  - MainCtrl
 *  - translateCtrl
 *
 *
 */



/**
 * MainCtrl - controller
 * Contains several global data used in different view
 *
 */


function MainCtrl($rootScope,$state) {
    if (!$rootScope.currentUser) {
        console.log("go to login");
       $state.go('logins');
    }
}
;

/**
 * translateCtrl - Controller for translate
 */
function translateCtrl($translate, $scope) {
    $scope.changeLanguage = function (langKey) {
        $translate.use(langKey);
        $scope.language = langKey;
    };
};

function loginCtrl($http,$scope,$rootScope,$state){
    
    this.login = function () {
        
        console.log('llogin');
        // requesting the token by usename and passoword
        $http({
            url: server+'authenticate',
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
               // AuthService.user = res.data.user;
                $rootScope.currentUser = res.data.user;

                //go to home page
                $state.go('dashboards.dashboard_1');


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
};

function accountCtrl($http, $scope, DataServiceApi) {

    DataServiceApi.GetData(server + 'accountype/findall').then(function (response) {
        $scope.accounts = response.data;
    });

    $scope.addAccount = function (isValid) {

        if (isValid) {

            DataServiceApi.PostData($scope.account, server + 'account/add');
        }
    };


};

function navbarCtrl($scope) {

    $scope.parentMenuFilter = function (item) {
        return item.menuCollection.length > 0 ;
    };
    $scope.subMenuFilter = function (item) {

        return  item.url !== null;
    };

};

function userCtrl($scope, $http, DataServiceApi) {

    DataServiceApi.GetData(server + 'menu/all').then(function (response) {
        $scope.menus = response.data;
    });
    $scope.user={menuCollection:[]};
    $scope.addUser = function () {

        DataServiceApi.PostData($scope.user, server + 'user/add');
    };

    $scope.addAllRight = function () {

        var selectedOpts = $('#lstBox1 option');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
   
        e.preventDefault();
    };
            
    $scope.addOneRigth = function () {
        var selectedOpts = $('#lstBox1 option:selected');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");

            e.preventDefault();
        }
        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        
        var menu={ id:0};
        menu.id=selectedOpts[0].value;
        $scope.user.menuCollection.push(menu);
        e.preventDefault();
    };
    $scope.addOneLeft = function () {
        var selectedOpts = $('#lstBox2 option:selected');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        $('#lstBox1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    };
    $scope.addAllLeft = function () {
        var selectedOpts = $('#lstBox2 option');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        $('#lstBox1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    };

}


/**
 *
 * Pass all functions into module
 */
app.controller('MainCtrl', MainCtrl)
        .controller('loginCtrl', loginCtrl)
        .controller('translateCtrl', translateCtrl)
        .controller('accountCtrl', accountCtrl)
        .controller('navbarCtrl', navbarCtrl)
        .controller('userCtrl', userCtrl);


