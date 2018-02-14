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
}

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

var tailFilesApp = angular.module("tailFilesApp",[]);

tailFilesApp.controller("TailFilesCtrl", function ($scope) {

    function init() {
        $scope.buffer = new CircularBuffer(600);
        $scope.searchText = '';
        $scope.connected = true;
        $scope.stompClient= null;
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
            $scope.buffer.add(angular.fromJson(angular.fromJson(message.body)));
        });
    };

    $scope.reconnect = function() {
        $scope.connected = true;
        setTimeout($scope.initSockets, 100);
    };

    init();
    $scope.initSockets();
}).filter('highlight', function($sce) {
    return function(text, phrase) {
      if (phrase) text = text.replace(new RegExp('('+phrase+')', 'gi'),
        '<span class="highlighted">$1</span>')

      return $sce.trustAsHtml(text)
    }
});