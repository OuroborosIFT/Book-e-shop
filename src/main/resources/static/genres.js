var stomp = null;

// подключаемся к серверу по окончании загрузки страницы
window.onload = function() {
    connect();
};

function connect() {
    var socket = new SockJS('/socket');
    stomp = Stomp.over(socket);
    stomp.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stomp.subscribe('/topic/genres', function (genre) {
            renderItem(genre);
        });
    });
}

// хук на интерфейс
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendContent(); });
});

// отправка сообщения на сервер
function sendContent() {
    stomp.send("/app/genres", {}, JSON.stringify({
        'title': $("#title").val()
    }));
}

// рендер сообщения, полученного от сервера
function renderItem(genreJson) {
    var genre = JSON.parse(genreJson.body);
    $("#table").append("<tr>" +
        "<td>" + genre.title + "</td>" +
        "<td><a href='/genres/" + genre.id + "/bucket'>Add to bucket</a></td>" +
        "</tr>");
}
