app.directive('waiter', function(){
    return {
        restrict: 'E',
        scope: false,
        replace: true,
        templateUrl: 'resources/html/templates//waiter.html',
        link: function (scope, element, attrs) {
            //Hide or show the modal
            scope.showModalWaiter = function (visible) {
                if (visible) {
                    $(element).modal("show");
                }
                else {
                    $(element).modal("hide");
                }
            };

            scope.$watch(
                function () {
                    return scope.showWaiter
                },
                function (newValue, oldValue) {
                    scope.showModalWaiter(newValue);
                });

            $(element).bind("hide.bs.modal", function () {
                scope.showWaiter = false;
                if (!scope.$$phase && !scope.$root.$$phase)
                    scope.$apply();
            });
        },
        controller: function ($scope) {
            $scope.showWaiter = false;


            $scope.openWaiter = function() {
                $scope.showWaiter = true;
            };

            $scope.closeWaiter = function () {
                $scope.showWaiter = false;
            };
        }
    }
});