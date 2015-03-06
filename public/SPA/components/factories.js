(function () {

    var app = angular.module("App.factories", []);

    app.factory("serverFactory", ["$http", function ($http) {
        return {
            submitSubjects: function (subjects, callback) {
                $http.post('/api/submit/first', subjects)
                    .success(function (data) {
                        callback(undefined, data);
                    })
                    .error(function (data, status, headers) {
                        callback(data);
                    });

            },

            getFirstSubjects: function (callback) {
                $http.get('/api/subject/first')
                    .success(function (data) {
                        callback(undefined, data);
                    })
                    .error(function (data, status) {
                        callback(data);
                    })
            }
        }
    }]);
})();
