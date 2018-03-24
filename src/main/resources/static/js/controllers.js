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

function MainCtrl($rootScope, $state) {
  if (!$rootScope.currentUser) {
    console.log("go to login");
    $state.go("logins");
  }
}

function settingsCtrl($scope, $rootScope, storageService) {
  $scope.touched = function () {
    storageService.setStorage("tax1", $scope.tax1);
    storageService.setStorage("tax2", $scope.tax2);
    storageService.setStorage("contact", $scope.contact);
  };
  $scope.tax1 = storageService.getStorage("tax1");
  $scope.tax2 = storageService.getStorage("tax2");
  $scope.contact = storageService.getStorage("contact");
}
/**
 * translateCtrl - Controller for translate
 */
function translateCtrl($translate, $scope) {
  $scope.changeLanguage = function (langKey) {
    $translate.use(langKey);
    $scope.language = langKey;
  };
}

function loginCtrl($http, $scope, $rootScope, $state) {
  this.login = function () {
    console.log("llogin");
    // requesting the token by usename and passoword
    $http({
      url: server + "authenticate",
      method: "POST",
      params: {
        username: $scope.username,
        password: $scope.password
      }
    }).then(
      function (res) {
        $scope.password = null;
        // checking if the token is available in the response
        if (res.data.token) {
          $scope.message = "";
          // setting the Authorization Bearer token with JWT token
          $http.defaults.headers.common["Authorization"] =
            "Bearer " + res.data.token;
          // AuthService.user = res.data.user;
          $rootScope.currentUser = res.data.user;

          //go to home page
          $state.go("dashboards.dashboard_1");
        } else {
          // if the token is not present in the response then the
          // authentication was not successful. Setting the error message.
          $scope.message = "Authetication Failed !";
          console.log("Authetication Failed !");
        }
      },
      function (error) {
        console.log(error);
      }
    );
  };
}

function accountCtrl($http, $scope, DataServiceApi, validateForms) {
  DataServiceApi.GetData(server + "accountype/findall").then(function (
    response
  ) {
    $scope.accounts = response.data;
  });

  $scope.onKeyUpSelect = function () {
    $('input[name="inputItem"]').valid();
  }

  // Call function from validateService to validate account form
  $scope.accountFormValidate = validateForms.accountForm;

  // Add new account
  $scope.addAccount = function (form) {
    if (form.validate()) {
      DataServiceApi.PostData($scope.account, server + "account/add");
    }
  };
}

function navbarCtrl($scope) {
  $scope.parentMenuFilter = function (item) {
    return item.menuCollection.length > 0;
  };
  $scope.subMenuFilter = function (item) {
    return item.url !== null;
  };
}

function userCtrl($scope, $http, DataServiceApi) {
  DataServiceApi.GetData(server + "role/all").then(function (response) {
    $scope.roles = response.data;
  });

  $scope.addUser = function (isValid) {
    if (isValid) {
      DataServiceApi.PostData($scope.user, server + "user/add");
    }
  };
}

