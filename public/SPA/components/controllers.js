(function () {

    var app = angular.module("App.controllers", []);


    app.controller("StandardCtrl", ["$scope", function ($scope) {

        $scope.list = "Gang of 6";

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

        $scope.selectedItems = {
            first: [],
            second: []
        };

        $scope.visible = function (item) {
            return $scope.subjects.indexOf(item) != -1;
        };


        $scope.addItem = function (item) {
            var fLength = $scope.selectedItems.first.length;
            var sLength = $scope.selectedItems.second.length;


            if (fLength != 2 || sLength != 2) {
                var index = $scope.subjects.indexOf(item);
                var chosenSubject = $scope.subjects.splice(index, 1);

                if (fLength != 2) {
                    $scope.selectedItems.first.push(chosenSubject);
                } else if (sLength != 2) {
                    $scope.selectedItems.second.push(chosenSubject);
                }
            }
        };

        $scope.removeItem = function (item) {


        };

    }]);


})();