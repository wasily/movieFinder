##### Выпускной проект OTUS.

Тема - "Автоматический поиск новинок кино/сериалов".

###### Описание.

Разработка подобного сервиса должна упростить поиск новинок фильмов/сериалов на торрент-трекерах. 

Используя IMDB (https://www.imdb.com/interfaces/), сервис берёт информацию о новинках за интересующий пользователя период и ищет данные релизы на https://rutracker.org/  (доступен открытый API - http://api.rutracker.org/v1/docs/), на выходе пользователю отдавются magnet-ссылки.

Сервис предоставляет следующие возможности:

- прямой поиск по ресурсам провайдеров контента (https://rutracker.org/ )
- подписка на новинки (появление фильма/эпизода сериала на ресурсах провайдеров контента). Оповещение пользователя появившихся новинках производится по email.

###### Стэк стехнологий.

В качестве СУБД используется MongoDB.

Backend представляет из себя REST-сервис.

Для решения задач периодической актуализации данных используется Spring Batch.

Frontend  реализован на React.js

Для реализации оповещения используется отдельный сервис, взаимодействие с backend`ом реализовано с помощью сообщений (Spring Integration).

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
- title
- size
- infoHash
- regTime

**subscriptions** (информация о подписках на оповещение):

- imdbId (для поиска инофрмации о выходе в эфир/прокат)
- contentType (movie, series)
- contentTitle (для поиска по релизам)
- [] usersEmails (для оповещения)
- searchDate - для фильмов - по наступлению даты, осуществлять поиск по релизам
- nextEpisodeSearchDate - для сериалов - по наступлению даты, ходить на imdb и обновлять дату поиска след. эпизода

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
- GET:  /subscriptions/{user_email} - поиск подписок определенного пользователя
- PUT:  /subscriptions/{imdb_id} - отмена подписки пользователя на опреленный фильм/сериал

Сервисы:

информация о релизах:

- getMovieReleases("title")
- getSeriesReleases("title")

информация о контенте:

- getNextAiredEpisodeDate("imdb_id") - получение информации об эфире ближайшей серии
- getMovieReleaseDate("imdb_id") - получение информации о премьере фильма
- getSeriesByTitle("title")
- getMoviesByTitle("title")
- getSeriesByImdbId("imdb_id")
- getMovieByImdbId("imdb_id")

информация о подписках на оповещение:

- subscribeOnMovie("imdb_id", "user_email")
- subscribeOnSeries("imdb_id", "user_email")
- getUserSubscriptions("user_email")
- getEmailsOfSubscription("imdb_id")
- unsubscribe("imdb_id", "user_email")