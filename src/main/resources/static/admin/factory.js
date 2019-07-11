var app = angular.module('sveucilisnaAdmin');

app.factory('AbstractRepository', [

  function () {

    function AbstractRepository(restangular, route) {
      this.restangular = restangular;
      this.route = route;
    }

    AbstractRepository.prototype = {
      getList: function (params) {
        return this.restangular.all(this.route).getList(params).$object;
      },
      get: function (id) {
        return this.restangular.one(this.route, id).get();
      },
      getView: function (id) {
        return this.restangular.one(this.route, id).one(this.route + 'view').get();
      },
      update: function (updatedResource, id) {
        return this.restangular.one(this.route, id).put(updatedResource);
      },
      updatePlayer: function (updatedResource, id) {
        return this.restangular.all(this.route + '/player/' + id).post(updatedResource);
      },
      getPlayers: function (id) {
        return this.restangular.all(this.route + '/players/' + id).getList().$object;
      },
      create: function (newResource) {
        return this.restangular.all(this.route).post(newResource);
      },
      remove: function (object) {
        return this.restangular.one(this.route, object.id).remove();
      }
    };

    AbstractRepository.extend = function (repository) {
      repository.prototype = Object.create(AbstractRepository.prototype);
      repository.prototype.constructor = repository;
    };

    return AbstractRepository;
  }
]);