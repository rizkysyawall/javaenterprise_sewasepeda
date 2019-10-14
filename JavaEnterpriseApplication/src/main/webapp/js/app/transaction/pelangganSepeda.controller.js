(function () {
    var app = angular.module('je-transaction-pelangganSepeda-controller', [
        'je-transaction-pelangganSepeda-service',
        'je-master-pelanggan-service',
        'je-master-sepeda-service',
        'ui.bootstrap', 'dialogs']);

    app.controller('TrsepedaListController', ['$scope', '$location',
        '$dialogs', 'TrsepedaListFactory','TrsepedaEditFactory',
        function ($scope, $location, $dialogs, TrsepedaListFactory,TrsepedaEditFactory) {

            $scope.currentPage = 1;
            $scope.totalTrsepeda = 0;
            $scope.pageSize = 10;

            $scope.pagination = {
                current: 1
            };
            //ordering
            $scope.predicate = 'name';
            $scope.reverse = false;

            $scope.pageChanged = function (newPage) {
                $scope.gridPromise = TrsepedaListFactory.query({
                    activePage: newPage,
                    order: $scope.predicate + "-" + ($scope.reverse ? "desc" : "asc")
                }, function (data) {
                    $scope.trsepedaList = data.list;
                    $scope.totalTrsepeda = data.total;
                });
            };
            $scope.pageChanged(1);

            $scope.create = function () {
                $location.path('/transaction/pelangganSepeda/new');
            };

            $scope.remove = function (trsepeda) {
                dlg = $dialogs.confirm('Konfirmasi', 'Apakah anda ingin hapus Sepeda : ' + trsepeda.id);
                dlg.result.then(function (btn) {
                    TrsepedaEditFactory.remove({id: trsepeda.id}, function (data) {
                        if (data.status == "OK") {
                            dlg = $dialogs.notify('Informasi', 'Data Sukses Dihapus');
                        } else {
                            dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                        }
                        $scope.search();
                    });

                }, function (btn) {
                    //canceled
                });

            };

            $scope.edit = function (trsepeda) {
                $location.path('/transaction/pelangganSepeda/' + trsepeda.id + '/edit');
            };

            $scope.detail = function (trsepeda) {
                $location.path('/transaction/pelangganSepeda/' + trsepeda.id + '/detail');
            };

        }]);

    app.controller('TrsepedaCreateController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'TrsepedaListFactory',
        'PelangganListFactory',
        'SepedaListFactory',
        function ($scope, $routeParams, $location, $dialogs, TrsepedaListFactory, PelangganListFactory, SepedaListFactory) {

            $scope.title = "Buat Baru Pelanggan-Sepeda";
            $scope.isEdit = false;

            $scope.statusList = [
                {"name": "Active"},
                {"name": "In Active"}
            ];

            PelangganListFactory.query({}, function (data) {
                $scope.pelangganList = data.list;
            });
            SepedaListFactory.query({}, function (data) {
                $scope.sepedaList = data.list;
            });


            $scope.trsepeda = {

            };



            $scope.save = function () {
                TrsepedaListFactory.create({
                    trsepeda: $scope.trsepeda
                }, function (data) {
                    //notif($dialogs, data.status, 'Simpan');
                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Disimpan');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/transaction/pelangganSepeda');
                });

            };

            $scope.cancel = function () {
                $location.path('/transaction/pelangganSepeda');
            };

        }]);


        app.controller('TrsepedaEditController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'TrsepedaEditFactory',
        'PelangganListFactory',
        'SepedaListFactory',

        function ($scope, $routeParams, $location, $dialogs,TrsepedaEditFactory,PelangganListFactory,
                SepedaListFactory) {

            $scope.title = "Ubah Pelanggan-Sepeda";
            $scope.isEdit = true;

            PelangganListFactory.query({}, function (data) {
                $scope.pelangganList = data.list;
            });
            SepedaListFactory.query({}, function (data) {
                $scope.sepedaList = data.list;
            });

            TrsepedaEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.trsepeda = data.trsepeda;
            });

            $scope.save = function() {
                TrsepedaEditFactory.update({
                    id: $scope.trsepeda.id,
                    trsepeda: $scope.trsepeda
                }, function(data) {
                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Disimpan');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/transaction/pelangganSepeda');
                });

            };

            $scope.cancel = function () {
                $location.path('/transaction/pelangganSepeda');
            };

        }]);

    app.controller('TrsepedaDetailController', [
        '$scope',
        '$routeParams',
        '$location',
        'TrsepedaEditFactory',
        function ($scope, $routeParams, $location, TrsepedaEditFactory) {

            $scope.title = "Info Detail Pelanggan - Sepeda";

            TrsepedaEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.trsepeda = data.trsepeda;
            });


            $scope.cancel = function () {
                $location.path('/transaction/pelangganSepeda');
            };

        }]);





})();
