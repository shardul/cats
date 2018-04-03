# Current Android implementation Tools (CATs)
The project is about CATs. This is an effort to use all the implementation best practices to build a simple app.
Though its written in JAVA and not in Kotlin, focus is on implementation tools rather than the syntax.

## Use Case
Fetch the worlds finest cats (animal) and show them in a grid. Application should persist for viewing pleasure next time, with a possibility to fetch new ones with a refresh button.
Also, using the same codebase, generate an app only for kitten images, this is necessary because kittens are cuter :)

[Download Cats](https://github.com/shardul/cats/releases/download/1.0/app-cats-release.apk) [Screenshot](https://github.com/shardul/cats/releases/download/1.0/cats_1.0.png)

[Download Kittens](https://github.com/shardul/cats/releases/download/1.0/app-kittens-release.apk)
[Screenshot](https://github.com/shardul/cats/releases/download/1.0/kittens_1.0.png)

## Concepts covered

1. Dependency injection
2. Data binding
3. Architecture components recommended by Google
4. Room Database
5. HTTP connections

## Tools at use

1. Android architecture components for better code architecture
2. Dagger 2 for dependency injection.
3. Android Room Database for database storage
4. Glide for image download and cache
5. Retrofit and OkHttp for network
6. Android Support and Design Libraries
7. Android Constraint layout
8. Espresso for instrumental testing

> I was always of an opinion to use minimal libraries and have maintained code for HTTP connections to database, but these are really powerful and are straight from Google, which in my opinion gives them a first class citizenship in projects. Plus the amount of boilerplate code it removes is outstanding.

## Code Packaging

* binding

Adapters to be used for data binding

* core

Hosts core controllers to use across the app

* db

Anything and everything required for Room Database, except entities.

* di

Dependency injection related classes for Dagger

* models

Models including database entities

* network

Service class for HTTP connections, response interfaces, and actual network fetch tasks.

* ui

UI Controllers including activities, fragments and respective Dagger components.

* util
Utility classes.

## Code Architecture

| CatsFragment.java ||
| ----------------- | ------- |
| UI Controllers (CatsFragment.java)||
| View Model (CatsViewModel.java)||
| Repository (CatsRepository.java)||
| Cats Dao and CatsDatabase | FetchCatsTask using CatsService|

> As suggested by Google Architecture Components, components can only access component below them and is accessed by the one on top of them


