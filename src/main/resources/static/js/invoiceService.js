
app

  // The default logo for the invoice
  .constant("DEFAULT_LOGO","img/logo.png")

  // The invoice displayed when the user first uses the app
  .constant("DEFAULT_INVOICE", {
    items: [{ qty: 10, description: "0", cost: "0" }]
  })

  // Service for accessing local storage
  .service("LocalStorage", [
    function() {
      var Service = {};

      // Returns true if there is a logo stored
      var hasLogo = function() {
        return !!localStorage["logo"];
      };

      // Returns a stored logo (false if none is stored)
      Service.getLogo = getLogo;
      function getLogo() {
        if (hasLogo()) {
          return localStorage["logo"];
        } else {
          return false;
        }
      }

      Service.setLogo = setLogo;
      function setLogo(logo) {
        localStorage["logo"] = logo;
      }

      // Checks to see if an invoice is stored
      var hasInvoice = function() {
        return !(
          localStorage["invoice"] == "" || localStorage["invoice"] == null
        );
      };

      // Returns a stored invoice (false if none is stored)
      Service.getInvoice = getInvoice;
      function getInvoice() {
        if (hasInvoice()) {
          return JSON.parse(localStorage["invoice"]);
        } else {
          return false;
        }
      }

      Service.setInvoice = setInvoice;
      function setInvoice(invoice) {
        localStorage["invoice"] = JSON.stringify(invoice);
      }

      // Clears a stored logo
      Service.clearLogo = clearLogo;
      function clearLogo() {
        localStorage["logo"] = "";
      }

      // Clears a stored invoice
      Service.clearinvoice = clearinvoice;
      function clearinvoice() {
        localStorage["invoice"] = "";
      }

      // Clears all local storage
      Service.clear = clear;
      function clear() {
        localStorage["invoice"] = "";
        Service.clearLogo();
      }

      return Service;
    }
  ]);

  