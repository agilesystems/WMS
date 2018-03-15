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
};

function settingsCtrl($scope, storageService) {

    $scope.touched = function () {
      storageService.setStorage("tax1", $scope.tax1);
      storageService.setStorage("tax2", $scope.tax2);
      storageService.setStorage("contact", $scope.contact);
}
     $scope.tax1 = storageService.getStorage('tax1');
     $scope.tax2 = storageService.getStorage('tax2');
     $scope.contact = storageService.getStorage("contact");

};
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

    DataServiceApi.GetData(server + 'role/all').then(function (response) {
        $scope.roles = response.data;
    });

    $scope.addUser = function(isValid) {
      if (isValid) {
        DataServiceApi.PostData($scope.user, server + "user/add");
      }
    };

}

function invoiceCtrl($scope, $http, DEFAULT_INVOICE, DEFAULT_LOGO, LocalStorage, DataServiceApi) {
  // Set defaults
  $scope.currencySymbol = '$';
  $scope.logoRemoved = false;
  $scope.printMode   = false;

  (function init() {
    // Attempt to load invoice from local storage

    !function() {
      var localInvoice = LocalStorage.getInvoice();
      $scope.invoice = localInvoice ? localInvoice : DEFAULT_INVOICE;
    }();
    // Set logo to the one from local storage or use default
    !function() {
      var logo = LocalStorage.getLogo();
      $scope.logo = logo ? logo : DEFAULT_LOGO;
    }();

      


      // get items from API
      DataServiceApi.GetData(server+'item/all').then(function (response) {
          $scope.Items = response.data;
          $scope.nameAndBarcode = function(item, nameOrBarcode) {
            return nameOrBarcode ? item.barcode + " " + item.name : item.name;
          };
      });

      // get Accounts from API
      DataServiceApi.GetData(server+'account/customer/all').then(function (response) {
          $scope.accounts = response.data;
      });

      // get Stores from API
      DataServiceApi.GetData(server+'store/all').then(function (response) {
          $scope.stores = response.data;
      });

      // get Invoice Type from API
      DataServiceApi.GetData(server+'invoice-type/all').then(function (response) {
          $scope.invoTypes = response.data;
      });
      
      // get Payment Type from API
      DataServiceApi.GetData(server+'payment-type/all').then(function (response) {
          $scope.payments = response.data;
      });

      // get Discount Type from API
      DataServiceApi.GetData(server+'payment-type/all').then(function (response) {
          $scope.discountTypes = response.data;
      });

  })()

  // Save invoice (to invoice and invoiceItem)
  $scope.addInvoice = function () {
      // Save invoice
      DataServiceApi.PostData($scope.invoice, server + 'invoice/add');
      // Save invoice item
      DataServiceApi.PostData($scope.invoiceItem, server + "invoiceItem/add");
  }

  // Adds an item to the invoice's items
  $scope.addItem = function() {
    $scope.invoice.items.push({ qty:0, cost:0, description:"" });
  }

  // Toggle's the logo
  $scope.toggleLogo = function(element) {
    $scope.logoRemoved = !$scope.logoRemoved;
    LocalStorage.clearLogo();
  };

  // Triggers the logo chooser click event
  $scope.editLogo = function() {
    // angular.element('#imgInp').trigger('click');
    document.getElementById('imgInp').click();
  };

  $scope.printInfo = function() {
    window.print();
  };

  // Remotes an item from the invoice
  $scope.removeItem = function(item) {
    $scope.invoice.items.splice($scope.invoice.items.indexOf(item), 1);
  };

  // Calculates the sub total of the invoice
  $scope.invoiceSubTotal = function() {
    var total = 0.00;
    angular.forEach($scope.invoice.Items, function(item, key) {
      total += item.quantity * item.cost;
    }); 
    return total;
  };

  // Calculates the tax of the invoice
  $scope.calculateTax = function() {
    return (($scope.invoice.tax * $scope.invoiceSubTotal())/100);
  };

  // Calculates the grand total of the invoice
  $scope.calculateGrandTotal = function() {
    saveInvoice();
    return $scope.calculateTax() + $scope.invoiceSubTotal();
  };

  // Clears the local storage
  $scope.clearLocalStorage = function() {
    var confirmClear = confirm('Are you sure you would like to clear the invoice?');
    if(confirmClear) {
      LocalStorage.clear();
      setInvoice(DEFAULT_INVOICE);
    }
  };

  // Sets the current invoice to the given one
  var setInvoice = function(invoice) {
    $scope.invoice = invoice;
    saveInvoice();
  };

  // Reads a url
  var readUrl = function(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      reader.onload = function (e) {
        document.getElementById('company_logo').setAttribute('src', e.target.result);
        LocalStorage.setLogo(e.target.result);
      }
      reader.readAsDataURL(input.files[0]);
    }
  };

  // Saves the invoice in local storage
  var saveInvoice = function() {
    LocalStorage.setInvoice($scope.invoice);
  };

  // Runs on document.ready
  angular.element(document).ready(function () {
    // Set focus
    document.getElementById('invoice-number').focus();

    // Changes the logo whenever the input changes
    document.getElementById('imgInp').onchange = function() {
      readUrl(this);
    };
  });


};

/**
 *
 * Pass all functions into module
 */
app.controller('MainCtrl', MainCtrl)
        .controller('loginCtrl', loginCtrl)
        .controller('translateCtrl', translateCtrl)
        .controller('accountCtrl', accountCtrl)
        .controller('navbarCtrl', navbarCtrl)
        .controller('userCtrl', userCtrl)
        .controller('invoiceCtrl', invoiceCtrl)
        .controller('settingsCtrl', settingsCtrl);


