(function () {

    var app = angular.module("App.controllers", []);


    app.controller("StandardCtrl", ["$scope", function ($scope) {

        $scope.list = "Hello world!";

    }]);

    app.controller("FistSelectCtrl", ["$scope", function ($scope) {
        $scope.subjects = [
            "Ai and algorithms",
            "Arduino & C++",
            "C#",
            "Paskall",
            "Android",
            "Game Design",
            "Software Architecture"
        ];



        $scope.addItem = function (itemName) {



        };

        $scope.removeItem = function (itemName) {
            

        };

    }]);


})();