app

    .config(['$translateProvider', translateConfig])
    .factory('toastrService', ['toaster', toasterFactory])
    .config(['$validatorProvider', validateConfig])
    .factory('validateForms', validateFactory)
    .config(['localStorageServiceProvider', localStorageConfig])
    .factory("storageService", ['localStorageService', localStorageFactory])
    .factory('DataServiceApi', ['$http', '$q', dataFromRest])

//=================== Validate config function===================
function validateConfig($validatorProvider) {

    $validatorProvider

        .setDefaults({
            success: "valid"
        });
    $validatorProvider
        // Check if Mobile phone number is correct
        .addMethod("mobileEG", function (phone_number, element) {
            phone_number = phone_number.replace(/\(|\)|\s+|-/g, "");
            return this.optional(element) || (phone_number.length > 9 && phone_number.match(/(010|011|012|014|015)\d{8}/));
        }, "Please specify a valid mobile number")
}

/**
 * Call this service in controller like :
 * toastrService.success(title, message);
 * function with number 2 applied in another contianer with custom properties
 */
function toasterFactory(toaster) {
    return {
        success: function (title, body) {
            toaster.pop({ type: 'success', title: title, body: body });
        },
        success2: function (title, body) {
            toaster.pop({ type: 'success', title: title, body: body, toasterId: 2 });
        },
        error: function (title, body) {
            toaster.pop({ type: 'error', title: title, body: body });
        },
        error2: function (title, body) {
            toaster.pop({ type: 'error', title: title, body: body, toasterId: 2 });
        },
        info: function (title, body) {
            toaster.pop({ type: 'info', title: title, body: body });
        },
        info2: function (title, body) {
            toaster.pop({ type: 'info', title: title, body: body, toasterId: 2 });
        },
        warning: function (title, body) {
            toaster.pop({ type: 'warning', title: title, body: body });
        },
        warning2: function (title, body) {
            toaster.pop({ type: 'warning', title: title, body: body, toasterId: 2 });
        },
    };
};


// /Local storage config (set default values)
function localStorageConfig(localStorageServiceProvider) {
    localStorageServiceProvider
        .setPrefix("wms")
        .setStorageType("localStorage")
        .setNotify("true, true");
};






// Local storage functions
function localStorageFactory(localStorageService) {
    storage = {};

    /**
     *  Local Storage Functions
     * 
     */
    storage.getStorage = getStorage;
    function getStorage(key) {
        return localStorageService.get(key);
    }

    storage.setStorage = setStorage;
    function setStorage(key, val) {
        localStorageService.set(key, val);
    }

    //Get specific key only
    storage.getkey = getkey;
    function getkey(key) {
        return localStorageService.deriveKey(key);
    }
    //Get localstorage items length
    storage.getLength = getLength;
    function getLength() {
        return localStorageService.length();
    }

    //Get localstorage kyes
    storage.getKeys = getKeys;
    function getKeys() {
        return localStorageService.keys();
    }
    // Return the derive key As String
    storage.deriveKey = deriveKey;
    function deriveKey(key) {
        return localStorageService.deriveKey(key)
    }

    storage.removeOne = removeOne;
    function removeOne(key) {
        localStorageService.remove(key);
    }

    storage.clearAll = clearAll;
    function clearAll() {
        localStorageService.clearAll();
    }

    /**
     *  Set Storage with sessionStorage
     */
    storage.setSessionStorage = setSessionStorage;
    function setSessionStorage(key, val) {
        localStorageService.set(key, val, "sessionStorage");
    }
    return storage;
};




// Data setter and getter
function dataFromRest($http, $q) {

    var deferred = $q.defer();
    var vm = {};

    vm.PostData = PostData;
    vm.GetData = GetData;

    return vm;

    function PostData(objectData, url) {
        $http({
            method: "POST",
            url: url,
            data: objectData,
            headers: "content-Type : application/json"
        }).then(function (response) {

            var res = response;
            deferred.resolve(res);

        }, function (response) {
            var res = response;
            deferred.resolve(res);
        });

        return deferred.promise;
    }

    function GetData(url) {
        return $http({
            method: "GET",
            url: url,
            headers: {},
        }).then(function (response) {
            return response;
        })
    }
};

