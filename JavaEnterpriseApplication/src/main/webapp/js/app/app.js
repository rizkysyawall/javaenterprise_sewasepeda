(function () {

    var app = angular.module('je', ['ngRoute', 'datePicker', 'cgBusy', 'je-paging',
        'je-main-workspace-controller',
        'je-master-pelanggan-controller',
        'je-master-pemilik-controller',
        'je-master-sepeda-controller',
        'je-transaction-pelangganSepeda-controller',
        'angularUtils.directives.dirPagination']);

    app.config(function ($routeProvider) {
        $routeProvider
                .when("/", {
                    templateUrl: "template/main/home.html"
                })              
                .when("/master/pelanggan", {
                    templateUrl: "template/master/pelanggan_list.html",
                    controller: "PelangganListController"
                })
                .when("/master/pelanggan/new", {
                    templateUrl: "template/master/pelanggan_save.html",
                    controller: "PelangganCreateController"
                })
                .when("/master/pelanggan/:id/edit", {
                    templateUrl: "template/master/pelanggan_save.html",
                    controller: "PelangganEditController"
                })
                .when("/master/pelanggan/:id/detail", {
                    templateUrl: "template/master/pelanggan_detail.html",
                    controller: "PelangganDetailController"
                })
                .when("/master/pemilik", {
                    templateUrl: "template/master/pemilik_list.html",
                    controller: "PemilikListController"
                })
                .when("/master/pemilik/new", {
                    templateUrl: "template/master/pemilik_save.html",
                    controller: "PemilikCreateController"
                })
                .when("/master/pemilik/:id/edit", {
                    templateUrl: "template/master/pemilik_save.html",
                    controller: "PemilikEditController"
                })
                .when("/master/pemilik/:id/detail", {
                    templateUrl: "template/master/pemilik_detail.html",
                    controller: "PemilikDetailController"
                })
                .when("/master/sepeda", {
                    templateUrl: "template/master/sepeda_list.html",
                    controller: "SepedaListController"
                })
                .when("/master/sepeda/new", {
                    templateUrl: "template/master/sepeda_save.html",
                    controller: "SepedaCreateController"
                })
                .when("/master/sepeda/:id/edit", {
                    templateUrl: "template/master/sepeda_save.html",
                    controller: "SepedaEditController"
                })
                .when("/master/sepeda/:id/detail", {
                    templateUrl: "template/master/sepeda_detail.html",
                    controller: "SepedaDetailController"
                })
                .when("/transaction/pelangganSepeda", {
                    templateUrl: "template/transaction/pelanggan_sepeda_list.html",
                    controller: "TrsepedaListController"
                })
                .when("/transaction/pelangganSepeda/new", {
                    templateUrl: "template/transaction/pelanggan_sepeda_save.html",
                    controller: "TrsepedaCreateController"
                })
                .when("/transaction/pelangganSepeda/:id/edit", {
                    templateUrl: "template/transaction/pelanggan_sepeda_save.html",
                    controller: "TrsepedaEditController"
                })
                .when("/transaction/pelangganSepeda/:id/detail", {
                    templateUrl: "template/transaction/pelanggan_sepeda_detail.html",
                    controller: "TrsepedaDetailController"
                });
    });

    app.filter('trusted', ['$sce', function ($sce) {
            return function (url) {
                return $sce.trustAsResourceUrl(url);
            };
        }]);

})();



