var app = angular.module('sveucilisnaPublic');


/* Define the Repository that interfaces with Restangular */
app.factory('PlayerRepository', ['Restangular', 'AbstractRepository',
  function (restangular, AbstractRepository) {

    function PlayerRepository() {
      AbstractRepository.call(this, restangular, 'players');
    }

    AbstractRepository.extend(PlayerRepository);
    return new PlayerRepository();
  }
]);

/* Here the controllers are defines */
app.controller('PlayersListCtrl', function ($scope, PlayerRepository) {
  $scope.players = PlayerRepository.getList();
  $scope.delete = function (data) {
    if(window.confirm('Are you sure?')) {
      PlayerRepository.remove(data).then(function () {
          $scope.players = PlayerRepository.getList();
        });
    }
  };
});

app.controller('PlayersItemCtrl', function ($scope, $stateParams, PlayerRepository) {
  $scope.player = PlayerRepository.get($stateParams.id).then(function (data) {
    $scope.player = data;
  });
});

app.controller('PlayersEditCtrl', function ($scope, $stateParams, $location, PlayerRepository) {
  $scope.player = PlayerRepository.get($stateParams.id).then(function (data) {
    $scope.player = data;
  });
  $scope.save = function () {
    $scope.player.put().then(function () {
      $location.path('/players/' + $stateParams.id);
    });
  };
});

app.controller('PlayersAddCtrl', function ($scope, $location, PlayerRepository) {
  $scope.save = function () {
    PlayerRepository.create($scope.player).then(function () {
      $location.path('/players');
    });
  };
});

/* Below are the states that are used */
app.config(['$stateProvider',
  function ($stateProvider) {

    $stateProvider
    .state('players', {
      abstract: true,
      url: '/players',
      templateUrl: 'public/views/players/main.html'
 })

    .state('players.list', {
      url: '',
      templateUrl: 'public/views/players/list.html',
      controller: 'PlayersListCtrl'
 })

    .state('players.add', {
      url: '/add',
      templateUrl: 'public/views/players/edit.html',
      controller: 'PlayersAddCtrl'
 })

    .state('players.edit', {
      url: '/edit/{id}',
      templateUrl: 'public/views/players/edit.html',
      controller: 'PlayersEditCtrl'
 })

    .state('players.item', {
      url: '/{id}',
      templateUrl: 'public/views/players/item.html',
      controller: 'PlayersItemCtrl'
 });
  }
]);