##### Выпускной проект OTUS.

Тема - "Автоматический поиск новинок кино/сериалов".

###### Описание.

Разработка подобного сервиса должна упростить поиск новинок фильмов/сериалов на торрент-трекерах. 

Сервис состоит изнескольких частей (**dataCollector** - наполнение БД, поиск новинок, оповещение пользователей, **movieFinder**- rest-сервис, предоставляющий функции поиска контента, релизов, подписки).

Используя IMDB (https://www.imdb.com/interfaces/), сервис берёт информацию о новинках за интересующий пользователя период и ищет данные релизы на https://rutracker.org/  (доступен открытый API - http://api.rutracker.org/v1/docs/), на выходе пользователю отдавются magnet-ссылки.

Сервис предоставляет следующие возможности:

- прямой поиск по ресурсам провайдеров контента (https://rutracker.org/ )
- подписка на новинки (появление фильма/сериала на ресурсах провайдеров контента). Оповещение пользователя появившихся новинках производится по email.

Подписка работает следующим путем:

1. При подписке ползьзователя на фильм/сериал, создается новая запись в **subscriptions**
2. После завершения обновления информации о релизах, при результативном поиске формируется (c использованием Spring Integration) message для поиска новинок, на которые есть подписка
3.  При результативном поисек новинок (c использованием Spring Integration) заускается NotifierService, которая осуществляет оповещение по email

###### Стэк стехнологий.

В качестве СУБД используется MongoDB.

Backend представляет из себя REST-сервис.

Для реализации поиска релизов и оповещения (Spring Mail) используются отдельные сервисы, взаимодействующие между собой с помощью сообщений (Spring Integration).

Для осуществления мониторинга используется Spring Actuator.

Для упрощения сборки и деплоя используется Docker.

###### Доменная модель. Коллекции MongoDB.

**movies** (информация о фильмах):

- imdbId
- primaryTitle
- originalTitle
- isAdult
- startYear
- genres

**series** (информация о сериалах):

- imdbId
- primaryTitle
- originalTitle
- isAdult
- startYear
- genres

**content_releases** (информация о релизах контента):

- trackerId
- contentType (movie, series)
- title
- size
- infoHash
- regTime

**subscriptions** (информация о подписках на оповещение):

- imdbId 
- contentType (movie, series)
- contentTitle (для поиска по релизам)
- userEmail
- lastUpdateTime - время, когда подписались, а в последствии - время, когда в последний раз cделали поиск релизов

###### API.

REST контроллеры:

- GET:  /releases/movies/{title} - поиск релизов фильмов по названию
- GET:  /releases/series/{title} - поиск релизов сериалов по названию
- GET:  /movies/title/{title} - поиск фильмов по названию
- GET:  /series/title/{title} - поиск сериалов по названию
- GET:  /movies/id/{imdb_id} - поиск фильмов по imdb_id
- GET:  /series/id/{imdb_id} - поиск сериалов по imdb_id
- POST:  /subscriptions/movies - подписатся на фильм
- POST:  /subscriptions/series - подписатся на сериал
- GET:  /subscriptions/{userEmail} - поиск подписок определенного пользователя
- DEL:  /subscriptions - отмена подписки пользователя на опреленный фильм/сериал

Сервисы:

информация о релизах:

- getMovieReleases("title")
- getSeriesReleases("title")

информация о контенте:

- getSeriesByTitle("title")
- getMoviesByTitle("title")
- getSeriesByImdbId("imdb_id")
- getMovieByImdbId("imdb_id")

информация о подписках на оповещение:

- subscribeOnMovie("imdb_id", "title", "user_email")
- subscribeOnSeries("imdb_id", "title", "user_email")
- getUserSubscriptions("user_email")
- unsubscribe("imdb_id", "user_email")