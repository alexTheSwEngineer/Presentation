app.controller("demoController", function ($scope, $http, demoService){
    $scope.wordList = [{text:"asd",size:23},{text:"asd",size:22},{text:"asd",size:13},{text:"asd",size:45}];

    $scope.sendText = function () {
        demoService.sendText()
            .then(function (success) {
                console.log(success);
            }, function (error) {
                console.log(error);
            })
    };
    
});
