app.service('AuthService', function($http) {
    return {
        login : function(username, password) {
            // Server
            // return $http.post('/sveucilisna/user/login', {userName: username, password: password}).then(function(response)

            return $http.post('/user/login', {userName: username, password: password}).then(function(response) {
                return response.data.token;
            });
        },

    };
});
