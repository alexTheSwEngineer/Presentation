app.controller("demoController", function ($scope, $http, demoService, filterFilter){
    $scope.globalWordList = [];
    $scope.wordList = [{text:"asd",size:23},{text:"asd",size:22},{text:"asd",size:13},{text:"asd",size:45}];

    $scope.inputText = "";

    $scope.inputNumber = "";

    $scope.sendText = function () {
        demoService.sendText($scope.inputText)
            .then(function (success) {
                $scope.item = success;
                $scope.item.map(function (obj) {
                    obj.size *=10;
                });
                $scope.wordList = $scope.item;
                $scope.globalWordList = $scope.wordList;
                console.log(success);
                $scope.inputText = "";
            }, function (error) {
                console.log(error);
            })
    };

    $scope.proba = function () {
        $scope.item = $scope.globalWordList;

        $scope.wordList = filterFilter($scope.item, {size: $scope.inputNumber});
    }
});
