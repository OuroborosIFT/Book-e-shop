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
        stomp.subscribe('/topic/books', function (book) {
            renderItem(book);
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
    stomp.send("/app/books", {}, JSON.stringify({
        'title': $("#title").val(),
        'image': $("#image").val(),
        'description': $("#description").val(),
        'price': $("#price").val()
    }));
}

// рендер сообщения, полученного от сервера
function renderItem(bookJson) {
    var book = JSON.parse(bookJson.body);
    $("#table").append("<tr>" +
        "<td>" + book.title + "</td>" +
        "<td>" + book.image + "</td>" +
        "<td>" + book.description + "</td>" +
        "<td>" + book.price + "</td>" +
        "<td><a href='/books/" + book.id + "/bucket'>Add to bucket</a></td>" +
        "</tr>");
}
