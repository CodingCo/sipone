(function () {
    var app = angular.module('App', [
        'ngRoute',
        'App.controllers',
        'App.directives'
    ]);


    app.config(["$routeProvider", function ($routeProvider) {

        $routeProvider.when("/subjectOne", {
            templateUrl: "SPA/views/subjectOne.html",
            controller: "FistSelectCtrl"
        }).otherwise({
            templateUrl: "SPA/views/frontpage.html",
            controller: "StandardCtrl"
        });

    }]);
})();


