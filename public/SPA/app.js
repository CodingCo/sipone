(function () {
    var app = angular.module('App', [
        'ngRoute',
        'toastr',
        'App.controllers',
        'App.directives',
        'App.factories'
    ]);


    app.config(["$routeProvider", function ($routeProvider) {

        $routeProvider.when("/subjectOne", {
            templateUrl: "SPA/views/subjectOne.html",
            controller: "FistSelectCtrl"
        }).when("/poolPage", {
            templateUrl: "SPA/views/poolPage.html",
            controller: ""
        }).otherwise({
            templateUrl: "SPA/views/frontpage.html",
            controller: "StandardCtrl"
        });

    }]);
})();


