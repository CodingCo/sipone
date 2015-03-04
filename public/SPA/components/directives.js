(function () {

    var app = angular.module("App.directives", []);

    app.directive('navigation', function () {
        return {
            restrict: 'A',
            templateUrl: "SPA/directives/navigation.html"
        }
    });


    app.directive('footer', function () {
        return {
            restrict: 'A',
            templateUrl: "SPA/directives/footer.html"
        }
    });

})();
