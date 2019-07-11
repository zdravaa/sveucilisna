var app = angular.module('sveucilisnaAdmin');


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


app.controller('getAllPlayers', function($scope, $http) {
    $http.get("http://localhost:8081/sveucilisna/players")
    .success(function (players) {$scope.players = players;});
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


app.controller('PlayersAddCtrl', function ($scope, $location, PlayerRepository, TeamRepository) {
$scope.teams = TeamRepository.getList();
  $scope.save = function () {
  $scope.player.team = JSON.parse($scope.player.team);
 console.log($scope.player);
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
      templateUrl: 'admin/views/players/main.html'
 })

    .state('players.list', {
      url: '',
      templateUrl: 'admin/views/players/list.html',
      controller: 'PlayersListCtrl'
 })

    .state('players.add', {
      url: '/add',
      templateUrl: 'admin/views/players/add.html',
      controller: 'PlayersAddCtrl'
 })

    .state('players.edit', {
      url: '/edit/{id}',
      templateUrl: 'admin/views/players/edit.html',
      controller: 'PlayersEditCtrl'
 })

    .state('players.item', {
      url: '/{id}',
      templateUrl: 'admin/views/players/item.html',
      controller: 'PlayersItemCtrl'
 });
  }
]);