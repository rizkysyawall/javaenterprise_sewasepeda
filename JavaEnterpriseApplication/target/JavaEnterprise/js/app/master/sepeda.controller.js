(function () {
    var app = angular.module('je-master-sepeda-controller', [
        'je-master-sepeda-service',
        'je-master-pemilik-service',
        'ui.bootstrap', 'dialogs']);

    app.controller('SepedaListController', ['$scope', '$location',
        '$dialogs', 'SepedaListFactory','SepedaEditFactory',
        function ($scope, $location, $dialogs, SepedaListFactory, SepedaEditFactory) {

            $scope.currentPage = 1;
            $scope.totalSepeda = 0;
            $scope.pageSize = 10;

            $scope.pagination = {
                current: 1
            };
            //ordering
            $scope.predicate = 'name';
            $scope.reverse = false;

            $scope.pageChanged = function (newPage) {
                $scope.gridPromise = SepedaListFactory.query({
                    activePage: newPage,
                    order: $scope.predicate + "-" + ($scope.reverse ? "desc" : "asc")
                }, function (data) {
                    $scope.sepedaList = data.list;
                    $scope.totalSepeda = data.total;
                });
            };
            $scope.pageChanged(1);

            $scope.search = function () {
                $scope.currentPage = 1;
                $scope.pageChanged(1);
            };
            $scope.create = function () {
                $location.path('/master/sepeda/new');
            };

            $scope.remove = function (sepeda) {
                dlg = $dialogs.confirm('Konfirmasi', 'Apakah anda ingin hapus Sepeda : ' + sepeda.merek);
                dlg.result.then(function (btn) {
                    SepedaEditFactory.remove({id: sepeda.id}, function (data) {
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
            $scope.edit = function (sepeda) {
                $location.path('/master/sepeda/' + sepeda.id + '/edit');
            };

            $scope.detail = function (sepeda) {
                $location.path('/master/sepeda/' + sepeda.id + '/detail');
            };

        }]);

    app.controller('SepedaCreateController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'SepedaListFactory',
        'PemilikListFactory',
        function ($scope, $routeParams, $location, $dialogs, SepedaListFactory, PemilikListFactory) {

            $scope.title = "Buat Baru Sepeda-Pemilik";
            $scope.isEdit = false;

            $scope.kategoriList = [
                {"name": "Anak"},
                {"name": "Dewasa"},
                {"name": "Remaja"},
                {"name": "General"},
            ];
            $scope.tipeList = [
                {"name": "Gunung"},
                {"name": "Santai"},
                {"name": "Mini"},
                {"name": "DoubleSeat"},
            ];
            PemilikListFactory.query({}, function (data) {
                $scope.pemilikList = data.list;
            });


            $scope.sepeda = {

            };



            $scope.save = function () {
                SepedaListFactory.create({
                    sepeda: $scope.sepeda
                }, function (data) {
                    //notif($dialogs, data.status, 'Simpan');
                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Disimpan');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/master/sepeda');
                });

            };

            $scope.cancel = function () {
                $location.path('/master/sepeda');
            };

        }]);


    app.controller('SepedaEditController', [
        '$scope',
        '$routeParams',
        '$location',
        '$dialogs',
        'SepedaEditFactory',
        'PemilikListFactory',
        function ($scope, $routeParams, $location, $dialogs, SepedaEditFactory,
                PemilikListFactory) {

            $scope.title = "Ubah Sepeda-Pemilik";
            $scope.isEdit = true;

            $scope.kategoriList = [
                {"name": "Anak"},
                {"name": "Dewasa"},
                {"name": "Remaja"},
                {"name": "General"},
            ];
            $scope.tipeList = [
                {"name": "Gunung"},
                {"name": "Santai"},
                {"name": "Mini"},
                {"name": "DoubleSeat"},
            ];
            PemilikListFactory.query({}, function (data) {
                $scope.pemilikList = data.list;
            });


            SepedaEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.sepeda = data.sepeda;
            });

            $scope.save = function () {
                SepedaEditFactory.update({
                    id: $scope.sepeda.id,
                    sepeda: $scope.sepeda
                }, function (data) {
                    if (data.status == "OK") {
                        dlg = $dialogs.notify('Informasi', 'Data Sukses Disimpan');
                    } else {
                        dlg = $dialogs.error('Data Gagal Disimpan :' + data.status);
                    }
                    $location.path('/master/sepeda');
                });

            };

            $scope.cancel = function () {
                $location.path('/master/sepeda');
            };

        }]);

    app.controller('SepedaDetailController', [
        '$scope',
        '$routeParams',
        '$location',
        'SepedaEditFactory',
        function ($scope, $routeParams, $location, SepedaEditFactory) {

            $scope.title = "Info Detail Sepeda-Pemilik";

            SepedaEditFactory.show({
                id: $routeParams.id
            }, function (data) {
                $scope.sepeda = data.sepeda;
            });


            $scope.cancel = function () {
                $location.path('/master/sepeda');
            };

        }]);





})();
