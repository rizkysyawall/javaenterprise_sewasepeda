(function () {

    var services = angular.module('je-transaction-pelangganSepeda-service', ['ngResource']);

    services.factory('TrsepedaListFactory', ['$resource', function ($resource) {

            return $resource('service/transaksi/trsewasepeda', {}, {
                query: {
                    method: 'GET',
                    isArray: false
                },
                create: {
                    method: 'POST'
                }
            });

        }]);

    services.factory('TrsepedaEditFactory', ['$resource', function ($resource) {

            return $resource('service/transaksi/trsewasepeda/:id', {}, {
                show: {
                    method: 'GET',
                    params: {id: '@id'}
                },
                update: {
                    method: 'PUT',
                    params: {id: '@id'}
                },
                remove: {
                    method: 'DELETE',
                    params: {id: '@id'}
                }

            });
        }]);

})();