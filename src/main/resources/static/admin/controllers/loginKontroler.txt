app.controller('LoginCtrl',['AuthService','$scope','$http','$state',
    '$localStorage', function(AuthService, $scope, $http, $state, $localStorage){
        $scope.error = null;
        $scope.login = function() {
            $scope.error = null;
            AuthService.login($scope.userName, $scope.password).then(function(token) {
                // store username and token in local storage to keep user logged
                // in between page refreshes
                $localStorage.token = token;
                // add jwt token to auth header for all requests made by the $http
                // service
                $http.defaults.headers.common.Authorization = 'Bearer ' + token;
                //$scope.checkRoles();
                $state.go('home');
            },function(error){
                $scope.error = error;
                $scope.userName = '';
                $scope.password = '';
            });
        }
        $scope.logout = function() {
            $scope.userName = '';
            $localStorage.token = null;
            $http.defaults.headers.common.Authorization = '';
            $scope.password = '';
            $state.go('login');
        }
        $scope.loggedIn = function() {
            return $localStorage.token !== null;
        }
    }]);

