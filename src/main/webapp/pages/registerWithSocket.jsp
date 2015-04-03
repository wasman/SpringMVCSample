<html>
<head>
    <title>Calculator App Using Spring 4 WebSocket</title>
    <script src="resources/sockjs.js"></script>
    <script src="resources/stomp.js"></script>
    <script type="text/javascript">
        var stompClient = null;
        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('registerDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('registerResponse').innerHTML = '';
        }
        function connect() {
            var socket = new SockJS('/registerSPerson');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/showResult', function (responseResult) {
                    showResult(JSON.parse(responseResult.body).result);
                });
            });
        }
        function disconnect() {
            stompClient.disconnect();
            setConnected(false);
            console.log("Disconnected");
        }
        function register() {
            var name = document.getElementById('name').value;
            var email = document.getElementById('email').value;
            stompClient.send(
                    "/socketEx/registerSPerson",
                    {},
                    JSON.stringify({'name': name, 'email': email})
            );
        }
        function showResult(message) {
            var response = document.getElementById('registerResponse');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
        }
    </script>
</head>
<body>
<noscript><h2>Enable Java script and reload this page to run Websocket Registration</h2></noscript>
<h1>Registration Using Spring 4 WebSocket</h1>

<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
        <br/><br/>
    </div>
    <div id="registerDiv">
        <label>Name :</label><input type="text" id="name"/><br/>
        <label>Email :</label><input type="text" id="email"/><br/><br/>
        <button id="register" onclick="register();">Send to Add</button>
        <p id="registerResponse"></p>
    </div>
</div>
</body>
</html>