var app = angular.module('sveucilisnaPublic');


/* Define the Repository that interfaces with Restangular */
app.factory('FixtureRepository', ['Restangular', 'AbstractRepository',
  function (restangular, AbstractRepository) {

    function FixtureRepository() {
      AbstractRepository.call(this, restangular, 'fixtures');
    }

    AbstractRepository.extend(FixtureRepository);
    return new FixtureRepository();
  }
]);

/* Here the controllers are defines */
app.controller('FixturesListCtrl', function ($scope, FixtureRepository, TeamRepository) {
  $scope.IsVisible = false;
  $scope.fixtures = FixtureRepository.getList();

  $scope.search = function () {
    $scope.IsVisible = true;
  }

  $scope.close1 = function () {
    $scope.fixture.homeTeamName = '';
  }

  $scope.purge = function () {
      $scope.fixture.id = null;
    }


    $scope.clear2 = function () {
          $scope.fixture.id = null;
        }


  $scope.delete = function (data) {
    if(window.confirm('Are you sure?')) {
      FixtureRepository.remove(data).then(function () {
          $scope.fixtures = FixtureRepository.getList();
          console.log($scope.fixtures);
        });
    }
  };
});

app.controller('FixturesItemCtrl', function ($scope, $stateParams, FixtureRepository) {
  $scope.fixture = FixtureRepository.get($stateParams.id).then(function (data) {
    $scope.fixture = data;
  });
});

app.controller('FixturesEditCtrl', function ($scope, $stateParams, $location, FixtureRepository) {
  $scope.fixture = FixtureRepository.get($stateParams.id).then(function (data) {
    $scope.fixture = data;
  });
  $scope.save = function () {
    $scope.fixture.put().then(function () {
      $location.path('/fixtures/' + $stateParams.id);
    });
  };
});



/*app.controller('GetFixtureByTeamName', function ($scope, $http, $window) {
    $scope.IsVisible = false;
    $scope.Search = function () {
        var fixture = '{Name: "' + $scope.Prefix + '" }';
        var post = $http({
            method: "POST",
            url: "/api/v1/fixtures/",
            dataType: 'json',
            data: fixture,
            headers: { "Content-Type": "application/json" }
        });

        post.success(function (data, status) {
            $scope.Fixtures = data;
            $scope.IsVisible = true;
        });

        post.error(function (data, status) {
            $window.alert(data.Message);
        });
    }
});*/



app.controller('FixturesAddCtrl', function ($scope, $location, FixtureRepository, TeamRepository) {
$scope.teams = TeamRepository.getList();
    $scope.team1;
    $scope.team2;

    $scope.$watch('team1', function () {
        if ($scope.team1) {
            $scope.players1 = TeamRepository.getPlayers($scope.team1.id);
        }
    }, true);

    $scope.$watch('team2', function () {
        if ($scope.team1) {
            $scope.players2 = TeamRepository.getPlayers($scope.team2.id);
        }
    }, true);

    $scope.players1;
    $scope.players2;

    $scope.deletePlayer1 = function (index) {
        $scope.players1.splice(index, 1);
    }

    $scope.deletePlayer2 = function (index) {
        $scope.players2.splice(index, 1);
    }

  $scope.save = function () {
      if ($scope.players1) {
          $scope.players1.forEach(function (player) {
              player.goalsScored = player.goal;
              player.redCards = player.red;
              player.yellowCards = player.yellow;
              console.log(player);
              FixtureRepository.updatePlayer(player, player.id);
          });
      }
      if ($scope.players2) {
          $scope.players2.forEach(function (player) {
              player.goalsScored = player.goal;
              player.redCards = player.red;
              player.yellowCards = player.yellow;
              console.log(player);
              FixtureRepository.updatePlayer(player, player.id);
          });
      }
      $scope.fixture.homeTeamId = $scope.team1.id;
      $scope.fixture.awayTeamId = $scope.team2.id;
      console.log($scope.fixture);

      console.log($scope.players1);
      console.log($scope.players2);
      console.log($scope.team1);
      console.log($scope.team2);
    FixtureRepository.create($scope.fixture).then(function () {
      $location.path('/fixtures');
      /*if($scope.players1) {
          $scope.players1.forEach(function (player) {
          });
      }
        if($scope.players2) {
            $scope.players2.forEach(function (player) {
            });
        }*/
    });
  };
});

/* Below are the states that are used */
app.config(['$stateProvider',
  function ($stateProvider) {

    $stateProvider
    .state('fixtures', {
      abstract: true,
      url: '/fixtures',
      templateUrl: 'public/views/fixtures/main.html'
 })

    .state('fixtures.list', {
      url: '',
      templateUrl: 'public/views/fixtures/list.html',
      controller: 'FixturesListCtrl'
 })

    .state('fixtures.add', {
      url: '/add',
      templateUrl: 'public/views/fixtures/edit.html',
      controller: 'FixturesAddCtrl'
 })

    .state('fixtures.edit', {
      url: '/edit/{id}',
      templateUrl: 'public/views/fixtures/edit.html',
      controller: 'FixturesEditCtrl'
 })

  .state('fixtures.getById', {
        url: '/getById/{id}',
        templateUrl: 'public/views/fixtures/getFixtureData.wsdl',
        controller: 'FixturesEditCtrl'
   })

    .state('fixtures.item', {
      url: '/{id}',
      templateUrl: 'public/views/fixtures/item.html',
      controller: 'FixturesItemCtrl'
 });
  }
]);