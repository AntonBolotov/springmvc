app.directive('userList', function () {
    return {
        restrict: 'E',
        replace: true,
        scope: false,
        templateUrl: 'resources/html/templates//userList.html',
        link: function (scope, element, attrs) {

            //Hide or show the modal
            scope.showModalUserList = function (visible) {
                if (visible) {
                    $(element).modal("show");
                }
                else {
                    $(element).modal("hide");
                }
            };

            scope.$watch(function () {
                return scope.visible
            }, function (newValue, oldValue) {
                //console.log(newValue);
                scope.showModalUserList(newValue);
            });

            $(element).bind("hide.bs.modal", function () {
                scope.visible = false;
                if (!scope.$$phase && !scope.$root.$$phase)
                    scope.$apply();
            });
        },  controller: function ($scope) {
            $scope.users = [];

            $scope.visible = false;


            $scope.openUserList = function(users) {
                if(users && users.length){
                    $scope.users = users;
                }

                $scope.visible = true;
            };

            $scope.closeUserList = function () {
                $scope.visible = false;
            };
        }
    }
});