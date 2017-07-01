function DvdService($http){
    this.add = function(formData){
        //return $.get(endpoint + '/' + id);

        return $http.post('dvd/new', formData, {
            transformRequest: function (data, headersGetterFunction) {
                return data;
            },
            headers: {
                'Content-Type': undefined,
                modelAttribute: 'dvd'
            }
        });
    };

    this.getMy = function(){
        return $http.get('dvd/my');
    };

    this.give = function(dvdId, userId){
        return $http({
            url: 'dvd/give',
            method: 'GET',
            params: {
                dvdId: dvdId,
                userId: userId
            }
        })
    };

    this.take = function(dvdId){
        return $http({
            url: 'dvd/take',
            method: 'GET',
            params: {
                dvdId: dvdId
            }
        })
    };

    this.getTakenDvds = function(){
        return $http({
            url: 'dvd/get/taken',
            method: 'GET'
        })
    };

    this.getGivenDvds = function(){
        return $http({
            url: 'dvd/get/given',
            method: 'GET'
        })
    };

    this.getFree = function(){
        return $http({
            url: 'dvd/get/free',
            method: 'GET'
        })
    };

    this.retrieve = function (id) {
        return  $http({
            url: 'dvd/retrieve',
            method: 'GET',
            params: {
                id: id
            }
        })
    };

    this.pickup = function (id) {
        return  $http({
            url: 'dvd/pickup',
            method: 'GET',
            params: {
                id: id
            }
        })
    };
};