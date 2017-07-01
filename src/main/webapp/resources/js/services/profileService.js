function ProfileService($http){
    this.getAll = function(){
        return $http.get('profile/all')
    };
}