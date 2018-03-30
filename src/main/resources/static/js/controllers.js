/* global server, app */

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

function MainCtrl() {
//    if (!$rootScope.currentUser) {
//        console.log("go to login");
//        $state.go("logins");
//    }
};

function settingsCtrl($scope, $http, storageService, DataServiceApi) {

  (function init() {
    DataServiceApi.GetData(server + "setting").then(function (response) {
      $scope.settings = response.data;
    })
  })();


  // $scope.touched = function () {
  //   storageService.setStorage("tax1", $scope.tax1);
  //   storageService.setStorage("tax2", $scope.tax2);
  //   storageService.setStorage("contact", $scope.contact);
  // };
  // $scope.tax1 = storageService.getStorage("tax1");
  // $scope.tax2 = storageService.getStorage("tax2");
  // $scope.contact = storageService.getStorage("contact");

  $scope.updateSettings = function () {
    DataServiceApi.PostData($scope.settings, server + "setting");
}
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
    console.log("Login CTRL Loaded");
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
                        res.data.user.role.menus.forEach(function (m) {
                            m.subMenus.forEach(function (sm) {
                                addState('forms.form_' + sm.id, sm.url);
                            });

                        });
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
    DataServiceApi.GetData(server + "account-type/all").then(function (
            response
            ) {
        $scope.accounts = response.data;
    });

    DataServiceApi.GetData(server + "city/all").then(function (
            response
            ) {
        $scope.cities = response.data;
    });
    $scope.onKeyUpSelect = function () {
        $('input[name="inputItem"]').valid();
    };

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
    
    $http({
                method: "GET",
                url: server + "role/all"
            }).then(function (response) {
              

                if (response.status === 200 && response.data !== null ) {
                    $scope.roles = response.data;
                }

            }).catch(function (response) {
                console.error('Gists error', response.status, response.data);
            }).finally(function () {
                console.log("finally finished gists");
            });
    
//    DataServiceApi.GetData(server + "role/all").then(function (response) {
//        $scope.roles = response.data;
//    });

    $scope.addUser = function (isValid) {

        if (isValid) {
            $scope.user.username = null;
            $scope.user.password = null;
            $http({
                method: "POST",
                url: server + "user/add",
                data: $scope.user
            }).then(function (response) {
                console.log('Post success ', response.status, response.data);

                if (response.status === 200 && response.data !== null && response.data.id > 0) {
                    $scope.user = response.data;
                }

            }).catch(function (response) {
                console.error('Gists error', response.status, response.data);
            }).finally(function () {
                console.log("finally finished gists");
            });

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
  toastrService,
  $stateParams
        ) {


    (function init() {
        // Init values
        $scope.newField = {};
        $scope.invoiceItem = {};
        $scope.invoice = {};
        $scope.tax = {};
        $scope.editing = false;
        $scope.invoiceItems = [];
    $scope.invoiceItem.discountPercentage = '';
    $scope.param = $stateParams.type;

    // Temp List to save searched items.
    $scope.Items =[];
    // To refresh items list in real time.
    $scope.searchedItems = function (itemName) {
        // get items from API
      return DataServiceApi.GetData(server + "item/sell/" + itemName).then(function (response) {
            $scope.Items = response.data;
        });
    }
        // get Accounts from API
    $scope.getAccountByInvoType = function () {
      if ($scope.invoice.invoiceType) {
        if ($scope.invoice.invoiceType.name === 'Buy' || $scope.invoice.invoiceType.name === 'Refund-Buy') {
          DataServiceApi.GetData(server + "account/supplier/all").then(function (response) {
            $scope.accounts = response.data;
        });

        } else {
          DataServiceApi.GetData(server + "account/customer/all").then(function (response) {
            $scope.accounts = response.data;
          });
        }
      }
      else {
        $scope.accounts = undefined;
      }
    }

    //Check if invoice type selected
    $scope.invoiceTypeNotNull = function () {
      if ($scope.invoice.invoiceType === undefined) {
        toastrService.error('خطأ', 'يرجى اختيار نوع الفاتورة أولاً');
      }
    }

        // get Stores from API
        DataServiceApi.GetData(server + "store/all").then(function (response) {
            $scope.stores = response.data;
        });

        // get Invoice Type from API
    $http.get(server + "invoice-type/all").then(function (response) {
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
      //DataServiceApi.PostData($scope.invoiceItem, server + "invoiceItem/add");
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
  ////////////////////////////////////////////////////////////////////////////

  DataServiceApi.GetData(server + "setting").then(function (response) {
    $scope.settings = response.data;
  })

  // Check if all taxes null to hide taxes table.
  $scope.emptyTaxes = function () {
    if (
      $scope.settings.tax1Value === 0 &&
      $scope.settings.tax2Value === 0 &&
      $scope.settings.tax3Value === 0 &&
      $scope.settings.tax4Value === 0 &&
      $scope.settings.tax5Value === 0
    ) {
      return false;
    } else {
      return true;
    }
  }

  // Check if all Discounts null to hide Discounts table.
  $scope.emptyDiscounts = function () {
    if (
      $scope.settings.discount1Valu === 0 &&
      $scope.settings.discount2Valu === 0
    ) {
      return false;
    } else {
      return true;
    }
  }

  // To make item vlaue the default value to calculate price after discount
  $scope.discountUpdate = function () {
    $scope.invoiceItem.discountPercentage = $scope.invoiceItem.storeItem.discountPercentage;
  }
  $scope.netTotalUpdate = function () {
    $scope.invoiceItem.totalNetPrice = $scope.invoiceItem.totalPrice - ($scope.invoiceItem.totalPrice / 100 * $scope.invoiceItem.discountPercentage)
  }

    //////////////////////////////////////////////////////////////////////////

  $scope.editRow = function (index) {
    $scope.editing = $scope.invoiceItems[index];
    };

  $scope.saveField = function () {
      $scope.editing.unitPrice = $scope.editing.storeItem.price;
      $scope.editing.totalPrice = $scope.editing.quantity * $scope.editing.unitPrice;
      $scope.editing.totalNetPrice = $scope.editing.totalPrice - ($scope.editing.totalPrice / 100 * $scope.editing.discountPercentage);
            $scope.editing = false;

    };
    //Call function from validateService to validate invoicItem form
    $scope.validateInvoiceItem = validateForms.invoiceItemForm;

    // Add new item to invoice
    $scope.addRow = function (form) {
    var temp = $scope.invoiceItem;
    if (form.validate()) {  // /check form validation
      if ($scope.invoiceItems.length === 0) { // Check if list of items null to add first item
            $scope.invoiceItems.push({
          storeItem: temp.storeItem,
          quantity: temp.quantity,
          unitPrice: temp.unitPrice,
          discountPercentage: temp.discountPercentage,
          totalPrice: temp.totalPrice,
          totalNetPrice: temp.totalNetPrice
        })
      } else {
        var isEqual = false;
        
        angular.forEach($scope.invoiceItems, function (val, key) { // Check if item exist to just update quantity.
          if (temp.storeItem.itemId === val.storeItem.itemId) {
            val.quantity += temp.quantity;
            val.unitPrice = temp.unitPrice;
            val.discountPercentage = temp.discountPercentage;
            val.totalPrice = val.unitPrice * val.quantity;
            val.totalNetPrice = val.totalPrice - (val.totalPrice / 100 * val.discountPercentage);
            isEqual = true;
          }
            });
        if (!isEqual) { // Add item if not exist.
          $scope.invoiceItems.push({
            storeItem: temp.storeItem,
            quantity: temp.quantity,
            discountPercentage: temp.discountPercentage,
            totalPrice: temp.totalPrice,
            unitPrice: temp.unitPrice,
            totalNetPrice: temp.totalNetPrice
          })
        }
      }
    }
    };

    $scope.deleteRow = function (index) {
        $scope.invoiceItems.splice(index, 1);
    };

    $scope.subTotal = function () {
        var total = 0;
    angular.forEach($scope.invoiceItems, function (itemVal, key) {
      total += itemVal.totalNetPrice;
        });
        return total;
    };
  // Return the value after discount
  $scope.priceAfterDiscount = function (unit, discount) {
    var u = ''; var d = '';
    u += unit; d += discount;
    return u - (u / 100 * d);
  }

  $scope.update = function () {
    $scope.$apply();
  }

    // default value to initlize 
  $scope.invotax = { cost: 0 };

  // Return taxes amount minus discounts amount
  $scope.calculateNetTaxAndDiscount = function () {
    var total = 0
    $scope.discount1 = $scope.settings.discount1Valu * $scope.subTotal() / 100;
    $scope.discount2 = $scope.settings.discount2Valu * $scope.subTotal() / 100;
    $scope.tax1 = $scope.settings.tax1Value * $scope.subTotal() / 100;
    $scope.tax2 = $scope.settings.tax2Value * $scope.subTotal() / 100;
    $scope.tax3 = $scope.settings.tax3Value * $scope.subTotal() / 100;
    $scope.tax4 = $scope.settings.tax4Value * $scope.subTotal() / 100;
    $scope.tax5 = $scope.settings.tax5Value * $scope.subTotal() / 100;

    function isChecked(val, ckb) {
      if (!ckb) {
        
        return val = 0
      }else{
        return val;
                }
            }

    total +=  isChecked($scope.tax1, $scope.tax1ckb) + 
              isChecked($scope.tax2, $scope.tax2ckb) + 
              isChecked($scope.tax3, $scope.tax3ckb) + 
              isChecked($scope.tax4, $scope.tax4ckb) + 
              isChecked($scope.tax5, $scope.tax5ckb) - 
              (isChecked($scope.discount1, $scope.dis1ckb) + 
              isChecked($scope.discount2, $scope.dis2ckb));
    return total;
    };

  // Return Net total with taxes and discounts
    $scope.calculateGrandTotal = function () {
    return $scope.calculateNetTaxAndDiscount() + $scope.subTotal();
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
