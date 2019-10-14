(function () {

    var services = angular.module('je-master-sepeda-service', ['ngResource']);

    services.factory('SepedaListFactory', ['$resource', function ($resource) {

            return $resource('service/master/sepeda', {}, {
                query: {
                    method: 'GET',
                    isArray: false
                },
                create: {
                    method: 'POST'
                }
            });

        }]);

    services.factory('SepedaEditFactory', ['$resource', function ($resource) {

            return $resource('service/master/sepeda/:id', {}, {
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