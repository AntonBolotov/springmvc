function FreeDvdCtrl($scope, DvdService, ProfileService) {
    $scope.dvds = [];
    $scope.selectedDvd = '';
    $scope.currentProfile = {};
    $scope.mainPage = 'class="active"';

    function loadFree() {
        $scope.openWaiter();
        DvdService.getFree()
            .then(function (json) {
                if (json.data && json.data.message) {
                    $scope.openMessage(json.data.message);
                    return;
                }

                $scope.dvds = json.data.dvds;
                $scope.currentProfile = json.data.currentProfile;
                console.log(json.data);
            }).finally(function () {
                $scope.closeWaiter();
            });
    }


    $scope.take = function(dvd){
        $scope.openWaiter();
        DvdService.take(dvd.id) .then(function (json) {
            if (json.data && json.data.message) {
                $scope.openMessage(json.data.message);
                return;
            }

            loadFree();
        }).finally(function () {
            $scope.closeWaiter();
        });
    };

    $scope.give = function(dvd){
        $scope.selectedDvd = dvd;
        if (!$scope.users || !$scope.users.length) {
            $scope.openWaiter();
            ProfileService.getAll().then(function (json) {
                if(json.data.message){
                    $scope.showMessage(json.data.message);
                    return;
                }
                $scope.openUserList(json.data.profiles);
            }).finally(function () {
                $scope.closeWaiter();
            });
        } else {
            $scope.openUserList();
        }
    };

    $scope.selectUser = function (user) {
        $scope.openWaiter();
        DvdService.give($scope.selectedDvd.id, user.id)
            .then(function (json) {
                if (json.data && json.data.message) {
                    $scope.openMessage(json.data.message);

                    return;
                }

                loadFree();
            })
            .finally(function () {
                $scope.closeUserList();
                $scope.closeWaiter();
            });
    };

    loadFree();
}
