(function () {
    var app = angular.module('App', [
        'ngRoute',
        'App.controllers',
        'App.directives'
    ]);


    app.config(["$routeProvider", function ($routeProvider) {

        $routeProvider.when("/first", {
            templateUrl: "",
            controller: ""
        }).otherwise({
            templateUrl:"SPA/views/frontpage.html",
            controller:"StandardCtrl"
        });

    }]);


})();


