INSERT INTO authors (id, seq_name, name, lastname, patronymic, portrait, about)
VALUES (1, 'author_seq', 'Александр', 'Пушкин', 'Сергеевич', 'https://upload.wikimedia.org/wikipedia/commons/5/56/Kiprensky_Pushkin.jpg', 'Один из самых авторитетных литературных деятелей первой трети XIX века. Ещё при жизни Пушкина сложилась его репутация величайшего национального русского поэта. Пушкин рассматривается как основоположник современного русского литературного языка.');
ALTER SEQUENCE author_seq RESTART WITH 2;
