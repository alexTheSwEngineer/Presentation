app.service("demoService", function ($http) {

    this.sendText = function (text) {
        return $http({
            method: "POST",
            url: "...",
            data: text
        }).then(function(response) {
            console.log("Promise");
        }, function(response) {
            console.log("error");
        });
    }

});