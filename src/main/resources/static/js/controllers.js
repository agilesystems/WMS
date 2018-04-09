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

function settingsCtrl($scope, $http, storageService, DataServiceApi, toastrService) {

  (function init() {
    DataServiceApi.GetData(server + "setting").then(function (response) {
      $scope.settings = response.data;
    })
  })();

  $scope.saveSettings = function () {
    DataServiceApi.PostData($scope.settings, server + "setting").then(function (res) {
      if (res.status !== 200 && res.data.id !== 1) {
        toastrService.error('Error', '');
        return
      } else { toastrService.success('', 'Settings Updated Successfully') }
    });
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

function accountCtrl($http, $scope, DataServiceApi, validateForms, toastrService) {
  $scope.acType = '1';

  // Get All Acounts Type From API
  DataServiceApi.GetData(server + "account-type/all").then(function (response) {
    $scope.accountsType = response.data;
  });

  // Get Cities From API
  DataServiceApi.GetData(server + "city/all").then(function (response) {
    $scope.cities = response.data;
  });
  // Validate select input
  $scope.onKeyUpSelect = function () {
    $('input[name="inputItem"]').valid();
  }

  // Call function from validateService to validate account form
  $scope.accountFormValidate = validateForms.accountForm;

  $scope.selectAccountType = function () {
    if ($scope.acType === '1') {
      // Get All Customer Acounts From API.
      DataServiceApi.GetData(server + "account/customer/all").then(function (res) {
        $scope.accounts = res.data;
      })
    } else {
      // Get All Supplier Acounts From API.
      DataServiceApi.GetData(server + "account/supplier/all").then(function (res) {
        $scope.accounts = res.data;
      })
    }
  }


  // Add new account
  $scope.addAccount = function (form) {
    if (form.validate()) {
      DataServiceApi.PostData($scope.account, server + "account/add");
    }
  };

  // Edit An Account in Accounts Table
  $scope.editAccount = function (account) {
    $scope.editableAccount = angular.copy(account);
  }

  // Save Edited Account
  $scope.saveAccount = function (form) {
    if (form.validate()) {
      DataServiceApi.PostData($scope.editableAccount, server + 'account/update').then(function (res) {
        if (res.status === 200 && res.data.id > 0) {
          toastrService.success('Done', 'Account Updated Successfully');
        } else {
          toastrService.error('Failed', 'Account Not Updated!');
        }
        console.log($scope.editableAccount);
        console.log(res.data);
      })

    }

  }

  // Delete An Account From Accounts Table
  $scope.deleteAccount = function (index) {

    DataServiceApi.PostData($scope.accounts[index].id, server + 'account/delete/' + 0).then(function (res) {
      if (res.data === true) {
        toastrService.success('Done', 'Account Deleted Successfully');
      } else {
        toastrService.error('Failed', 'Account Not Deleted!');
      }
    })
  };

  // Enable search and pager in products table.
  $(".footable").footable({
    paging: {
      enabled: true,
      size: 5,
    }
  });
}

function navbarCtrl($scope) {
  $scope.parentMenuFilter = function (item) {
    return item.menuCollection.length > 0;
  };
  $scope.subMenuFilter = function (item) {
    return item.url !== null;
  };
}

function userCtrl($scope, $http, DataServiceApi, validateForms, toastrService) {

  // Validate Form
  $scope.validateUser = validateForms.userForm;

  // Get All Users From API.
  DataServiceApi.GetData(server + "user/all").then(function (res) {
    $scope.users = res.data;
  })

  $http({
    method: "GET",
    url: server + "role/all"
  }).then(function (response) {


    if (response.status === 200 && response.data !== null) {
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

  // Edit User in users table
  $scope.editUser = function (user) {
    $scope.editableUser = angular.copy(user);
  }

  // Save Edited User
  $scope.saveUser = function (form) {
    if (form.validate()) {
      DataServiceApi.PostData($scope.editableUser, server + 'user/update').then(function (res) {
        // Get All Users From API.
        DataServiceApi.GetData(server + "user/all").then(function (res) {
          $scope.users = res.data;
        })
      })

    }

  }

  $scope.deleteUser = function (index) {

    DataServiceApi.PostData($scope.users[index].id, server + 'user/delete/' + 0).then(function (res) {
      if (res.data === true) {
        toastrService.success('Done', 'User Deleted Successfully');
      } else {
        toastrService.error('Failed', 'User Not Deleted!');
      }
    })
  };

  // Enable search and pager in products table.
  $(".footable").footable({
    paging: {
      enabled: true,
      size: 5,
    }
  });
}

function invoiceCtrl(
  $scope,
  $rootScope,
  $http,
  DataServiceApi,
  storageService,
  validateForms,
  toaster,
  toastrService,
  $stateParams,
  localStorageService,
  $interval,
  SweetAlert
) {

  console.log($rootScope.currentUser);
  (function init() {
    // Init values
    $scope.newField = {};
    $scope.invoiceItem = {};
    $scope.invoice = {};
    $scope.taxes = {};
    $scope.ckb = {};
    $scope.editing = {};
    $scope.invoiceItems = [];
    $scope.param = $stateParams.type;
    $scope.settings = {};
    // Temp List to save searched items.
    $scope.buyItems = [];
    $scope.sellItems = [];
    getSellInvoiceFromLocalStorage();
    // To search specific item in items list.
    $scope.searchedSellItems = function (itemName) {
      // get items from API
      return DataServiceApi.GetData(server + "item/sell/" + itemName).then(function (response) {
        $scope.sellItems = response.data;
      });
    }

    // To search specific item in items list.
    $scope.searchedBuyItems = function (itemName) {
      // get items from API
      return DataServiceApi.GetData(server + "item/buy/" + itemName).then(function (response) {
        $scope.buyItems = response.data;
      });
    }

    // get Customer Accounts from API
    DataServiceApi.GetData(server + "account/customer/all").then(function (response) {
      $scope.customerAccounts = response.data;
    });

    // get Accounts from API
    DataServiceApi.GetData(server + "account/supplier/all").then(function (response) {
      $scope.supplierAccounts = response.data;
    });

    // get Payment Type from API
    DataServiceApi.GetData(server + "payment-type/all").then(function (response) {
      $scope.payments = response.data;
    });

    // get taxes and discount from settings.
    DataServiceApi.GetData(server + "setting").then(function (response) {
      $scope.settings = response.data;
    });

    // Save Invoice in local storage every 5 seconds
    $interval(saveSellInvoiceToLocalStorage, 5000);

  })();

  //Call function from validateService to validate forms
  $scope.validateInvoice = validateForms.invoiceForm;
  $scope.validateInvoiceItem = validateForms.invoiceItemForm;
  $scope.validateInvoiceItem2 = validateForms.invoiceItem2Form;

  // Save invoice (invoice and invoiceItem)
  $scope.addInvoice = function (form) {

    //  Assign values to invoice object
    $scope.invoice.discount1Percentage = $scope.settings.discount1Valu;
    $scope.invoice.discount2Percentage = $scope.settings.discount2Valu;
    $scope.invoice.discount1Amount = $scope.dis.discount1;
    $scope.invoice.discount2Amount = $scope.dis.discount2;
    $scope.invoice.tax1Percentage = $scope.settings.tax1Value;
    $scope.invoice.tax2Percentage = $scope.settings.tax2Value;
    $scope.invoice.tax3Percentage = $scope.settings.tax3Value;
    $scope.invoice.tax4Percentage = $scope.settings.tax4Value;
    $scope.invoice.tax5Percentage = $scope.settings.tax5Value;
    $scope.invoice.tax1Amount = $scope.taxes.tax1;
    $scope.invoice.tax2Amount = $scope.taxes.tax2;
    $scope.invoice.tax3Amount = $scope.taxes.tax3;
    $scope.invoice.tax4Amount = $scope.taxes.tax4;
    $scope.invoice.tax5Amount = $scope.taxes.tax5;
    $scope.invoice.invoiceItemsList = $scope.invoiceItems;

    if (form.validate()) { // Check inputs validation
      if ($scope.invoiceItems === undefined || $scope.invoiceItems.length === 0) { // Check if no items in invoice
        toastrService.error('Failed!', 'Add one Item to invoice at least please');
        return
      }
      // Save invoice
      DataServiceApi.PostData($scope.invoice, server + "invoice/sell/add").then(function (res) {
        if (res.status === 200) {
          toastrService.success('Success', 'Invoice saved successfully');
          console.log(res.data);
        } else {
          toastrService.error('Failed!', 'Invoice not saved!');
        };
      });
    } else { toastrService.error('', 'Some Fields Required!') }

    console.log($scope.invoice);
  };
  //save store
  // $scope.addStore = function () {
  //   DataServiceApi.PostData($scope.store, server + "sotre/add")
  // }

  /////////////////////////////////////   Add New Item    /////////////////////////////////////

  // To make item value the default value to calculate price after discount
  $scope.discountUpdate = function () {
    $scope.invoiceItem.discountPercentage = $scope.invoiceItem.storeItem.discountPercentage;
  }

  // Return the value after discount
  $scope.priceAfterDiscount = function (unit, discount) {
    return unit - (unit / 100 * discount);
  }

  // Add new item to invoice
  $scope.addRow = function (form) {
    var temp = $scope.invoiceItem;
    if (form.validate()) {  // check form validation
      if ($scope.invoiceItems.length === 0) { // Check if list of items null to add first item
        $scope.invoiceItems.push({
          storeItem: temp.storeItem,
          quantity: temp.quantity,
          unitPrice: temp.unitPrice,
          discountPercentage: temp.discountPercentage,
          totalPrice: temp.totalPrice,
          totalNetPrice: temp.totalNetPrice
        })
      } else { // if list of items not null
        var isEqual = false;
        // Check if item exist to just update it.
        angular.forEach($scope.invoiceItems, function (val) {
          if (temp.storeItem.itemId === val.storeItem.itemId) {
            isEqual = true;
            $scope.updateObject = val;
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
        } else {
          SweetAlert.swal({ // Show message to confirm update item or cancel
            title: "The Item Is Exist",
            text: "Are You Sure You Want To Add It ?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes",
            cancelButtonText: "No",
            closeOnConfirm: true,
            closeOnCancel: true
          },
            function (isConfirm) {
              if (isConfirm) { // update item
                $scope.updateObject.quantity += temp.quantity;
                $scope.updateObject.unitPrice = temp.unitPrice;
                $scope.updateObject.discountPercentage = temp.discountPercentage;
                $scope.updateObject.totalPrice = $scope.updateObject.unitPrice * $scope.updateObject.quantity;
                $scope.updateObject.totalNetPrice = $scope.updateObject.totalPrice - ($scope.updateObject.totalPrice / 100 * $scope.updateObject.discountPercentage);
              }
            });
        }
      }
    }
  };

  $scope.editRow = function (index) {
    $scope.temp = $scope.invoiceItems[index]; // add current item to a temp object to edit it.
    $scope.editing = $scope.temp; // Pass data to edit screen
    $scope.tempQuantity = $scope.editing.quantity; // temp variable to sotre default value
    $scope.tempItem = $scope.editing.storeItem; // temp variable to sotre default value
  };

  // Save item after editting.
  $scope.saveField = function (form) {
    if (form.validate()) {
      // update current object with new values
      $scope.temp.quantity = $scope.editing.quantity;
      $scope.temp.storeItem = $scope.editing.storeItem;
      $scope.temp.unitPrice = $scope.temp.storeItem.price;
      $scope.temp.totalPrice = $scope.temp.quantity * $scope.temp.unitPrice;
      $scope.temp.totalNetPrice = $scope.temp.totalPrice - ($scope.temp.totalPrice / 100 * $scope.temp.discountPercentage);
      $scope.tempQuantity = $scope.temp.quantity; // update temp variable with new value after save
      $scope.tempItem = $scope.temp.storeItem; // update temp variable with new value after save
    }
  };

  $scope.cancelField = function () {
    // Return to default value if press cancel without save or last value if press save.
    $scope.temp.quantity = $scope.tempQuantity;
    $scope.temp.storeItem = $scope.tempItem;
  }

  $scope.deleteRow = function (index) {
    $scope.invoiceItems.splice(index, 1);
  };

  /////////////////////////////////////   Tax And Discount Functions   /////////////////////////////////////

  // Check if all taxes null to hide taxes table.
  $scope.emptyTaxes = function () {
    if ($scope.settings.tax1Value === 0 &&
      $scope.settings.tax2Value === 0 &&
      $scope.settings.tax3Value === 0 &&
      $scope.settings.tax4Value === 0 &&
      $scope.settings.tax5Value === 0) {
      return false
    } else { return true }
  }

  // Check if all Discounts null to hide Discounts table.
  $scope.emptyDiscounts = function () {
    if ($scope.settings.discount1Valu === 0 &&
      $scope.settings.discount2Valu === 0) {
      return false
    } else { return true }
  }

  $scope.subTotal = function () {
    var total = 0;
    angular.forEach($scope.invoiceItems, function (itemVal) {
      total += itemVal.totalNetPrice;
    });
    return total;
  };

  // Check if quantity more than available quantity
  $scope.quantityValidate = function () {
    if ($scope.invoiceItem.quantity > $scope.invoiceItem.storeItem.availableQuantity) {
      $scope.invoiceItem.quantity = $scope.invoiceItem.storeItem.availableQuantity;
    }
  }

  // Return taxes amount minus discounts amount
  $scope.getNetTaxesAndDiscounts = function () {
    var total = 0
    $scope.dis = {};
    // Convert value from percentage for taxes and discounts
    $scope.dis.discount1 = $scope.settings.discount1Valu * $scope.subTotal() / 100;
    $scope.dis.discount2 = $scope.settings.discount2Valu * $scope.subTotal() / 100;
    $scope.taxes.tax1 = $scope.settings.tax1Value * $scope.subTotal() / 100;
    $scope.taxes.tax2 = $scope.settings.tax2Value * $scope.subTotal() / 100;
    $scope.taxes.tax3 = $scope.settings.tax3Value * $scope.subTotal() / 100;
    $scope.taxes.tax4 = $scope.settings.tax4Value * $scope.subTotal() / 100;
    $scope.taxes.tax5 = $scope.settings.tax5Value * $scope.subTotal() / 100;
    return total = $scope.totalTaxes - $scope.getTotalDiscounts();
  };


  $scope.getTotalTaxes = function () {
    $scope.totalTaxes = 0;
    angular.forEach($scope.taxes, function (val, key) {
      if ($scope.ckb[key]) {
        $scope.totalTaxes += val;
      }
    })
    return $scope.totalTaxes;
  }

  // Return total discounts
  $scope.getTotalDiscounts = function () {
    $scope.totalDiscounts = 0;
    $scope.totalDiscounts += $scope.dis1ckb ? $scope.dis.discount1 : 0;
    $scope.totalDiscounts += $scope.dis2ckb ? $scope.dis.discount2 : 0;
    return $scope.totalDiscounts;
  }

  // Return total taxes percent
  $scope.getTotalPercentTaxes = function () {
    $scope.totalPercentTaxes = 0;
    angular.forEach($scope.taxes, function (val, key) {
      if ($scope.ckb[key]) {
        $scope.totalPercentTaxes += val / $scope.subTotal() * 100;
      }
    })
    return $scope.totalPercentTaxes;
  }

  // Return total discount percent
  $scope.getTotalPercentDiscount = function () {
    $scope.invoicePercentAmount = 0;
    $scope.invoicePercentAmount += $scope.dis1ckb ? $scope.dis.discount1 : 0;
    $scope.invoicePercentAmount += $scope.dis2ckb ? $scope.dis.discount2 : 0;
    return $scope.invoicePercentAmount;
  }

  // Return total without any discount or tax.
  $scope.totalInvoiceAmount = function () {
    var total = 0
    angular.forEach($scope.invoiceItems, function (val, key) {
      total += val.totalPrice;
    });
    return $scope.invoice.invoiceAmount = total;
  }

  // Return Net total with taxes and discounts
  $scope.calculateGrandTotal = function () {
    return $scope.invoice.invoiceNetAmount = $scope.getNetTaxesAndDiscounts() + $scope.subTotal();
  };

  // Return invoice Outstanding Amount
  $scope.getinvoiceOutstandingAmount = function () {
    return $scope.invoice.invoiceOutstandingAmount = $scope.calculateGrandTotal() - $scope.invoice.paiedAmount;
  }

  // Return total amount after taxes.
  $scope.getTotalAmountAfterTaxes = function () {
    $scope.invoice.invoiceAmountAfterTax = $scope.subTotal();
    angular.forEach($scope.taxes, function (val, key) {
      if ($scope.ckb[key]) {
        $scope.invoice.invoiceAmountAfterTax += val;
      }
    })
    return $scope.invoice.invoiceAmountAfterTax;
  }

  // Return total amount after discount.
  $scope.getTotalAmountAfterDiscount = function () {
    if ($scope.dis1ckb || $scope.dis2ckb) {
      $scope.invoice.invoiceAmountAfterDiscount = $scope.subTotal()
    } else { $scope.invoice.invoiceAmountAfterDiscount = 0 }
    $scope.invoice.invoiceAmountAfterDiscount -= $scope.dis1ckb ? $scope.dis.discount1 : 0;
    $scope.invoice.invoiceAmountAfterDiscount -= $scope.dis2ckb ? $scope.dis.discount2 : 0;
    return $scope.invoice.invoiceAmountAfterDiscount;
  }

  $scope.checkAllTaxes = function () {
    if ($scope.ckbAllTaxes) {
      $scope.ckb.tax1 = true;
      $scope.ckb.tax2 = true;
      $scope.ckb.tax3 = true;
      $scope.ckb.tax4 = true;
      $scope.ckb.tax5 = true;
    } else { $scope.ckb = {} }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////

  $scope.resetInvoice = function resetInvoice() {
    $scope.invoice = {};
    $scope.invoiceItem = {};
    $scope.invoiceItems = [];
    $scope.taxes = {};
    $scope.ckb = {};
    $scope.dis = {};
    $scope.dis1ckb = '';
    $scope.dis2ckb = '';
  }

  // Save invoice to local storage every 5 seconds...
  function saveSellInvoiceToLocalStorage() {
    localStorageService.set("sellTaxes", $scope.ckb);
    localStorageService.set("sellDiscount1", $scope.dis1ckb);
    localStorageService.set("sellDiscount2", $scope.dis2ckb);
    $scope.invoice.invoiceItemsList = $scope.invoiceItems
    localStorageService.set("sellInvoice", $scope.invoice);

  }

  // Get last invoice saved from local storage
  function getSellInvoiceFromLocalStorage() {
    if (localStorageService.get("sellInvoice") === undefined || localStorageService.get("sellInvoice") === null) {
      return;
    }
    $scope.invoice = localStorageService.get("sellInvoice") //get object from local storage
    angular.forEach($scope.invoice.invoiceItemsList, function (val) {
      $scope.invoiceItems.push(val);
    });
    $scope.ckb = localStorageService.get("sellTaxes");
    $scope.dis1ckb = localStorageService.get("sellDiscount1");
    $scope.dis2ckb = localStorageService.get("sellDiscount2");

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
