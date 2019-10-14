(function() {

	var services = angular.module('je-master-pelanggan-service', [ 'ngResource' ]);

	
	services.factory('PelangganListFactory', [ '$resource', function($resource) {

		return $resource('service/master/pelanggan', {}, {
			query : {
				method : 'GET',
				isArray : false
			},
			create : {
				method : 'POST'
			}
		});

	} ]);
	
	services.factory('PelangganEditFactory', [ '$resource', function($resource) {

		return $resource('service/master/pelanggan/:id', {}, {
			show : {
				method : 'GET',
				params: {id: '@id'}
			},
			update : {
				method : 'PUT',
				params :{id: '@id'}
			},
			remove : {
				method : 'DELETE',
				params :{id: '@id'}
			}
		});

	} ]);
	
})();