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
        stomp.subscribe('/topic/authors', function (author) {
            renderItem(author);
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
    stomp.send("/app/authors", {}, JSON.stringify({
        'name': $("#name").val(),
        'lastname': $("#lastname").val(),
        'patronymic': $("#patronymic").val(),
        'portrait': $("#portrait").val(),
        'about': $("#about").val()
    }));
}

// рендер сообщения, полученного от сервера
function renderItem(authorJson) {
    var author = JSON.parse(authorJson.body);
    $("#table").append("<tr>" +
        "<td>" + author.name + "</td>" +
        "<td>" + author.lastname + "</td>" +
        "<td>" + author.patronymic + "</td>" +
        "<td>" + author.portrait + "</td>" +
        "<td>" + author.about + "</td>" +
//        "<td><a href='/authors/" + author.id + "/bucket'>Add to bucket</a></td>" +
        "</tr>");
}
