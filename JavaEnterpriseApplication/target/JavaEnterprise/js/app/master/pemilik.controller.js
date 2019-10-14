(function () {

    var app = angular.module('je-master-pemilik-controller', [
        'je-master-pemilik-service',
        'ui.bootstrap', 'dialogs']);

    app.controller('PemilikListController', ['$scope', '$location',
        '$dialogs', 'PemilikListFactory', 'PemilikEditFactory',
        function ($scope, $location, $dialogs, PemilikListFactory, PemilikEditFactory) {

            $scope.currentPage = 1;
            $scope.totalPemilik = 0;
            $scope.pageSize = 10;

            $scope.pagination = {
                current: 1
            };

            //ordering
            $scope.predicate = 'name';
            $scope.reverse = false;

            $scope.pageChanged = function (newPage) {
                $scope.gridPromise = PemilikListFactory.query({
                    activePage: newPage,
                    order: $scope.predicate + "-" + ($scope.reverse ? "desc" : "asc")
                }, function (data) {
                    $scope.pemilikList = data.list;
                    notif($dialogs, data.status, 'List');
                    $scope.totalPemilik = data.total;
                });
            };
            $scope.pageChanged(1);

            $scope.search = function () {
                $scope.currentPage = 1;
                $scope.pageChanged(1);
            };

            $scope.create = function () {
                $location.path('/master/pemilik/new');
            };

            $scope.remove = function (pemilik) {
                dlg = $dialogs.confirm('Konfirmasi', 'Apakah anda ingin hapus Pemilik : ' + pemilik.namapemilik);
                dlg.result.then(function (btn) {
                    PemilikEditFactory.remove({id: pemilik.id}, function (data) {
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

            $scope.edit = function (pemilik) {
                $location.path('/master/pemilik/' + pemilik.id + '/edit');
            };

            $scope.detail = function (pemilik) {
                $location.path('/master/pemilik/' + pemilik.id + '/detail');
            };

        }]);

    app.controller('PemilikCreateController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'PemilikListFactory',
        function ($scope, $routeParams, $location, $dialogs,
                PemilikListFactory) {

            $scope.title = "Buat Baru Pemilik";
            $scope.isEdit = false;

            $scope.genderList = [
                {"name": "Pria"},
                {"name": "Wanita"},
            ];

            $scope.pemilik = {

            };
            $scope.save = function () {
                PemilikListFactory.create({
                    pemilik: $scope.pemilik
                }, function (data) {

                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Disimpan');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/master/pemilik');
                });

            };

            $scope.cancel = function () {
                $location.path('/master/pemilik');
            };

        }]);


    app.controller('PemilikEditController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'PemilikEditFactory',
        'PemilikListFactory',
        function ($scope, $routeParams, $location, $dialogs, PemilikEditFactory,
                PemilikListFactory) {

            $scope.title = "Ubah Pemilik";
            $scope.isEdit = true;

            $scope.genderList = [
                {"name": "Pria"},
                {"name": "Wanita"},
            ];

            PemilikEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.pemilik = data.pemilik;
            });

            $scope.save = function () {
                PemilikEditFactory.update({
                    id: $scope.pemilik.id,
                    pemilik: $scope.pemilik
                }, function (data) {
                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Diubah');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/master/pemilik');
                });

            };

            $scope.cancel = function () {
                $location.path('/master/pemilik');
            };

        }]);

    app.controller('PemilikDetailController', [
        '$scope',
        '$routeParams',
        '$location',
        'PemilikEditFactory',
        function ($scope, $routeParams, $location, PemilikEditFactory) {

            $scope.title = "Info Detail Karyawan";

            PemilikEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.pemilik = data.pemilik;
            });


            $scope.cancel = function () {
                $location.path('/master/pemilik');
            };

        }]);

    /*
     function notif($dialogs, status, result) {
     
     switch (status) {
     case 'OK':
     if (result == 'Simpan') {
     dlg = $dialogs.notify('Informasi', 'Data Sukses Disimpan');
     } else if (result == 'Hapus') {
     dlg = $dialogs.notify('Informasi', 'Data Sukses Dihapus');
     }
     break;
     case '500':
     if (result == 'Simpan') {
     dlg = $dialogs.error('Data Gagal Disimpan');
     } else if (result == 'Hapus') {
     dlg = $dialogs.error('Data Gagal Dihapus');
     }
     break;
     }
     }
     */

})();
