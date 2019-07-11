var app = angular.module('sveucilisnaAdmin');


/* Define the Repository that interfaces with Restangular */
app.factory('UserRepository', ['Restangular', 'AbstractRepository',
  function (restangular, AbstractRepository) {

    function UserRepository() {
      AbstractRepository.call(this, restangular, 'users');
    }

    AbstractRepository.extend(UserRepository);
    return new UserRepository();
  }
]);

/* Here the controllers are defines */
app.controller('UsersListCtrl', function ($scope, UserRepository) {
  $scope.users = UserRepository.getList();
  $scope.delete = function (data) {
    if(window.confirm('Are you sure?')) {
      UserRepository.remove(data).then(function () {
          $scope.users = UserRepository.getList();
        });
    }
  };
});

app.controller('UsersItemCtrl', function ($scope, $stateParams, UserRepository) {
  $scope.user = UserRepository.get($stateParams.id).then(function (data) {
    $scope.user = data;
  });
});

app.controller('UsersEditCtrl', function ($scope, $stateParams, $location, UserRepository) {
  $scope.user = UserRepository.get($stateParams.id).then(function (data) {
    $scope.user = data;
  });
  $scope.save = function () {
    $scope.user.put().then(function () {
      $location.path('/users/' + $stateParams.id);
    });
  };
});

app.controller('UsersAddCtrl', function ($scope, $location, UserRepository) {
  $scope.save = function () {
    UserRepository.create($scope.user).then(function () {
      $location.path('/users');
    });
  };
});

/* Below are the states that are used */
app.config(['$stateProvider',
  function ($stateProvider) {

    $stateProvider
    .state('users', {
      abstract: true,
      url: '/users',
      templateUrl: 'admin/views/users/main.html'
 })

    .state('users.list', {
      url: '',
      templateUrl: 'admin/views/users/list.html',
      controller: 'UsersListCtrl'
 })

    .state('users.add', {
      url: '/add',
      templateUrl: 'admin/views/users/edit.html',
      controller: 'UsersAddCtrl'
 })

    .state('users.edit', {
      url: '/edit/{id}',
      templateUrl: 'admin/views/users/edit.html',
      controller: 'UsersEditCtrl'
 })

    .state('users.item', {
      url: '/{id}',
      templateUrl: 'admin/views/users/item.html',
      controller: 'UsersItemCtrl'
 });
  }
]);