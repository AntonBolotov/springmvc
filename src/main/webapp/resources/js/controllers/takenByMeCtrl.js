function TakenByMeCtrl($scope, DvdService, ProfileService, $localStorage) {
    $scope.takenCards = [];
    $scope.selectedDvd = '';

    $scope.openWaiter();
    function loadTakens() {
        $scope.openWaiter();
        DvdService.getTakenDvds() // load model with delay
            .then(function (json) {
                if (json.data && json.data.message) {
                    $scope.openMessage(json.data.message);
                    return;
                }

                $scope.takenCards = json.data.takenItems; // No more problems
                console.log(json.data);
            }).finally(function () {
                $scope.closeWaiter();
            });
    }

    $scope.retrieve = function (takenItem) {
        $scope.openWaiter();
        DvdService.retrieve(takenItem.id)
            .then(function (json) {
                if (json.data && json.data.message) {
                    $scope.openMessage(json.data.message);
                    return;
                }

                loadTakens();
            }).finally(function () {
                $scope.closeWaiter();
            });
    };

    loadTakens();
}