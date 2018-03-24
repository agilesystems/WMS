/**
 * INSPINIA - Responsive Admin Theme
 *
 */
var app = angular.module("wms", [
  "ui.router", // Routing
  "oc.lazyLoad", // ocLazyLoad
  "ui.bootstrap", // Ui Bootstrap
  "pascalprecht.translate", // Angular Translate
  "ngIdle", // Idle timer
  "ngAnimate",
  "ui.select",
  "ngSanitize", // ngSanitize
  "ngMessages",
  "AngularPrint",
  "moment-picker",
  "LocalStorageModule",
  "ngValidate",
  "toaster",
  "ngTable"
    ]);
var server="http://localhost:8080/";
var $routeProviderReference;

// Other libraries are loaded dynamically in the config.js file using the library ocLazyLoad