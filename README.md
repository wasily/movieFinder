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

movies (информация о фильмах):

- imdb_id
- primaryTitle
- originalTitle
- isAdult
- genres

series (информация о сериалах):

- imdb_id
- primaryTitle
- originalTitle
- isAdult
- genres

rutracker_releases (информация о релизах контента):

- title
- size
- info_hash
- reg_time

subscriptions (информация о подписках на оповещение):

- imdb_id (для поиска инофрмации о выходе в эфир/прокат)
- content_type (movie, series)
- content_title (для поиска по релизам)
- [] users_emails (для оповещения)
- search_date - для фильмов - по наступлению даты, осуществлять поиск по релизам
- next_episode_search_date - для сериалов - по наступлению даты, ходить на imdb и обновлять дату поиска след. эпизода

###### API.

REST контроллеры:

- GET:  /movies - поиск фильмов по названию

- GET:  /series - поиск сериалов по названию

- GET:  /movies/{imdb_id} - поиск фильмов по imdb_id
- GET:  /series/{imdb_id} - поиск сериалов по imdb_id
- POST:  /subscribe/movies - подписатся на фильм

- POST:  /subscribe/series - подписатся на сериал
- GET:  /subscriptions/{user_email} - поиск подписок определенного пользователя
- PUT:  /subscriptions/{imdb_id} - отмена подписки пользователя на опреленный фильм/сериал

Репозитории:

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