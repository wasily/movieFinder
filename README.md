##### Выпускной проект OTUS.

Тема - "Автоматический поиск новинок кино/сериалов".

###### Описание.

Разработка подобного сервиса должна упростить поиск новинок фильмов/сериалов на торрент-трекерах. 

Используя IMDB (https://www.imdb.com/interfaces/), сервис берёт информацию о новинках за интересующий пользователя период и ищет данные релизы на https://rutracker.org/  (доступен открытый API - http://api.rutracker.org/v1/docs/), на выходе пользователю отдавются magnet-ссылки.

Сервис предоставляет следующие возможности:

- прямой поиск по ресурсам провайдеров контента (https://rutracker.org/ )
- подписка на новинки (появление фильма/сериала на ресурсах провайдеров контента). Оповещение пользователя появившихся новинках производится по email.

Подписка работает следующим путем:

1. При подписке ползьзователя на фильм/сериал, создается новая запись в **subscriptions**
2. После завершения обновления информации о релизах, при результативном поиске формируется **subscribed_releases** и обновляет **subscriptions**.lastUpdateTime
3.  Затем заускается NotifierService, которая исходя из **subscribed_releases** и **subscriptions**, осуществляет оповещение, очищает **subscribed_releases**

###### Стэк стехнологий.

В качестве СУБД используется MongoDB.

Backend представляет из себя REST-сервис.

Для решения задач периодической актуализации данных используется Spring Batch.

Frontend  реализован на React.js

Для реализации поиска релизов и оповещения используются отдельные сервисы, взаимодействующие между собой с помощью сообщений (Spring Integration).

Для упрощения сборки и деплоя используется Docker.

###### Доменная модель. Коллекции MongoDB.

**movies** (информация о фильмах):

- imdbId
- primaryTitle
- originalTitle
- isAdult
- genres

**series** (информация о сериалах):

- imdbId
- primaryTitle
- originalTitle
- isAdult
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
- userDTO (для оповещения)
- lastUpdateTime - время, когда подписались, а в последствии - время, когда в последний раз cделали поиск релизов

**subscribed_releases** (информация о результатах поиска релизов по подпискам):

- imdbId
- contentType
- [] content_releases

###### API.

REST контроллеры:

- GET:  /releases/movies - поиск релизов фильмов по названию
- GET:  /releases/series - поиск релизов сериалов по названию
- GET:  /movies - поиск фильмов по названию
- GET:  /series - поиск сериалов по названию
- GET:  /movies/{imdb_id} - поиск фильмов по imdb_id
- GET:  /series/{imdb_id} - поиск сериалов по imdb_id
- POST:  /subscribe/movies - подписатся на фильм
- POST:  /subscribe/series - подписатся на сериал
- GET:  /subscriptions/{user} - поиск подписок определенного пользователя
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

- subscribeOnMovie("imdb_id", "user_email")
- subscribeOnSeries("imdb_id", "user_email")
- getUserSubscriptions("user_email")
- unsubscribe("imdb_id", "user_email")