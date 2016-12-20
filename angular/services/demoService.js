app.service("demoService", function ($http, $q) {

    this.sendText = function (text) {
        var def = $q.defer();
        $http.post("http://localhost:8080/count", text)
            .success(function (success) {
                def.resolve(success);
            })
            .error(function (error) {
                def.reject('Error while creating widget: ' +JSON.stringify(error));
            });
        return def.promise;
    }

});