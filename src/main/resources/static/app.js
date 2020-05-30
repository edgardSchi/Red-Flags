var stompClient = null;
var connected = false;

let cards = [];

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

async function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        connected = true;
        stompClient.subscribe('/user/topic/join', function (response) {
            let json = JSON.parse(response.body);
            if(json == "USERNAME_TAKEN") {
                alert("Username is already taken!")
            } else {
                showPlayers(json);

            }

        });
        stompClient.subscribe('/topic/players', function (response) {
            let json = JSON.parse(response.body);
            console.log(json)
            if(json.action == "PLAYER_CONNECTED") {
                showPlayer(json.player);
            }
        });
        stompClient.subscribe('/topic/cards', function (response) {
            let json = JSON.parse(response.body);
            showPlayedCard(json);
        });
        stompClient.subscribe('/user/topic/cards', function (response) {
            let json = JSON.parse(response.body);
            if(json.intent = "ADD_CARD") {
                cards.push(json.updatedCard);
            }
            showOwnCards();
            console.log("My cards: " + cards.toString());
        });
    });
    return;
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    connected = false;
}

async function sendName() {
    //stompClient.send("/app/card", {}, JSON.stringify({'name': $("#name").val()}));
    if(!connected) await connect();
    stompClient.send("/app/join", {}, $("#name").val());
}

function playCard(id) {
    stompClient.send("/app/playCard", {}, cards[id].id);
    cards.splice(id, 1);
    showOwnCards();

    console.log("Play card called!");
}

function drawCard() {
    stompClient.send("/app/drawCard", {});
}

function showPlayers(players) {
    $("#greetings").empty();
   for(let i = 0; i < players.length; i++) {
        showPlayer(players[i]);
    }
}

function showPlayer(player) {
    $("#greetings").append("<tr><td>" + player.username + "</td></tr>");
}

function showPlayedCard(card) {
    $("#cards").append("<tr><td>" + card.content + "</td></tr>");
}

function showCard(id, card) {
    $("#own-cards").append($('<button>' + card.content + '</button>').attr('id', id).attr('onClick', 'playCard(this.id)'));
}

function showOwnCards() {
    $("#own-cards").empty();
    for(let i = 0; i < cards.length; i++) {
        showCard(i, cards[i]);
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); $("#send").prop("disabled", true)});
    $( "#draw-card").click(function () { drawCard() });
});