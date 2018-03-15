app
  .config(function(localStorageServiceProvider) {
    localStorageServiceProvider
      .setPrefix("wms")
      .setStorageType("localStorage")
      .setNotify("true, true");
  })
  .factory("storageService", function(localStorageService) {
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
    function setStorage(key,val) {
      localStorageService.set(key, val);
    }
    
    //Get specific key only
    storage.getkey = getkey;
    function getkey(key) {
     return localStorageService.deriveKey(key);
    }
    //Get localstorage items length
    var getLength = localStorageService.length();

    //Get localstorage kyes
    
    var getKeys = localStorageService.keys();

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
     * 
     */
     storage.setSessionStorage = setSessionStorage;
     function setSessionStorage(key, val) {
       localStorageService.set(key, val, "sessionStorage");
     }
    return storage;
  });