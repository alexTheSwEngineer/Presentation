app.controller("demoController", function ($scope, $http, demoService, filterFilter){
    $scope.globalWordList = [];
    $scope.wordList = [{text:"Example1",size:23},{text:"Example2",size:22},{text:"Example3",size:13},{text:"Example4",size:45}];
    $scope.globalWordList = $scope.wordList;
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

    $scope.changeInput = function () {
        $scope.item = $scope.globalWordList;

        // $scope.wordList = filterFilter($scope.item, {size: $scope.inputNumber});
        $scope.wordList = filterFilter($scope.item, {size: $scope.inputNumber}, function (actual, expected) {
            console.log(actual);
            console.log(expected);

            if (actual < expected){
                return true;
            }
            return false;
        });
    }
});
