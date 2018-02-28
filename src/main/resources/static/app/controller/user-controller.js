
/* global server */

app.controller('user-controller', init);

function init($scope, $http, DataServiceApi) {

    DataServiceApi.GetData(server + 'menu/all').then(function (response) {
        $scope.menus = response.data;
    });

    $scope.addUser = function () {
        var data = {};
        data.firstname = $scope.fname;
        data.lastname = $scope.lname;
        data.address = $scope.address;
        data.username = $scope.username;
        data.password = $scope.password;
        data.phone = $scope.phone;
        data.userMenuCollection = $('#lstBox1 option');
//      console.log(data.userMenuCollection);
        DataServiceApi.PostData(data, server + 'user/add');
    };
    
    $scope.addAllRight= function(){
        
        var selectedOpts = $('#lstBox1 option');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    };
    
    $scope.addOneRigth=function(){
           var selectedOpts = $('#lstBox1 option:selected');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    };
    $scope.addOneLeft=function(){
         var selectedOpts = $('#lstBox2 option:selected');
        if (selectedOpts.length === 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }
        $('#lstBox1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
    };
    $scope.addAllLeft=function(){
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



    