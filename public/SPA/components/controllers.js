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

        $scope.subjects = ["Algorithms", "AI", "Gaming", "C#", "C++", "Android", "arduino", "System architecture", "SIP"];

        $scope.enabled = function (item) {
            return item === "AI";
        };

        $scope.disabled = function (item) {
            return item === "Gaming"

        };

        $scope.data = {
            happy: 17,
            borderline: 32,
            angry: 5
        };


        var data = {
            labels: ["Happy", "Borderline", "Angry"],
            datasets: [
                {
                    label: "Hello",
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,0.8)",
                    highlightFill: "rgba(151,187,205,0.75)",
                    highlightStroke: "rgba(151,187,205,1)",
                    data: [$scope.data.happy, $scope.data.borderline, $scope.data.angry]
                }
            ]
        };

        var options = {
            scaleBeginAtZero: true,
            scaleShowGridLines: true,
            scaleGridLineColor: "rgba(0,0,0,.05)"
        };


        var ctx = document.getElementById("myChart").getContext("2d");
        var myBarChart = new Chart(ctx).Bar(data, options);

    }]);

})();