function CircularBuffer(size) {
    this.size = size;
    this.arr = [];
}

CircularBuffer.prototype.size = function() {
    return this.size;
};

CircularBuffer.prototype.add = function(a) {
        if(a.context==""){
            this.arr[this.arr.length-1].body = this.arr[this.arr.length-1].body + "\n " + a.body;
            this.arr[this.arr.length-1].isCollapsible = true;
        }else{
            if (this.arr.length >= this.size) {
                this.arr.shift();
            }
            this.arr.push(a);
        }
};

CircularBuffer.prototype.addAll = function(array){
    for(var i=0; i<array.length;i++){
        this.add(array[i]);
    }
};

CircularBuffer.prototype.addArray = function(arr) {
    for (var i=0;i<arr.length;i++) {
        this.add(arr[i]);
    }
};

CircularBuffer.prototype.newlineStr = function() {
    var str="";
    for (var i=0;i<this.arr.length;i++) {
        str = str + this.arr[i] + "\n";
    }
    return str;
};

CircularBuffer.prototype.newRow = function() {
    var str="";
    for (var i=0;i<this.arr.length;i++) {
        str = str + "<tr><td>"+ this.arr[i] +"</td></tr>"+ + "\n";
    }
    return str;
};

CircularBuffer.prototype.getArr = function() {
    return this.arr;
};

CircularBuffer.prototype.clear = function() {
    this.arr = [];
};

var tailFilesApp = angular.module("tailFilesApp",['angular-growl', 'ngAnimate']);

tailFilesApp.config(['growlProvider', function (growlProvider) {
    growlProvider.globalTimeToLive(3000);
    growlProvider.globalDisableCountDown(true);
    growlProvider.globalDisableIcons(true);
    growlProvider.globalPosition('top-left');
    growlProvider.globalDisableCloseButton(true);
}]);

tailFilesApp.controller("TailFilesCtrl", ['$scope', 'growl', '$http', '$interval', function ($scope, growl, $http, $interval) {

    function init() {
        $scope.pattern = "; %d %-5p [%c] %m%n";
        $scope.filePath = "C:/Work/log/out.txt";
        $scope.sidebarToggle = false;
        $scope.numLines = 600;
        $scope.buffer = new CircularBuffer($scope.numLines);
        $scope.renderBuffer = new CircularBuffer($scope.numLines);
        $scope.searchText = '';
        $scope.connected = true;
        $scope.stompClient= null;
        $scope.templateAppDev = `nexj.core.controller
nexj.model.class
nexj.model.library
nexj.core.rpc.http
nexj.core.persistence.sql.SQLAdapter
ERROR
WARN`;
        $scope.templateQA = `nexj.core.controller`;
        $scope.templateCustom = $scope.templateAppDev;
        $scope.whitelist = $scope.templateAppDev;
        $interval(function(){$scope.renderBuffer=$scope.buffer}, 1000);
        $scope.submit();
        
    }

    $scope.disconnect = function () {
        $scope.connected = false;
        $scope.stompClient.disconnect();
    }

    $scope.initSockets = function() {
        $scope.connected = true;
        $scope.socket = {};
        $scope.socket.client = new SockJS(URLS.tailFilesURL);  //new WebSocket('ws://' + window.location.host + URLS.tailFilesURL);
        $scope.stompClient = Stomp.over($scope.socket.client);
        $scope.stompClient.connect({}, function() {
            $scope.stompClient.subscribe(URLS.tailFilesTopic, $scope.notify);
        });
        $scope.socket.client.onclose = $scope.reconnect;
    };

    $scope.notify = function(message) {
        $scope.$apply(function() {
            $scope.buffer.addAll(angular.fromJson(angular.fromJson(message.body)));
        });
    };

    $scope.reconnect = function() {
        $scope.connected = true;
        setTimeout($scope.initSockets, 100);
    };

    $scope.submit = function(){
        $scope.buffer = new CircularBuffer($scope.numLines);
        $scope.templateCustom = $scope.whitelist;
        var splitted_whitelist = $scope.whitelist.split("\n");
        var data = {
            filename: $scope.filePath,
            filters: splitted_whitelist
        };
        var config = {
                headers : {
                    'Accept': 'text/plain'
                }
        };
        $http.post('/submit',data, config).then(function successCallback(response) {
            console.log(response.data);
            growl.success(response.data,{});
        }, function errorCallback(response) {
            growl.error("Oops! 'Something' went wrong.",{});
            console.log("Oops! 'Something' went wrong.");
        });
    }
    $scope.focusLog = function($event){
       $scope.searchText = '';
       setTimeout(function(){
           $event.target.focus();
           $event.target.scrollIntoView({inline: "center"});
       }, 100);
    };

    init();
    $scope.initSockets();
}]).filter('highlight', function($sce) {
    return function(text, phrase) {
      if (phrase) text = text.replace(new RegExp('('+phrase+')', 'gi'),
        '<span class="highlighted">$1</span>')

      return $sce.trustAsHtml(text)
    }
});