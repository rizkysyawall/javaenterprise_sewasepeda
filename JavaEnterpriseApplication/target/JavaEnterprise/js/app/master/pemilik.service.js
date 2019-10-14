(function() {

	var services = angular.module('je-master-pemilik-service', [ 'ngResource' ]);

	
	services.factory('PemilikListFactory', [ '$resource', function($resource) {

		return $resource('service/master/pemilik', {}, {
			query : {
				method : 'GET',
				isArray : false
			},
			create : {
				method : 'POST'
			}
		});

	} ]);
	
	services.factory('PemilikEditFactory', [ '$resource', function($resource) {

		return $resource('service/master/pemilik/:id', {}, {
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