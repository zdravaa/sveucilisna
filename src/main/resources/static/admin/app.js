var app = angular.module('sveucilisnaAdmin', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'restangular',
  'ui.router',
  'ngProgress',
  'ngStorage'
]);


// the following method will run at the time of initializing the module. That
// means it will run only one time.
app.run(function($rootScope, $state, $localStorage, $http) {
    // keep user logged in after page refresh
    if ($localStorage.token) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;
    }
    //For implementing the authentication with ui-router we need to listen the
    //state change. For every state change the ui-router module will broadcast
    //the '$stateChangeStart'.
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
        // checking the user is logged in or not
        if (!$localStorage.token) {
            // To avoiding the infinite looping of state change we have to add a
            // if condition.
            if (toState.name != 'login') {
                event.preventDefault();
                $state.go('login');
            }
        } else {
            // checking the user is authorized to view the states
            if (toState.name == 'login') {
                event.preventDefault();
                $state.go('home');
            }
        }
    });
});


app.config(function (RestangularProvider) {
  // Server:
 //RestangularProvider.setBaseUrl('/sveucilisna/api/v1');
 // Local ( bez prefiksa ):
  RestangularProvider.setBaseUrl('/api/v1');
});


app.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'admin/views/main.html',
                controller: 'MainCtrl'
            }).state('emailTemplate', {
            url: '/emailTemplate',
            templateUrl: 'emailTemplate.html'
        }).state('login',{
            url:'/admin-panel/login',
            templateUrl:'login.html',
            controller:'LoginCtrl'
        });


    }
]);


