(function () {

    var app = angular.module("App.controllers", []);


    app.controller("StandardCtrl", ["$scope", function ($scope) {

        $scope.list = "Gang of 6";

    }]);

    app.controller("FistSelectCtrl", ["$scope", "serverFactory", "toastr", function ($scope, serverFactory, toastr) {
        $scope.subjects = [];
        $scope.selectedItems = {
            first: [],
            second: []
        };

        function getCourse() {
            serverFactory.getFirstSubjects(function (err, data) {
                if (err) {
                    $scope.subjects = ["No elective subjects yet"];
                    return;
                }
                $scope.subjects = [];
                for (var i = 0; i < data.length; ++i) {
                    $scope.subjects.push(data[i].electiveCourseName);
                }
            });
        }

        getCourse();


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
            if ($scope.selectedItems.first.length == 2 && $scope.selectedItems.second.length == 2) {
                //serverFactory.submitSubjects($scope.selectedItems, function (err, data) {
                //    if (err) {
                //        toastr.warning("Something went wrong");
                //        return;
                //    }
                //    toastr.success("you chose: " + data);
                //});

                toastr.success("Well chosen");
                getCourse();
                $scope.selectedItems = {
                    first: [],
                    second: []
                };

            } else {
                toastr.warning("you must select 2 first, and 2 second priorities");
            }
        }

    }]);

    app.controller("poolCtrl", ["$scope", "toastr", function ($scope, toastr) {
        $scope.subjects = [];
    }]);

})();