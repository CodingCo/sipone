(function () {

    var app = angular.module("App.controllers", []);


    app.controller("StandardCtrl", ["$scope", function ($scope) {

        $scope.list = "Gang of 6";

    }]);

    app.controller("FistSelectCtrl", ["$scope", "serverFactory", "toastr", function ($scope, serverFactory, toastr) {
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
                var chosenSubject = $scope.subjects.splice(index, 1)[0];

                if (fLength != 2) {
                    $scope.selectedItems.first.push(chosenSubject);
                } else if (sLength != 2) {
                    $scope.selectedItems.second.push(chosenSubject);
                }
            }
        };

        $scope.removeItem = function (item) {
            var indexOfObject = "";
            var objectToRemove = "";

            function remove(field) {
                indexOfObject = $scope.selectedItems[field].indexOf(item);
                objectToRemove = $scope.selectedItems[field].splice(indexOfObject, 1)[0];
                $scope.subjects.push(objectToRemove);
            }

            if ($scope.selectedItems.first.indexOf(item) != -1) {
                remove("first");
            } else if ($scope.selectedItems.second.indexOf(item) != -1) {
                remove("second");
            }
        };

        $scope.submit = function () {
            toastr.success("Submitted");
        }

    }]);


})();