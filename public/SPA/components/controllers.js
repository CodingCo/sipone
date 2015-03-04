(function () {

    var app = angular.module("App.controllers", []);


    app.controller("StandardCtrl", ["$scope", function ($scope) {

        $scope.list = "Hello world!";

    }]);

    app.controller("FistSelectCtrl", ["$scope", function ($scope) {


    }]);


})();