app.directive('messageDialog', function () {
    return {
        restrict: 'E',
        scope: false,
        templateUrl: 'resources/html/templates/errorMessage.html',
        replace: true,
        link: function (scope, element, attrs) {

            //Hide or show the modal
            scope.showModalMsg = function (visible) {
                if (visible) {
                    $(element).modal("show");
                }
                else {
                    $(element).modal("hide");
                }
            };

            scope.$watch(function () {
                return scope.showMessage
            }, function (newValue, oldValue) {
                //console.log(newValue);
                scope.showModalMsg(newValue);
            });

            $(element).bind("hide.bs.modal", function () {
                scope.showMessage = false;
                if (!scope.$$phase && !scope.$root.$$phase)
                    scope.$apply();
            });
        },
        controller: function ($scope) {
            $scope.showMessage = false;

            $scope.openMessage = function(text) {
                $scope.popUpDialogContent = text;
                $scope.showMessage = true;
            };

            $scope.closeMessage = function () {
                $scope.showMessage = false;
            };
        }
    }
});