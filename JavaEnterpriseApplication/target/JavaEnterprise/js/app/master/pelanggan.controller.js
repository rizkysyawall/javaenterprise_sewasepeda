(function () {

    var app = angular.module('je-master-pelanggan-controller', [
        'je-master-pelanggan-service',
        'ui.bootstrap', 'dialogs']);

    app.controller('PelangganListController', ['$scope', '$location',
        '$dialogs', 'PelangganListFactory', 'PelangganEditFactory',
        function ($scope, $location, $dialogs, PelangganListFactory, PelangganEditFactory) {

            $scope.currentPage = 1;
            $scope.totalPelanggan = 0;
            $scope.pageSize = 10;

            $scope.pagination = {
                current: 1
            };

            //ordering
            $scope.predicate = 'name';
            $scope.reverse = false;

            $scope.pageChanged = function (newPage) {
                $scope.gridPromise = PelangganListFactory.query({
                    activePage: newPage,
                    order: $scope.predicate + "-" + ($scope.reverse ? "desc" : "asc")
                }, function (data) {
                    $scope.pelangganList = data.list;
                    notif($dialogs, data.status, 'List');
                    $scope.totalPelanggan = data.total;
                });
            };
            $scope.pageChanged(1);

            $scope.search = function () {
                $scope.currentPage = 1;
                $scope.pageChanged(1);
            };

            $scope.create = function () {
                $location.path('/master/pelanggan/new');
            };

            $scope.remove = function (pelanggan) {
                dlg = $dialogs.confirm('Konfirmasi', 'Apakah anda ingin hapus Pelanggan : ' + pelanggan.nmpelanggan);
                dlg.result.then(function (btn) {
                    PelangganEditFactory.remove({id: pelanggan.id}, function (data) {
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

            $scope.edit = function (pelanggan) {
                $location.path('/master/pelanggan/' + pelanggan.id + '/edit');
            };

            $scope.detail = function (pelanggan) {
                $location.path('/master/pelanggan/' + pelanggan.id + '/detail');
            };

        }]);

    app.controller('PelangganCreateController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'PelangganListFactory',
        function ($scope, $routeParams, $location, $dialogs,
                PelangganListFactory) {

            $scope.title = "Buat Baru Pelanggan";
            $scope.isEdit = false;

            $scope.genderList = [
                {"name": "Pria"},
                {"name": "Wanita"},
            ];

            $scope.pelanggan = {

            };
            $scope.save = function () {
                PelangganListFactory.create({
                    pelanggan: $scope.pelanggan
                }, function (data) {

                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Disimpan');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/master/pelanggan');
                });

            };

            $scope.cancel = function () {
                $location.path('/master/pelanggan');
            };

        }]);


    app.controller('PelangganEditController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'PelangganEditFactory',
        'PelangganListFactory',
        function ($scope, $routeParams, $location, $dialogs, PelangganEditFactory,
                PelangganListFactory) {

            $scope.title = "Ubah Pelanggan";
            $scope.isEdit = true;

            $scope.genderList = [
                {"name": "Pria"},
                {"name": "Wanita"},
            ];

            PelangganEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.pelanggan = data.pelanggan;
            });

            $scope.save = function () {
                PelangganEditFactory.update({
                    id: $scope.pelanggan.id,
                    pelanggan: $scope.pelanggan
                }, function (data) {
                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Diubah');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/master/pelanggan');
                });

            };

            $scope.cancel = function () {
                $location.path('/master/pelanggan');
            };

        }]);

    app.controller('PelangganDetailController', [
        '$scope',
        '$routeParams',
        '$location',
        'PelangganEditFactory',
        function ($scope, $routeParams, $location, PelangganEditFactory) {

            $scope.title = "Info Detail Pelanggan";

            PelangganEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.pelanggan = data.pelanggan;
            });


            $scope.cancel = function () {
                $location.path('/master/pelanggan');
            };

        }]);

})();
