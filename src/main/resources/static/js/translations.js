/**
 * INSPINIA - Responsive Admin Theme
 *
 */
function config($translateProvider) {

    $translateProvider
            .translations('en', {

                // Define all menu elements
                DASHBOARD: 'Dashboard',
                GRAPHS: 'Graphs',
                MAILBOX: 'Mailbox',
                WIDGETS: 'Widgets',
                METRICS: 'Metrics',
                FORMS: 'Forms',
                APPVIEWS: 'App views',
                OTHERPAGES: 'Other pages',
                UIELEMENTS: 'UI elements',
                MISCELLANEOUS: 'Miscellaneous',
                GRIDOPTIONS: 'Grid options',
                TABLES: 'Tables',
                COMMERCE: 'E-commerce',
                GALLERY: 'Gallery',
                MENULEVELS: 'Menu levels',
                ANIMATIONS: 'Animations',
                LANDING: 'Landing page',
                LAYOUTS: 'Layouts',

                // Define some custom text
                WELCOME: 'Welcome Amelia',
                MESSAGEINFO: 'You have 42 messages and 6 notifications.',
                SEARCH: 'Search for something...',
                DEMO: 'Internationalization (sometimes shortened to \"I18N , meaning \"I - eighteen letters -N\") is the process of planning and implementing products and services so that they can easily be adapted to specific local languages and cultures, a process called localization . The internationalization process is sometimes called translation or localization enablement .',


                //Defin Menus
                X_NET:'X-NNNNs'
            })
            .translations('es', {

                // Define all menu elements
                DASHBOARD: 'Salpicadero',
                GRAPHS: 'Gráficos',
                MAILBOX: 'El correo',
                WIDGETS: 'Widgets',
                METRICS: 'Métrica',
                FORMS: 'Formas',
                APPVIEWS: 'Vistas app',
                OTHERPAGES: 'Otras páginas',
                UIELEMENTS: 'UI elements',
                MISCELLANEOUS: 'Misceláneo',
                GRIDOPTIONS: 'Cuadrícula',
                TABLES: 'Tablas',
                COMMERCE: 'E-comercio',
                GALLERY: 'Galería',
                MENULEVELS: 'Niveles de menú',
                ANIMATIONS: 'Animaciones',
                LANDING: 'Página de destino',
                LAYOUTS: 'Esquemas',

                // Define some custom text
                WELCOME: 'Bienvenido Amelia',
                MESSAGEINFO: 'Usted tiene 42 mensajes y 6 notificaciones.',
                SEARCH: 'Busca algo ...',
                DEMO: 'Internacionalización (a veces abreviado como \"I18N, que significa\" I - dieciocho letras N \") es el proceso de planificación e implementación de productos y servicios de manera que se pueden adaptar fácilmente a las lenguas y culturas locales específicas, un proceso llamado localización El proceso de internacionalización. a veces se llama la traducción o la habilitación de localización.'
            })
            .translations("ar", {
      ADDAC: {
        ADDNEWACCOUNT: "اضافة حساب جديد",
        NAME: "الاسم: ",
        CODE: "الكود: ",
        ACTYPE: "نوع الحساب: ",
        EMAIL: "الايميل: ",
        PHONENO: "رقم الموبايل: ",
        ADDRS: "العنوان :",
        CITY: "المدينة: ",
        STATE: "المحافظة: ",
        EXTINFO: "معلومات اضافية: ",
        SAVE: "حفظ"
      },
      ADDUSER: {
          ADDNEWUSER:'اضافة مستخدم جديد: ',
          FNAME: 'الاسم الاول: ',
          LNAME:'الاسم الاخير: ',
          PHONENO:'رقم الموبايل: ',
          ADDRS:'العنوان: ',
          USERN:'اسم المستخدم: ',
          PASS:'كلمة السر: ',
          SELECTEROLE:'الصلاحيات: ',
          SAVE:'حفظ'
      },
      ADDINVO: {
        BRANCH:'الفرع',
        INVOTYPE:'نوع الفاتورة',
        CASHTYPE:'طريقة الدفع',
        REFERENCE:'المرجع',
        ACCOUNTTYPE:'نوع الحساب',
        INVODATE:'التاريخ',
        STORE:'المخزن',
        DISCOUNT:'الخصم',
        PRODUCT:'المنتج',
        QUANTITY:'الكمية',
        COST:'الكلفة',
        TOTAL:'المبلغ',
        SUBTOTAL:'المجموع',
        TAX:'الضريبة',
        GRANDTOTAL:'المبلغ الإجمالي',
        SAVE:'حفظ الفاتورة',
        PRINT:'طباعة',
        NEW:'فاتورة جديدة',
        VIEWMODE:'معاينة',
        EDITMODE:'تعديل',
        EDITLOGO:'تعديل الشعار',
        SHOWLOGO:'إظهار الشعار',
        HIDELOGO:'إخفاء الشعار'
      }
    });

  $translateProvider.preferredLanguage("ar");
}

app.config(config);
