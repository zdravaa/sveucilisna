var app = angular.module('sveucilisnaPublic');


/* Define the Repository that interfaces with Restangular */
app.factory('UpcomingFixturesRepository', ['Restangular', 'AbstractRepository',
  function (restangular, AbstractRepository) {

    function UpcomingFixturesRepository() {
      AbstractRepository.call(this, restangular, 'upcomingFixtures');
    }

    AbstractRepository.extend(UpcomingFixturesRepository);
    return new UpcomingFixturesRepository();
  }
]);

/* Here the controllers are defines */
app.controller('UpcomingFixturesListCtrl', function ($scope, UpcomingFixturesRepository) {
  $scope.upcomingFixtures = UpcomingFixturesRepository.getList();
  console.log($scope.upcomingFixtures)
  $scope.delete = function (data) {
    if(window.confirm('Are you sure?')) {
      UpcomingFixturesRepository.remove(data).then(function () {
          $scope.upcomingFixtures = UpcomingFixturesRepository.getList();
        });
    }
  };
});

app.controller('UpcomingFixturesItemCtrl', function ($scope, $stateParams, UpcomingFixturesRepository) {
  $scope.upcomingFixtures = UpcomingFixturesRepository.get($stateParams.id).then(function (data) {
    $scope.upcomingFixtures = data;
  });
});

app.controller('UpcomingFixturesEditCtrl', function ($scope, $stateParams, $location, UpcomingFixturesRepository) {
  $scope.upcomingFixtures = UpcomingFixturesRepository.get($stateParams.id).then(function (data) {
    $scope.upcomingFixtures = data;
  });
  $scope.save = function () {
    $scope.upcomingFixtures.put().then(function () {
      $location.path('/upcomingFixtures/' + $stateParams.id);
    });
  };
});

app.controller('UpcomingFixturesAddCtrl', function ($scope, $location, UpcomingFixturesRepository, TeamRepository) {
$scope.teams = TeamRepository.getList();
  $scope.save = function () {
    UpcomingFixturesRepository.create($scope.upcomingFixtures).then(function () {
      $location.path('/upcomingFixtures');
    });
  };
});


/* Below are the states that are used */
app.config(['$stateProvider',
  function ($stateProvider) {

    $stateProvider
    .state('upcomingFixtures', {
      abstract: true,
      url: '/upcomingFixtures',
      templateUrl: 'admin/views/upcomingFixtures/main.html'
 })

    .state('upcomingFixtures.list', {
      url: '',
      templateUrl: 'admin/views/upcomingFixtures/list.html',
      controller: 'UpcomingFixturesListCtrl'
 })

    .state('upcomingFixtures.add', {
      url: '/add',
      templateUrl: 'admin/views/upcomingFixtures/edit.html',
      controller: 'UpcomingFixturesAddCtrl'
 })

    .state('upcomingFixtures.edit', {
      url: '/edit/{id}',
      templateUrl: 'admin/views/upcomingFixtures/edit.html',
      controller: 'UpcomingFixturesEditCtrl'
 })

    .state('upcomingFixtures.item', {
      url: '/{id}',
      templateUrl: 'admin/views/upcomingFixtures/item.html',
      controller: 'UpcomingFixturesItemCtrl'
 });
  }
]);