
/* global server */

app.controller('user-controller', init);

function init($scope, $http, DataServiceApi) {

    DataServiceApi.GetData(server + 'menu/all').then(function (response) {
        $scope.menus = response.data;
    });
$scope.user={menuCollection:[]};
    $scope.addUser = function () {

//        data.firstname=  $scope.fname;
//        data.lastname = $scope.lname;
//        data.address = $scope.address;
//        data.username = $scope.username;
//        data.password = $scope.password;
//        data.phone = $scope.phone;
//        
//        var options = $('#lstBox1 option').each(function()
//{
//       
//});


        console.log($scope.user.menuCollection);
        DataServiceApi.PostData($scope.user, server + 'user/add');
    };
//    $scope.$watch('selected', function (nowSelected) {
//        // reset to nothing, could use `splice` to preserve non-angular references
//        $scope.user.menuCollection = [];
//
//        if (!nowSelected) {
//            // sometimes selected is null or undefined
//            return;
//        }
//
//        // here's the magic
//        angular.forEach(nowSelected, function (val) {
//            $scope.user.menuCollection.push(val);
//        });
//    });

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
        var selectedOpts = $('#lstBox1 option:selected.value');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");

            e.preventDefault();
        }
        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        
        var menu={ id:0};
        menu.id=selectedOpts.option.value;
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



    