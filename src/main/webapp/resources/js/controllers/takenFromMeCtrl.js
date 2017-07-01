function TakenFromMeCtrl($scope, DvdService, ProfileService, $localStorage) {
    $scope.givenDvds = [];
    $scope.selectedDvd = '';

    $scope.openWaiter();
    function loadGiven() {
        $scope.openWaiter();

        DvdService.getGivenDvds() // load model with delay
            .then(function (json) {
                if (json.data && json.data.message) {
                    $scope.openMessage(json.data.message);
                    return;
                }

                $scope.givenDvds = json.data.takenItems; // No more problems
                console.log(json.data);
            }).finally(function () {
                $scope.closeWaiter();
            });
    }

    $scope.pickup = function(takenItem){
        $scope.openWaiter();

        DvdService.pickup(takenItem.id)
            .then(function (json) {
                if (json.data && json.data.message) {
                    $scope.openMessage(json.data.message);
                    return;
                }

                loadGiven();
            }).finally(function () {
                $scope.closeWaiter();
            });
    };

    loadGiven();
}
