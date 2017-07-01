app.factory("sessionInjector", ['$log', function($log){
    return {
        request: function(config) {return config;},
        response: function(response) {
            if (typeof response.data === "string" && response.data.indexOf("form-signin") > -1) {
                //alert("Session expired.");
                location.href = 'login';
            }
            return response;
        }
    };
}]);
app.config(["$httpProvider", function($httpProvider){
    $httpProvider.interceptors.push("sessionInjector");
}]);