// Translate Config
function translateConfig($translateProvider) {

    // $translateProvider
    //   .translations("en", { // Define all menu elements
    //     DASHBOARD: "Dashboard", GRAPHS: "Graphs", MAILBOX: "Mailbox", WIDGETS: "Widgets", METRICS: "Metrics", FORMS: "Forms", APPVIEWS: "App views", OTHERPAGES: "Other pages", UIELEMENTS: "UI elements", MISCELLANEOUS: "Miscellaneous", GRIDOPTIONS: "Grid options", TABLES: "Tables", COMMERCE: "E-commerce", GALLERY: "Gallery", MENULEVELS: "Menu levels", ANIMATIONS: "Animations", LANDING: "Landing page", LAYOUTS: "Layouts", // Define some custom text
    //     WELCOME: "Welcome Amelia", MESSAGEINFO: "You have 42 messages and 6 notifications.", SEARCH: "Search for something...", DEMO: 'Internationalization (sometimes shortened to "I18N , meaning "I - eighteen letters -N") is the process of planning and implementing products and services so that they can easily be adapted to specific local languages and cultures, a process called localization . The internationalization process is sometimes called translation or localization enablement .', //Defin Menus
    //     X_NET: "X-NNNNs" })
    //   .translations("es", { // Define all menu elements
    //     DASHBOARD: "Salpicadero", GRAPHS: "Gráficos", MAILBOX: "El correo", WIDGETS: "Widgets", METRICS: "Métrica", FORMS: "Formas", APPVIEWS: "Vistas app", OTHERPAGES: "Otras páginas", UIELEMENTS: "UI elements", MISCELLANEOUS: "Misceláneo", GRIDOPTIONS: "Cuadrícula", TABLES: "Tablas", COMMERCE: "E-comercio", GALLERY: "Galería", MENULEVELS: "Niveles de menú", ANIMATIONS: "Animaciones", LANDING: "Página de destino", LAYOUTS: "Esquemas", // Define some custom text
    //     WELCOME: "Bienvenido Amelia", MESSAGEINFO: "Usted tiene 42 mensajes y 6 notificaciones.", SEARCH: "Busca algo ...", DEMO: 'Internacionalización (a veces abreviado como "I18N, que significa" I - dieciocho letras N ") es el proceso de planificación e implementación de productos y servicios de manera que se pueden adaptar fácilmente a las lenguas y culturas locales específicas, un proceso llamado localización El proceso de internacionalización. a veces se llama la traducción o la habilitación de localización.' })


    $translateProvider.preferredLanguage("ar");
    $translateProvider.registerAvailableLanguageKeys(['en', 'ar'], {
        'en-*': 'en',
        'ar-*': 'ar'
    });
    $translateProvider.useStaticFilesLoader({
        prefix: '',
        suffix: '.json'
    });
}


/**
 *  How To Use...
 *  define object with inputs name to validate it , then call it from controller like:
 *  $scope.exampleForm = validateForms.exampleForm.
 */
function validateFactory() {
    vm = {};

    vm.userForm = {
        ignore: ":hidden:not(.inputHidden)",
        onkeyup: function (element) {
            this.element(element);  // <- "eager validation"
        },
        onfocusout: function (element) {
            this.element(element);  // <- "eager validation"
        },

        rules: {
        }
    }

    vm.accountForm = {
        ignore: ":hidden:not(.inputHidden)",
        onkeyup: function (element) {
            this.element(element);  // <- "eager validation"
        },
        onfocusout: function (element) {
            this.element(element);  // <- "eager validation"
        },

        rules: {
            inputItem: "required",
            inputItem2: "required",
            accountType: "required",
            name: {
                required: true,
            },
            email: {
                email: true,
            },
            password: "required",
            username: "require",
            mobile1: {
                required: true,
                mobileEG: true
            },
            mobile2: "mobileEG",
            mobile3: "mobileEG",
            address: "required"


        }
    }

    vm.itemForm = {
        ignore: ":hidden:not(.inputHidden)",
        onkeyup: function (element) {
            this.element(element);  // <- "eager validation"
        },
        onfocusout: function (element) {
            this.element(element);  // <- "eager validation"
        },

        rules: {
            categoryInput: "required",
            itemName: "required",
            globalId: {
                required: true,
            },
            globalBarcode: {
                required: true,
            }
        }
    }

    vm.invoiceItemForm = {
        ignore: ":hidden:not(.inputHidden)",
        onkeyup: function (element) {
            this.element(element);  // <- "eager validation"
        },
        onfocusout: function (element) {
            this.element(element);  // <- "eager validation"
        },

        rules: {
            hiddenItem: "required",
            item: {
                required: true,
            },
            quantity: {
                required: true,
                digits: true,
                min: 1,
            },
            discount:{
                min: 0,
                max: 100
            }
        }

    }

    vm.invoiceItem2Form = {
        ignore: ":hidden:not(.inputHidden)",
        onkeyup: function (element) {
            this.element(element);  // <- "eager validation"
        },
        onfocusout: function (element) {
            this.element(element);  // <- "eager validation"
        },
        rules: {
            hiddenItem: "required",
            item: {
                required: true,
            },
            quantity: {
                required: true,
                digits: true,
                min: 1
            },
            discount: {
                min: 0,
                max: 100
            }
        }

    }

    vm.invoiceForm = {
        ignore: ":hidden:not(.inputHidden)",
        onkeyup: function (element) {
            this.element(element);  // <- "eager validation"
        },
        onfocusout: function (element) {
            this.element(element);  // <- "eager validation"
        },

        rules: {
            paymentTypeInput: "required",
            accountInput: "required",
            tempInvoice: "required"
        }

    };
    vm.discountTypeForm = {
        ignore: ":hidden:not(.inputHidden)",
        onkeyup: function (element) {
            this.element(element);  // <- "eager validation"
        },
        onfocusout: function (element) {
            this.element(element);  // <- "eager validation"
        },

        rules: {
            name: "required"
        }

    };

    return vm;
};