
/* global server, app */

/**
 * INSPINIA - Responsive Admin Theme
 *
 * Main controller.js file
 * Define controllers with data used in Inspinia theme
 *
 *
 * Functions (cبontrollers)
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
}
;

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
            } else {
                toastrService.success('', 'Settings Updated Successfully')
            }
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

function invoiceSellCtrl($scope, $rootScope, $http, DataServiceApi, storageService, validateForms, toaster, toastrService, $stateParams, localStorageService, $interval, SweetAlert, $translate) {

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
        $scope.items = [];
        $scope.accounts = [];
        getSellInvoiceFromLocalStorage();
        // To search specific item in items list.
        $scope.searcheditems = function (itemName) {
            // get items from API
            return DataServiceApi.GetData(server + "item/sell/" + itemName).then(function (response) {
                $scope.items = response.data;
            });
        }

        // get Customer Accounts from API
        $scope.searchedAccounts = function (account) {
            DataServiceApi.GetData(server + "account/customer/" + account).then(function (response) {
                $scope.accounts = response.data;
            });
        }

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
                toastrService.error($translate.instant('ERROR'), $translate.instant('EMPTY_ITEMS_LIST_WARNING'));
                return
            }
            // Save invoice
            DataServiceApi.PostData($scope.invoice, server + "invoice/sell/add").then(function (res) {
                if (res.status === 200 && res.data > 0) {
                    toastrService.success('', $translate.instant('SAVE_INVOICE_SUCCESSFULLY_MESSAGE'));
                    console.log(res.data);
                } else {
                    toastrService.error($translate.instant('ERROR'), $translate.instant('INVOICE_NOT_SAVED'));
                    console.log(res);
                }
                ;
            });
        } else {
            toastrService.error($translate.instant('ERROR'), $translate.instant('FIELDS_REQUIRED_MESSAGE'));
        }

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
    function mapStoreItem(storItemDTO) {

        var storItem = {
            id: storItemDTO.storeItemId,
            discountPercentage: storItemDTO.discountPercentage,
            name: storItemDTO.name,
            barcode: storItemDTO.barcode,
            availableQuantity: storItemDTO.availableQuantity,
            expiryDate: storItemDTO.expiryDate,
            globalBarcode: storItemDTO.globalBarcode,
            itemGlobalId: storItemDTO,
            itemId: storItemDTO.itemId,
            price: storItemDTO.price,
            storeId: storItemDTO.storeId,
            storeItemId: storItemDTO.storeItemId,
            storeName: storItemDTO.storeName
        };
        return storItem;
    }
    $scope.addRow = function (form) {
        var temp = $scope.invoiceItem;
        console.log(temp);
        console.log(mapStoreItem(temp.storeItem));
        if (form.validate()) {  // check form validation
            if ($scope.invoiceItems.length === 0) { // Check if list of items null to add first item
                $scope.invoiceItems.push({
                    storeItem: mapStoreItem(temp.storeItem),
                    quantity: temp.quantity,
                    unitPrice: temp.unitPrice,
                    discountPercentage: temp.discountPercentage,
                    totalPrice: temp.totalPrice,
                    totalNetPrice: temp.totalNetPrice
                })
                console.log($scope.invoiceItems);
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
                        storeItem: mapStoreItem(temp.storeItem),
                        quantity: temp.quantity,
                        discountPercentage: temp.discountPercentage,
                        totalPrice: temp.totalPrice,
                        unitPrice: temp.unitPrice,
                        totalNetPrice: temp.totalNetPrice
                    })
                } else {
                    SweetAlert.swal({// Show message to confirm update item or cancel
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
                                if ($scope.updateObject.quantity > temp.storeItem.availableQuantity) {
                                    toastrService.warning('', $translate.instant('QUANTITY_WARNING'));
                                    $scope.updateObject.quantity = temp.storeItem.availableQuantity
                                }

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
        console.log($scope.editing);
        console.log($scope.invoiceItems[index]);
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
            $scope.tempQuantity = $scope.temp.quantity; // update quantity with new value after save
            $scope.tempItem = $scope.temp.storeItem; // update temp variable with new value after save
            angular.element('.modal').modal('hide'); // close modal after save
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
        } else {
            return true
        }
    }

    // Check if all Discounts null to hide Discounts table.
    $scope.emptyDiscounts = function () {
        if ($scope.settings.discount1Valu === 0 &&
            $scope.settings.discount2Valu === 0) {
            return false
        } else {
            return true
        }
    }

    $scope.subTotal = function () {
        var total = 0;
        angular.forEach($scope.invoiceItems, function (itemVal) {
            total += itemVal.totalNetPrice;
        });
        return total;
    };

    // Show availble quantity on focus
    $scope.availableQuantityAlert = function (val) {
        toastrService.info($translate.instant('AVAILABLE_Q') + val.storeItem.availableQuantity, '');
    }

    // Check if quantity more than available quantity
    $scope.quantityValidate = function (val) {
        if (val.quantity > val.storeItem.availableQuantity) {
            val.quantity = val.storeItem.availableQuantity;
            toastrService.warning('', $translate.instant('QUANTITY_WARNING'));
        }
    }

    // Check if paid amount more than grandTotal
    $scope.paidAmountValidate = function () {
        if ($scope.invoice.paiedAmount > $scope.calculateGrandTotal()) {
            $scope.invoice.paiedAmount = $scope.calculateGrandTotal()
            toastrService.warning('', $translate.instant('PAID_AMOUNT_WARNING'));
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
        } else {
            $scope.invoice.invoiceAmountAfterDiscount = 0
        }
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
        } else {
            $scope.ckb = {}
        }
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

function invoiceRefundSellCtrl($scope, $rootScope, $http, DataServiceApi, storageService, validateForms, toaster, toastrService, $stateParams, localStorageService, $interval, SweetAlert, $translate) {

    (function init() {
        // Init values
        $scope.newQval = {};
        $scope.newInvoice = {};
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
        $scope.items = [];
        $scope.customerAccounts = [];
        getRefundSellInvoiceFromLocalStorage();
        // To search specific item in items list.
        $scope.searcheditems = function (itemName) {
            // get items from API
            return DataServiceApi.GetData(server + "item/sell/" + itemName).then(function (response) {
                $scope.items = response.data;
            });
        }

        //get invoices by account
        $scope.getAccountInvoices = function () {
            if ($scope.invoice.account === undefined) {
                return;
            } else {
                DataServiceApi.GetData(server + "invoice/getAllByAccount/" + $scope.invoice.account.id).then(function (res) {
                    $scope.accountInvoices = res.data;
                });
            }
        }

        // Assign selected invoice to fields
        $scope.selectedInvoice = function () {
            $scope.invoiceItems = $scope.invoice.tempInvoice.invoiceItemsDTOList;
            $scope.newInvoice.paymentType = $scope.invoice.tempInvoice.paymentType;
            $scope.newInvoice.invoiceItemsList = $scope.invoiceItems
            $scope.newInvoice.reference = "Reference From: " + $scope.invoice.tempInvoice.reference;
            $scope.newInvoice.account = $scope.invoice.tempInvoice.accountDTO;
            $scope.newInvoice.branch = $scope.invoice.tempInvoice.branchDTO;
            $scope.newInvoice.invoiceAmount = $scope.invoice.tempInvoice.invoiceAmount;
            $scope.newInvoice.invoiceNetAmount = $scope.invoice.tempInvoice.invoiceNetAmount;
            $scope.newInvoice.invoiceOutstandingAmount = $scope.invoice.tempInvoice.invoiceOutstandingAmount;
            // $scope.newInvoice.paiedAmount = paiedAmount;
            $scope.paidAmountValidate();
        }

        // get Customer Accounts from API
        $scope.searchedAccounts = function (account) {
            DataServiceApi.GetData(server + "account/customer/" + account).then(function (response) {
                $scope.customerAccounts = response.data;
            });
        }

        // get Payment Type from API
        DataServiceApi.GetData(server + "payment-type/all").then(function (response) {
            $scope.payments = response.data;
        });

        // Save Invoice in local storage every 5 seconds
        $interval(saveRefundSellInvoiceToLocalStorage, 5000);

    })();

    //Call function from validateService to validate forms
    $scope.validateInvoice = validateForms.invoiceForm;
    $scope.validateInvoiceItem2 = validateForms.invoiceItem2Form;

    // Save invoice (invoice and invoiceItem)
    $scope.addInvoice = function (form) {

        if (form.validate()) { // Check inputs validation
            if ($scope.invoiceItems === undefined || $scope.invoiceItems.length === 0) { // Check if no items in invoice
                toastrService.error($translate.instant('ERROR'), $translate.instant('EMPTY_ITEMS_LIST_WARNING'));
                return
            }
            // Save invoice
            DataServiceApi.PostData($scope.newInvoice, server + "invoice/refundSell/add").then(function (res) {
                if (res.status === 200 && res.data > 0) {
                    toastrService.success('', $translate.instant('SAVE_INVOICE_SUCCESSFULLY_MESSAGE'));
                    console.log(res.data);
                } else {
                    toastrService.error($translate.instant('ERROR'), $translate.instant('INVOICE_NOT_SAVED'));
                    console.log(res);
                }
                ;
            });
        } else {
            toastrService.error($translate.instant('ERROR'), $translate.instant('FIELDS_REQUIRED_MESSAGE'));
        }

        console.log($scope.invoice);
    };

    $scope.editRow = function (index) {
        $scope.temp = $scope.invoiceItems[index]; // add current item to a temp object to edit it.
        $scope.editing = $scope.temp; // Pass data to edit screen
        $scope.tempQuantity = $scope.editing.quantity; // temp variable to sotre default value
        //$scope.tempItem = $scope.editing.storeItem; // temp variable to sotre default value
    };

    // Save item after editting.
    $scope.saveField = function (form) {
        if (form.validate()) {
            // update current object with new values
            $scope.temp.quantity = $scope.editing.quantity;
            //$scope.temp.storeItem = $scope.editing.storeItem;
            //$scope.temp.unitPrice = $scope.temp.storeItem.price;
            $scope.temp.totalPrice = $scope.temp.quantity * $scope.temp.unitPrice;
            $scope.temp.totalNetPrice = $scope.temp.totalPrice - ($scope.temp.totalPrice / 100 * $scope.temp.discountPercentage);
            $scope.tempQuantity = $scope.temp.quantity; // update quantity with new value after save
            //$scope.tempItem = $scope.temp.storeItem; // update temp variable with new value after save
            angular.element('.modal').modal('hide'); // close modal after save
        }
    };

    $scope.cancelField = function () {
        // Return to default value if press cancel without save or last value if press save.
        $scope.temp.quantity = $scope.tempQuantity;
        //$scope.temp.storeItem = $scope.tempItem;
    }

    $scope.deleteRow = function (index) {
        $scope.invoiceItems.splice(index, 1);
    };

    $scope.subTotal = function () {
        var total = 0;
        angular.forEach($scope.invoiceItems, function (itemVal) {
            total += itemVal.totalNetPrice;
        });
        return total;
    };

    // Get old quantity value to validate
    $scope.oldQuantity = function (val) {
        $scope.$watch('val', function () {
            $scope.newQval = val.quantity;
        });
    }

    // Check if quantity more than available quantity
    $scope.quantityValidate = function (val) {
        if (val.quantity > $scope.newQval) {
            val.quantity = $scope.newQval;
            toastrService.warning('', $translate.instant('REFUND_QUANTITY_WARNING'));
        }
    }

    // Check if paid amount more than grandTotal
    $scope.paidAmountValidate = function () {
        if ($scope.newInvoice.paiedAmount > $scope.subTotal()) {
            $scope.newInvoice.paiedAmount = $scope.subTotal()
            toastrService.warning('', $translate.instant('PAIDAMOUNT_WARNING'));
        }
    }

    // Return total without any discount or tax.
    $scope.totalInvoiceAmount = function () {
        var total = 0
        angular.forEach($scope.invoiceItems, function (val, key) {
            total += val.totalPrice;
        });
        return $scope.newInvoice.invoiceAmount = total;
    }

    // Return Net total with taxes and discounts
    $scope.calculateGrandTotal = function () {
        return $scope.newInvoice.invoiceNetAmount = $scope.getNetTaxesAndDiscounts() + $scope.subTotal();
    };

    // Return invoice Outstanding Amount
    $scope.getinvoiceOutstandingAmount = function () {
        return $scope.newInvoice.invoiceOutstandingAmount = $scope.subTotal() - $scope.newInvoice.paiedAmount;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    $scope.resetInvoice = function resetInvoice() {
        $scope.invoice = {};
        $scope.newInvoice = {};
        $scope.invoiceItem = {};
        $scope.invoiceItems = [];
    }

    // Save invoice to local storage every 5 seconds...
    function saveRefundSellInvoiceToLocalStorage() {
        $scope.newInvoice.invoiceItemsList = $scope.invoiceItems
        localStorageService.set("refundSellInvoice", $scope.newInvoice);
        localStorageService.set("refundSellAcountInvoice", $scope.invoice);

    }

    // Get last invoice saved from local storage
    function getRefundSellInvoiceFromLocalStorage() {
        if (localStorageService.get("refundSellInvoice") === undefined || localStorageService.get("refundSellInvoice") === null) {
            return;
        }
        if (localStorageService.get("refundSellAcountInvoice") === undefined || localStorageService.get("refundSellAcountInvoice") === null) {
            return;
        }
        $scope.newInvoice = localStorageService.get("refundSellInvoice") //get object from local storage
        $scope.invoice = localStorageService.get("refundSellAcountInvoice") //get object from local storage        
        angular.forEach($scope.newInvoice.invoiceItemsList, function (val) {
            $scope.invoiceItems.push(val);
        });
    }

    // Enable search and pager in products table.
    $(".footable").footable({
        paging: {
            enabled: true,
            size: 5,
        }
    });

}

function invoiceBuyCtrl($scope, $rootScope, $http, DataServiceApi, storageService, validateForms, toaster, toastrService, $stateParams, localStorageService, $interval, SweetAlert, $translate) {

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
        $scope.items = [];
        $scope.accounts = [];
        getBuyInvoiceFromLocalStorage();
        // To search specific item in items list.
        $scope.searcheditems = function (itemName) {
            // get items from API
            return DataServiceApi.GetData(server + "item/buy/" + itemName).then(function (response) {
                $scope.items = response.data;
            });
        }

        // get Customer Accounts from API
        $scope.searchedAccounts = function (account) {
            DataServiceApi.GetData(server + "account/supplier/" + account).then(function (response) {
                $scope.accounts = response.data;
            });
        }

        // get Payment Type from API
        DataServiceApi.GetData(server + "payment-type/all").then(function (response) {
            $scope.payments = response.data;
        });

        // get taxes and discount from settings.
        DataServiceApi.GetData(server + "setting").then(function (response) {
            $scope.settings = response.data;
        });

        // Save Invoice in local storage every 5 seconds
        $interval(saveBuyInvoiceToLocalStorage, 5000);

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
                toastrService.error($translate.instant('ERROR'), $translate.instant('EMPTY_ITEMS_LIST_WARNING'));
                return
            }
            // Save invoice
            DataServiceApi.PostData($scope.invoice, server + "invoice/buy/add").then(function (res) {
                if (res.status === 200 && res.data > 0) {
                    toastrService.success('', $translate.instant('SAVE_INVOICE_SUCCESSFULLY_MESSAGE'));
                    console.log(res.data);
                } else {
                    toastrService.error($translate.instant('ERROR'), $translate.instant('INVOICE_NOT_SAVED'));
                    console.log(res);
                }
                ;
            });
        } else {
            toastrService.error($translate.instant('ERROR'), $translate.instant('FIELDS_REQUIRED_MESSAGE'));
        }

        console.log($scope.invoice);
    };
    //save store
    // $scope.addStore = function () {
    //   DataServiceApi.PostData($scope.store, server + "sotre/add")
    // }

    /////////////////////////////////////   Add New Item    /////////////////////////////////////

    // To make discount value 0 by default
    $scope.discountUpdate = function () {
        if ($scope.invoiceItem.discountPercentage === undefined) {
            $scope.invoiceItem.discountPercentage = 0;
        }
    }

    // Return the value after discount
    $scope.priceAfterDiscount = function (unit, discount) {
        return unit - (unit / 100 * discount);
    }

    // Add new item to invoice
    function mapStoreItem(storItemDTO) {

        var storItem = {
            id: storItemDTO.id,
            discountPercentage: storItemDTO.discountPercentage,
            name: storItemDTO.name,
            barcode: storItemDTO.barcode,
            availableQuantity: storItemDTO.availableQuantity,
            expiryDate: storItemDTO.expiryDate,
            globalBarcode: storItemDTO.globalBarcode,
            itemGlobalId: storItemDTO,
            itemId: storItemDTO.itemId,
            price: storItemDTO.price,
            storeId: storItemDTO.storeId,
            //storeItemId: storItemDTO.storeItemId,
            storeName: storItemDTO.storeName
        };
        return storItem;
    }
    $scope.addRow = function (form) {
        $scope.invoiceItem.unitPrice = $scope.newPrice;
        var temp = $scope.invoiceItem;
        console.log(temp);
        console.log(mapStoreItem(temp.storeItem));
        if (form.validate()) {  // check form validation
            if ($scope.invoiceItems.length === 0) { // Check if list of items null to add first item
                $scope.invoiceItems.push({
                    storeItem: mapStoreItem(temp.storeItem),
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
                    if (temp.storeItem.name === val.storeItem.name) {
                        isEqual = true;
                        $scope.updateObject = val;
                    }
                });
                if (!isEqual) { // Add item if not exist.
                    temp.unitPrice = $scope.newPrice;
                    $scope.invoiceItems.push({
                        storeItem: mapStoreItem(temp.storeItem),
                        quantity: temp.quantity,
                        discountPercentage: temp.discountPercentage,
                        totalPrice: temp.totalPrice,
                        unitPrice: temp.unitPrice,
                        totalNetPrice: temp.totalNetPrice
                    })
                } else {
                    SweetAlert.swal({// Show message to confirm update item or cancel
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
                                if ($scope.updateObject.quantity > temp.storeItem.availableQuantity) {
                                    toastrService.warning('', $translate.instant('QUANTITY_WARNING'));
                                    $scope.updateObject.quantity = temp.storeItem.availableQuantity
                                }

                                $scope.updateObject.unitPrice = temp.unitPrice;
                                $scope.updateObject.discountPercentage = temp.discountPercentage;
                                $scope.updateObject.totalPrice = $scope.updateObject.unitPrice * $scope.updateObject.quantity;
                                $scope.updateObject.totalNetPrice = $scope.updateObject.totalPrice - ($scope.updateObject.totalPrice / 100 * $scope.updateObject.discountPercentage);
                            }
                        });
                }
            }
        }
        console.log($scope.invoiceItems);
    };

    $scope.editRow = function (index) {
        $scope.temp = $scope.invoiceItems[index]; // add current item to a temp object to edit it.
        $scope.editing = $scope.temp; // Pass data to edit screen
        $scope.tempQuantity = $scope.editing.quantity; // temp variable to sotre default value
        $scope.tempItem = $scope.editing.storeItem; // temp variable to sotre default value
        console.log($scope.editing);
        console.log($scope.invoiceItems[index]);
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
            $scope.tempQuantity = $scope.temp.quantity; // update quantity with new value after save
            $scope.tempItem = $scope.temp.storeItem; // update temp variable with new value after save
            angular.element('.modal').modal('hide'); // close modal after save
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
        } else {
            return true
        }
    }

    // Check if all Discounts null to hide Discounts table.
    $scope.emptyDiscounts = function () {
        if ($scope.settings.discount1Valu === 0 &&
            $scope.settings.discount2Valu === 0) {
            return false
        } else {
            return true
        }
    }

    $scope.subTotal = function () {
        var total = 0;
        angular.forEach($scope.invoiceItems, function (itemVal) {
            total += itemVal.totalNetPrice;
        });
        return total;
    };

    // Check if paid amount more than grandTotal
    $scope.paidAmountValidate = function () {
        if ($scope.invoice.paiedAmount > $scope.calculateGrandTotal()) {
            $scope.invoice.paiedAmount = $scope.calculateGrandTotal()
            toastrService.warning('', $translate.instant('PAIDAMOUNT_WARNING'));
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
        } else {
            $scope.invoice.invoiceAmountAfterDiscount = 0
        }
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
        } else {
            $scope.ckb = {}
        }
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
    function saveBuyInvoiceToLocalStorage() {
        localStorageService.set("buyTaxes", $scope.ckb);
        localStorageService.set("buyDiscount1", $scope.dis1ckb);
        localStorageService.set("buyDiscount2", $scope.dis2ckb);
        $scope.invoice.invoiceItemsList = $scope.invoiceItems
        localStorageService.set("buyInvoice", $scope.invoice);

    }

    // Get last invoice saved from local storage
    function getBuyInvoiceFromLocalStorage() {
        if (localStorageService.get("buyInvoice") === undefined || localStorageService.get("buyInvoice") === null) {
            return;
        }
        $scope.invoice = localStorageService.get("buyInvoice") //get object from local storage
        angular.forEach($scope.invoice.invoiceItemsList, function (val) {
            $scope.invoiceItems.push(val);
        });
        $scope.ckb = localStorageService.get("buyTaxes");
        $scope.dis1ckb = localStorageService.get("buyDiscount1");
        $scope.dis2ckb = localStorageService.get("buyDiscount2");

    }

    // Enable search and pager in products table.
    $(".footable").footable({
        paging: {
            enabled: true,
            size: 5,
        }
    });


}


function invoiceRefundBuyCtrl($scope, $rootScope, $http, DataServiceApi, storageService, validateForms, toaster, toastrService, $stateParams, localStorageService, $interval, SweetAlert, $translate) {

    (function init() {
        // Init values
        $scope.newQval = {};
        $scope.newInvoice = {};
        $scope.newField = {};
        $scope.invoiceItem = {};
        $scope.invoice = {};
        $scope.taxes = {};
        $scope.editing = {};
        $scope.invoiceItems = [];
        $scope.param = $stateParams.type;
        // Temp List to save searched items.
        $scope.items = [];
        $scope.supplierAccounts = [];
        getRefundBuyInvoiceFromLocalStorage();
        // To search specific item in items list.
        $scope.searcheditems = function (itemName) {
            // get items from API
            return DataServiceApi.GetData(server + "item/buy/" + itemName).then(function (response) {
                $scope.items = response.data;
            });
        }

        //get invoices by account
        $scope.getAccountInvoices = function () {
            if ($scope.invoice.account === undefined) {
                return;
            } else {
                DataServiceApi.GetData(server + "invoice/getAllByAccount/" + $scope.invoice.account.id).then(function (res) {
                    $scope.accountInvoices = res.data;
                });
            }
        }

        // Assign selected invoice to fields
        $scope.selectedInvoice = function () {
            $scope.invoiceItems = $scope.invoice.tempInvoice.invoiceItemsDTOList;
            $scope.newInvoice.paymentType = $scope.invoice.tempInvoice.paymentType;
            $scope.newInvoice.invoiceItemsList = $scope.invoiceItems
            $scope.newInvoice.reference = "Reference From: " + $scope.invoice.tempInvoice.reference;
            $scope.newInvoice.account = $scope.invoice.tempInvoice.accountDTO;
            $scope.newInvoice.branch = $scope.invoice.tempInvoice.branchDTO;
            $scope.newInvoice.invoiceAmount = $scope.invoice.tempInvoice.invoiceAmount;
            $scope.newInvoice.invoiceNetAmount = $scope.invoice.tempInvoice.invoiceNetAmount;
            $scope.newInvoice.invoiceOutstandingAmount = $scope.invoice.tempInvoice.invoiceOutstandingAmount;
            // $scope.newInvoice.paiedAmount = paiedAmount;
            $scope.paidAmountValidate();
        }

        // get Customer Accounts from API
        $scope.searchedAccounts = function (account) {
            DataServiceApi.GetData(server + "account/supplier/" + account).then(function (response) {
                $scope.supplierAccounts = response.data;
            });
        }

        // get Payment Type from API
        DataServiceApi.GetData(server + "payment-type/all").then(function (response) {
            $scope.payments = response.data;
        });

        // Save Invoice in local storage every 5 seconds
        $interval(saveRefundBuyInvoiceToLocalStorage, 5000);

    })();

    //Call function from validateService to validate forms
    $scope.validateInvoice = validateForms.invoiceForm;
    $scope.validateInvoiceItem2 = validateForms.invoiceItem2Form;

    // Save invoice (invoice and invoiceItem)
    $scope.addInvoice = function (form) {

        if (form.validate()) { // Check inputs validation
            if ($scope.invoiceItems === undefined || $scope.invoiceItems.length === 0) { // Check if no items in invoice
                toastrService.error($translate.instant('ERROR'), $translate.instant('EMPTY_ITEMS_LIST_WARNING'));
                return
            }
            // Save invoice
            DataServiceApi.PostData($scope.newInvoice, server + "invoice/refundBuy/add").then(function (res) {
                if (res.status === 200 && res.data > 0) {
                    toastrService.success('', $translate.instant('SAVE_INVOICE_SUCCESSFULLY_MESSAGE'));
                    console.log(res.data);
                } else {
                    toastrService.error($translate.instant('ERROR'), $translate.instant('INVOICE_NOT_SAVED'));
                    console.log(res);
                }
                ;
            });
        } else {
            toastrService.error($translate.instant('ERROR'), $translate.instant('FIELDS_REQUIRED_MESSAGE'));
        }

        console.log($scope.invoice);
    };

    $scope.editRow = function (index) {
        $scope.temp = $scope.invoiceItems[index]; // add current item to a temp object to edit it.
        $scope.editing = $scope.temp; // Pass data to edit screen
        $scope.tempQuantity = $scope.editing.quantity; // temp variable to sotre default value
        //$scope.tempItem = $scope.editing.storeItem; // temp variable to sotre default value
    };

    // Save item after editting.
    $scope.saveField = function (form) {
        if (form.validate()) {
            // update current object with new values
            $scope.temp.quantity = $scope.editing.quantity;
            //$scope.temp.storeItem = $scope.editing.storeItem;
            //$scope.temp.unitPrice = $scope.temp.storeItem.price;
            $scope.temp.totalPrice = $scope.temp.quantity * $scope.temp.unitPrice;
            $scope.temp.totalNetPrice = $scope.temp.totalPrice - ($scope.temp.totalPrice / 100 * $scope.temp.discountPercentage);
            $scope.tempQuantity = $scope.temp.quantity; // update quantity with new value after save
            //$scope.tempItem = $scope.temp.storeItem; // update temp variable with new value after save
            angular.element('.modal').modal('hide'); // close modal after save
        }
    };

    $scope.cancelField = function () {
        // Return to default value if press cancel without save or last value if press save.
        $scope.temp.quantity = $scope.tempQuantity;
        //$scope.temp.storeItem = $scope.tempItem;
    }

    $scope.deleteRow = function (index) {
        $scope.invoiceItems.splice(index, 1);
    };

    $scope.subTotal = function () {
        var total = 0;
        angular.forEach($scope.invoiceItems, function (itemVal) {
            total += itemVal.totalNetPrice;
        });
        return total;
    };

    // Get old quantity value to validate
    $scope.oldQuantity = function (val) {
        $scope.$watch('val', function () {
            $scope.newQval = val.quantity;
        });
    }

    // Check if quantity more than available quantity
    $scope.quantityValidate = function (val) {
        if (val.quantity > $scope.newQval) {
            val.quantity = $scope.newQval;
            toastrService.warning('', $translate.instant('REFUND_QUANTITY_WARNING'));
        }
    }

    // Check if paid amount more than grandTotal
    $scope.paidAmountValidate = function () {
        if ($scope.newInvoice.paiedAmount > $scope.subTotal()) {
            $scope.newInvoice.paiedAmount = $scope.subTotal()
            toastrService.warning('', $translate.instant('PAIDAMOUNT_WARNING'));
        }
    }

    // Return total without any discount or tax.
    $scope.totalInvoiceAmount = function () {
        var total = 0
        angular.forEach($scope.invoiceItems, function (val, key) {
            total += val.totalPrice;
        });
        return $scope.newInvoice.invoiceAmount = total;
    }

    // Return Net total with taxes and discounts
    $scope.calculateGrandTotal = function () {
        return $scope.newInvoice.invoiceNetAmount = $scope.getNetTaxesAndDiscounts() + $scope.subTotal();
    };

    // Return invoice Outstanding Amount
    $scope.getinvoiceOutstandingAmount = function () {
        return $scope.newInvoice.invoiceOutstandingAmount = $scope.subTotal() - $scope.newInvoice.paiedAmount;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    $scope.resetInvoice = function resetInvoice() {
        $scope.invoice = {};
        $scope.newInvoice = {};
        $scope.invoiceItem = {};
        $scope.invoiceItems = [];
    }

    // Save invoice to local storage every 5 seconds...
    function saveRefundBuyInvoiceToLocalStorage() {
        $scope.newInvoice.invoiceItemsList = $scope.invoiceItems
        localStorageService.set("refundBuyInvoice", $scope.newInvoice);
        localStorageService.set("refundBuyAcountInvoice", $scope.invoice);

    }

    // Get last invoice saved from local storage
    function getRefundBuyInvoiceFromLocalStorage() {
        if (localStorageService.get("refundBuyInvoice") === undefined || localStorageService.get("refundBuyInvoice") === null) {
            return;
        }
        if (localStorageService.get("refundBuyAcountInvoice") === undefined || localStorageService.get("refundBuyAcountInvoice") === null) {
            return;
        }
        $scope.newInvoice = localStorageService.get("refundBuyInvoice") //get object from local storage
        $scope.invoice = localStorageService.get("refundBuyAcountInvoice") //get object from local storage        
        angular.forEach($scope.newInvoice.invoiceItemsList, function (val) {
            $scope.invoiceItems.push(val);
        });
    }

    // Enable search and pager in products table.
    $(".footable").footable({
        paging: {
            enabled: true,
            size: 5,
        }
    });

}

function invoicesCtrl($scope, $rootScope, $http, DataServiceApi, storageService, validateForms, toaster, toastrService, $stateParams, localStorageService, $interval, SweetAlert, $translate) {
    (function () {
        $scope.invoice = {};
        localInvoices = {};
        $scope.accountInvoices = [];
    getInvoicesFromLocalStorage();
    // Save Invoice in local storage every 5 seconds
    $interval(saveInvoicesToLocalStorage, 5000);
    })();

    // Get accounts list by select account type
    $scope.selectAccountType = function () {
        if ($scope.acType === '1') {
            $scope.accounts = [];
            // get Customer Accounts from API
            $scope.searchedAccounts = function (account) {
                DataServiceApi.GetData(server + "account/customer/" + account).then(function (response) {
                    $scope.accounts = response.data;
                });
            }
        } else if ($scope.acType === '2') {
            $scope.accounts = [];
            $scope.searchedAccounts = function (account) {
                DataServiceApi.GetData(server + "account/supplier/" + account).then(function (response) {
                    $scope.accounts = response.data;
                });
            }
        } else { $scope.accounts = [] };
    }

    //get invoices by account
    $scope.getAccountInvoices = function () {
        if ($scope.invoice.account === null || $scope.invoice.account === undefined) {
            $scope.accountInvoices = [];
        } else {
            DataServiceApi.GetData(server + "invoice/getAllByAccount/" + $scope.invoice.account.id).then(function (res) {
                $scope.accountInvoices = res.data;
            });
        }
    }

    // Delete Invoice
    $scope.deleteInvoice = function (index) {
        if ($scope.acType === '1') {
            DataServiceApi.PostData($scope.accountInvoices[index].id, server + "invoice/deleteSellInvoice/" + $scope.accountInvoices[index].id).then(function (res) {
                if (res.status === 200 && res.data === true) {
                    toastrService.success('', $translate.instant('INVOICE_DELETED_MSG') );
                } else {
                    toastrService.error($translate.instant('ERROR'), $translate.instant('INVOICE_NOT_DELETED_MSG'));
                }
            });
        } else {
            DataServiceApi.PostData($scope.accountInvoices[index].id, server + "invoice/deleteBuyInvoice/" + $scope.accountInvoices[index].id).then(function (res) {
                if (res.status === 200 && res.data === true) {
                    toastrService.success('', $translate.instant('INVOICE_DELETED_MSG'));
                } else {
                    toastrService.error($translate.instant('ERROR'), $translate.instant('INVOICE_NOT_DELETED_MSG'));
                }
            });
        }
    }

    // Save invoice to local storage every 5 seconds...
    function saveInvoicesToLocalStorage() {
        $scope.localInvoices = $scope.invoice;
        $scope.localInvoices.acType = $scope.acType;
        $scope.localInvoices.invoices = $scope.accountInvoices;
        localStorageService.set("accountInvoices", $scope.localInvoices);

    }

    // Get last invoice saved from local storage
    function getInvoicesFromLocalStorage() {
        if (localStorageService.get("accountInvoices") === undefined || localStorageService.get("accountInvoices") === null) {
            return;
        }
        
        $scope.localInvoices = localStorageService.get("accountInvoices");
        $scope.invoice.account = $scope.localInvoices.account;
        $scope.acType = $scope.localInvoices.acType;
        angular.forEach($scope.localInvoices.invoices, function (val) {
            $scope.accountInvoices.push(val);
        });
    }

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
    .controller("invoiceSellCtrl", invoiceSellCtrl)
    .controller("invoiceRefundSellCtrl", invoiceRefundSellCtrl)
    .controller("invoiceBuyCtrl", invoiceBuyCtrl)
    .controller("invoiceRefundBuyCtrl", invoiceRefundBuyCtrl)
    .controller("settingsCtrl", settingsCtrl)
    .controller("invoicesCtrl", invoicesCtrl)
