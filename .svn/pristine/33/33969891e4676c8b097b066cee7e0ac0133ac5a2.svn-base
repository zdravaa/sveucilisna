var app = angular.module('sveucilisnaPublic');


/* Define the Repository that interfaces with Restangular */
app.factory('TeamRepository', ['Restangular', 'AbstractRepository',
  function (restangular, AbstractRepository) {

    function TeamRepository() {
      AbstractRepository.call(this, restangular, 'teams');
    }

    AbstractRepository.extend(TeamRepository);
    return new TeamRepository();
  }
]);

/* Here the controllers are defines */
app.controller('TeamsListCtrl', function ($scope, TeamRepository) {
  $scope.teams = TeamRepository.getList();
  $scope.delete = function (data) {
    if(window.confirm('Are you sure?')) {
      TeamRepository.remove(data).then(function () {
          $scope.teams = TeamRepository.getList();
        });
    }
  };
});

app.controller('TeamsItemCtrl', function ($scope, $stateParams, TeamRepository) {
  $scope.team = TeamRepository.get($stateParams.id).then(function (data) {
    $scope.team = data;
  });
});

app.controller('TeamsEditCtrl', function ($scope, $stateParams, $location, TeamRepository) {
  $scope.team = TeamRepository.get($stateParams.id).then(function (data) {
    $scope.team = data;
  });
  $scope.save = function () {
    $scope.team.put().then(function () {
      $location.path('/teams/' + $stateParams.id);
    });
  };
});

app.controller('TeamsAddCtrl', function ($scope, $location, TeamRepository) {
  $scope.save = function () {
    TeamRepository.create($scope.team).then(function () {
      $location.path('/teams');
    });
  };
});

/* Below are the states that are used */
app.config(['$stateProvider',
  function ($stateProvider) {

    $stateProvider
    .state('teams', {
      abstract: true,
      url: '/teams',
      templateUrl: 'public/views/teams/main.html'
 })

    .state('teams.list', {
      url: '',
      templateUrl: 'public/views/teams/list.html',
      controller: 'TeamsListCtrl'
 })

    .state('teams.add', {
      url: '/add',
      templateUrl: 'public/views/teams/edit.html',
      controller: 'TeamsAddCtrl'
 })

    .state('teams.edit', {
      url: '/edit/{id}',
      templateUrl: 'public/views/teams/edit.html',
      controller: 'TeamsEditCtrl'
 })

    .state('teams.item', {
      url: '/{id}',
      templateUrl: 'public/views/teams/players-list.html',
      controller: 'TeamsItemCtrl'
 });
  }
]);