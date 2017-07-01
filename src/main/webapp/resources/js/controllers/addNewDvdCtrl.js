function AddNewDvdCtrl($scope, DvdService) {
    $scope.dvdForm = {};

    $scope.reset = function (dvd) {
        $scope.dvdForm.$setPristine(true);

        $scope.dvdName = '';
        $scope.description = '';
        $('#poster').val('');
        $scope.dvdForm.$setUntouched();
        $scope.dvdForm.$setPristine();
    };

    $scope.submit = function (dvdForm) {
        if (dvdForm.$valid) {
            $scope.openWaiter();

            var formData = new FormData();
            formData.append('name', $scope.dvdName);
            formData.append('description', $scope.description);

            var file = document.getElementById('poster').files[0];
            formData.append('poster', file);

            DvdService.add(formData)
                .then(function (data, status) {
                    $scope.openMessage(data.data.message);
                    $scope.reset();

                }).catch(function (data, status) {
                    if (!data || data.status == 400) {
                        $scope.openMessage('Неизвестная ошибка');
                    } else {
                        $scope.openMessage(data.data.message)
                    }

                }).finally(function () {
                    $scope.closeWaiter();
                });
        }
    };

}