function invoiceCtrl(
  $scope,
  $http,
  DataServiceApi,
  storageService,
  validateForms,
  toaster,
  toastrService
) {


  (function init() {
    // Init values
    $scope.newField = {};
    $scope.invoiceItem = {};
    $scope.invoice = {};
    $scope.tax = {};
    $scope.editing = false;
    $scope.invoiceItems = [];

    // get items from API
    DataServiceApi.GetData(server + "item/all").then(function (response) {
      $scope.Items = response.data;
    });

    // get Accounts from API
    DataServiceApi.GetData(server + "account/customer/all").then(function (
      response
    ) {
      $scope.accounts = response.data;
    });

    // get Stores from API
    DataServiceApi.GetData(server + "store/all").then(function (response) {
      $scope.stores = response.data;
    });

    // get Invoice Type from API
    DataServiceApi.GetData(server + "invoice-type/all").then(function (
      response
    ) {
      $scope.invoTypes = response.data;
    });

    // get Payment Type from API
    DataServiceApi.GetData(server + "payment-type/all").then(function (
      response
    ) {
      $scope.payments = response.data;
    });

    // get Discount Type from API
    DataServiceApi.GetData(server + "payment-type/all").then(function (response) {
      $scope.discountTypes = response.data;
    });
  })();

  //Call function from validateService to validate invoice form
  $scope.validateInvoice = validateForms.invoiceForm;
  // Save invoice (invoice and invoiceItem)
  $scope.addInvoice = function (form) {
    
    if (form.validate()) {

    // Save invoice
    DataServiceApi.PostData($scope.invoice, server + "invoice/add").then(function (res) {
      if (res.status === 200 && res.data) {
        toastrService.success('Success', 'Invoice saved successfully');
        console.log(res.data);
      } else {
        toastrService.error('Failed', 'Invoice not saved!');
        console.log(res);
      };

    });
    // Save invoice item
    DataServiceApi.PostData($scope.invoiceItem, server + "invoiceItem/add");
  }
  };

  //save store 
  $scope.addStore = function () {
    DataServiceApi.PostData($scope.store, server + "sotre/add")
  }


  //Call function from validateService to validate form
  $scope.validatediscountTypeForm = validateForms.discountTypeForm;
  //save discountType
  $scope.adddiscountType = function (form) {
    if (form.validate()) {
      DataServiceApi.PostData($scope.discountType, server + "discount-type/add")
      toastrService.success('Success', 'saved successfully');
    } else {
      toastrService.error('Failed', 'not saved!');
    }
  }


  //////////////////////////////////////////////////////////////////////////

  $scope.editRow = function (field) {
    $scope.editing = $scope.invoiceItems.indexOf(field);
    $scope.newField = angular.copy(field);
  };

  $scope.saveField = function (index) {
    if ($scope.editing) {
      $scope.invoiceItems[$scope.editing] = $scope.newField;
      $scope.editing = false;
    }
  };

  $scope.cancel = function (index) {
    if ($scope.editing) {
      $scope.invoiceItems[$scope.editing] = $scope.newField;
      $scope.editing = false;
    }
  };

  //Call function from validateService to validate invoicItem form
  $scope.validateInvoiceItem = validateForms.invoiceItemForm;

  // Add new item to invoice
  $scope.addRow = function (form) {
    var val = $scope.invoiceItem;
    if (form.validate()) {
      $scope.invoiceItems.push({
        item: val.item,
        quantity: val.quantity,
        cost: val.cost
      });
    }
  };

  $scope.deleteRow = function (index) {
    $scope.invoiceItems.splice(index, 1);
  };

  $scope.subTotal = function () {
    var total = 0;
    angular.forEach($scope.invoiceItems, function (item) {
      total += item.quantity * item.cost;
    });
    return total;
  };

  // default value to initlize 
  $scope.invotax = { cost: 0 };

  $scope.calculateTax = function () {
    var totalWithTax = 0;
    // get tax from local storage
    $scope.getTax1 = storageService.getStorage("tax1");
    $scope.getTax2 = storageService.getStorage("tax2");
    //convert from percentage to value to calculate all taxes
    var tax = function percentage(tax) {
      //check if tax exist and not null
      if (tax === null) {
        return tax = 0;
      } else {
        // check if tax in percentage value
        if (tax.rdb === "per" && $scope.subTotal() !== null && $scope.subTotal() !== "" && $scope.subTotal() !== 0) {
          var cost = tax.cost * $scope.subTotal() / 100;
          return cost;
        }
        return cost = tax.cost;
      }
    }
    // check and handle if tax not exist in local storage
    function taxKeyExist(tax) {
      var keys = storageService.getKeys();
      if (storageService.getLength() === 0 || keys.indexOf(tax) === -1) {
        storageService.setStorage(tax, { name: '', cost: 0 });
      }
    }
    taxKeyExist("tax1");
    taxKeyExist("tax2");

    //select default taxes to add to invoice
    !$scope.tax.tax1ckb ? $scope.getTax1.cost = 0 : $scope.getTax1;
    !$scope.tax.tax2ckb ? $scope.getTax2.cost = 0 : $scope.getTax2;
    //calculate all taxes avilable
    totalWithTax = tax($scope.getTax1) + tax($scope.getTax2) + tax($scope.invotax);
    return totalWithTax;
  };

  $scope.calculateGrandTotal = function () {
    return $scope.calculateTax() + $scope.subTotal();
  };

  $scope.resetInvoice = function resetInvoice() {
    $scope.invoice = {};
    $scope.invoiceItem = {};
    $scope.invoiceItems = [];
    $scope.invotax = { cost: 0, rdb: 'val' };
    $scope.tax = {};
  }


  // Enable search and pager in products table.
  $(".footable").footable({
    paging: {
      enabled: true,
      size: 5,
    }
  });

}

/**
 *
 * Pass all functions into module
 */
app
  .controller("MainCtrl", MainCtrl)
  .controller("loginCtrl", loginCtrl)
  .controller("translateCtrl", translateCtrl)
  .controller("accountCtrl", accountCtrl)
  .controller("navbarCtrl", navbarCtrl)
  .controller("userCtrl", userCtrl)
  .controller("invoiceCtrl", invoiceCtrl)
  .controller("settingsCtrl", settingsCtrl)