angular
.module('wms')
.factory('DataServiceApi',dataFromRest)

function dataFromRest($http) {
    
   var vm = {};

   vm.PostData = PostData;
   vm.GetData = GetData;

    return vm;

    function PostData(objectData,url) {
        $http({
            method: "POST",
            url: url,
            data: objectData,
            headers: "content-Type : application/json"
        }).then(function (response) {

            alert('Done');
        });
    }

    function GetData(url) {
       return $http({
            method: "GET",
            url: url,
            headers:{},
        }).then(function (response) {
            return response;
            Alert('Success');
        })
    }
}