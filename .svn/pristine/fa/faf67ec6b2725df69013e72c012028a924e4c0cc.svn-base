app.controller('LoginCtrl', ['AuthService','$scope','$http','$state','$localStorage',
                                                function(AuthService, $scope, $http, $state, $localStorage) {
                                                    $scope.error = null;
                                                    //$scope.roleUser = false;
                                                    //$scope.roleAdmin = false;
                                                    //$scope.roleFoo = false;

                                                    $scope.login = function() {
                                                        $scope.error = null;

                                                        if ($scope.userName != null && $scope.userName !== ''){
                                                            if ($scope.password  != null && $scope.password !== ''){
                                                                AuthService.login($scope.userName, $scope.password).then(function(token) {
                                                                    // store username and token in local storage to keep user logged in between page refreshes
                                                                    $localStorage.token = token;

                                                                    // add jwt token to auth header for all requests made by the $http service
                                                                    $http.defaults.headers.common.Authorization = 'Bearer ' + token;
                                                                    //$scope.checkRoles();

                                                                    $state.go('home');
                                                                    alert("Welcome" + ' ' + $scope.userName + '!');
                                                                },
                                                                function(error){
                                                                    $scope.error = error;
                                                                    $scope.userName = '';
                                                                    $scope.password = '';
                                                                    alert("Unijeli ste pogrešne podatke!");
                                                                });
                                                            }
                                                        }
                                                    }

                                                    /*$scope.checkRoles = function() {
                                                        AuthService.hasRole('user').then(function(user) {$scope.roleUser = user});
                                                        AuthService.hasRole('admin').then(function(admin) {$scope.roleAdmin = admin});
                                                        AuthService.hasRole('foo').then(function(foo) {$scope.roleFoo = foo});
                                                    }*/

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
                                                } ]);