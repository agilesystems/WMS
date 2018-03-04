app
   .controller('account-controller', init);

function init($http, $scope, DataServiceApi) {

    DataServiceApi.GetData(server + 'accountype/findall').then(function (response) {
        $scope.accounts = response.data;
    });

    $scope.addAccount = function (isValid) {

        if (isValid) {

            DataServiceApi.PostData($scope.account, server + 'account/add');
        }
    };


}