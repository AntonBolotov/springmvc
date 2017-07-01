var app = angular.module('app', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'resources/html/pages/free.html',
            controller: 'Free'
        })
        .when('/new', {
            templateUrl: 'resources/html/pages/new.html',
            controller: 'New'
        })
        .when('/takenByMe', {
            templateUrl: 'resources/html/pages/takenByMe.html',
            controller: 'TakenByMe'
        })
        .when('/takenFromMe', {
            templateUrl: 'resources/html/pages/takenFromMe.html',
            controller: 'TakenFromMe'
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.controller('New', ['$scope', 'DvdService', AddNewDvdCtrl]);
app.controller('Free', ['$scope', 'DvdService', 'ProfileService', FreeDvdCtrl]);
app.controller('TakenByMe', ['$scope', 'DvdService', TakenByMeCtrl]);
app.controller('TakenFromMe', ['$scope', 'DvdService', TakenFromMeCtrl]);
app.controller('MainMenuCtrl', ['$scope', '$location', MainMenuCtrl]);

app.service('DvdService', DvdService);
app.service('ProfileService', ProfileService);

