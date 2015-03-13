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

        var subjects = ["Algorithms", "AI", "Gaming", "C#", "C++", "Android", "arduino", "System architecture", "SIP"];
        var newSubject = [
            {
                title: "Algorithms",
                first: 10,
                second: 5
            },
            {
                title: "AI",
                first: 7,
                second: 2
            },
            {
                title: "Gaming",
                first: 14,
                second: 8
            },
            {
                title: "C#",
                first: 11,
                second: 2
            },
            {
                title: "C++",
                first: 2,
                second: 7
            },
            {
                title: "Android",
                first: 21,
                second: 16
            },
            {
                title: "Arduino",
                first: 1,
                second: 0
            },
            {
                title: "System architecture",
                first: 9,
                second: 9
            },
            {
                title: "System architecture",
                first: 13,
                second: 5
            }
        ];





        $scope.poolA = subjects.slice(0);
        $scope.poolB = subjects.slice(0);

        $scope.chosen = {
            A: ["A"],
            B: ["B"]
        };

        $scope.activate = function (item, pool) {
            if ($scope.chosen[pool].indexOf(item) == -1) {
                $scope.chosen[pool].push(item);

                if (pool === "A") {
                    if ($scope.chosen['B'].indexOf(item) != -1) {
                        $scope.chosen['B'].splice($scope.chosen['B'].indexOf(item), 1);
                    }
                } else {
                    if ($scope.chosen['A'].indexOf(item) != -1) {
                        $scope.chosen['A'].splice($scope.chosen['A'].indexOf(item), 1);
                    }
                }
            } else if ($scope.chosen[pool].indexOf(item) != -1) {
                $scope.chosen[pool].splice($scope.chosen[pool].indexOf(item), 1);
            }
        };

        $scope.enabled = function (item, pool) {
            if ($scope.chosen[pool].indexOf(item) != -1) {
                return true;
            }
        };

        $scope.disabled = function (item, pool) {
            if (pool === "B") {
                if ($scope.chosen['A'].indexOf(item) != -1 && $scope.chosen[pool].indexOf(item) == -1) {
                    return true;
                }
            } else {
                if ($scope.chosen['B'].indexOf(item) != -1 && $scope.chosen[pool].indexOf(item) == -1) {
                    return true;
                }
            }
        };

        $scope.clearSelections = function () {
            $scope.chosen['A'] = [];
            $scope.chosen['B'] = [];
            toastr.info("Poolboy cleaned your pools")
        };

        $scope.submitSelections = function () {
            $scope.chosen['A'] = [];
            $scope.chosen['B'] = [];
            toastr.success("A lot of people are now happy")
        };

        $scope.flashTool = function () {
          toastr.warning("The flash has not yet reached this destination");
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

    }
    ])
    ;

})();