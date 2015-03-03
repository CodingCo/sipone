(function () {
    var app = angular.module("App", []);

    app.controller("SimpleController", ["$scope", function ($scope) {

        $scope.list = "I am a list element";
    }]);


})();


