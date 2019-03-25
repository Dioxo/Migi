# Migi

Migi is a system that allows you to take notes of whatever you need at all times, plus it has a system that notifies you when you are more likely to forget any of these notes.
This project uses the theory of the spaced review so that you get the best results when studying and do not have to worry about forgetting something.

*For more information about:*

The spaced review, visit -> https://en.wikipedia.org/wiki/Spaced_repetition

The algorithm used, visit the following link -> https://www.supermemo.com/english/ol/sm2.htm

I also recommend to see the application of [anki] (https://apps.ankiweb.net) that served as inspiration for this project, its documentation is very complete about the spacing  repetition system and different algorithms that optimize the task of memorizing

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You will need Android Studio, a server running with MySQL, PHP and Nginx or Apache.

*You can find more information on how to install this environment known as LAMP or LEMP (depending on the server you wish to use) in the following link:*

[LAMP](https://www.digitalocean.com/community/tutorials/comment-installer-la-pile-linux-apache-mysql-php-lamp-sur-un-serveur-ubuntu-18-04-fr)

Or

[LEMP]
(https://www.digitalocean.com/community/tutorials/how-to-install-linux-nginx-mysql-php-lemp-stack-ubuntu-18-04)


### Installing

Start by cloning the repository
```
git clone https://github.com/Dioxo/Migi.git
```

Inside you will find everything you need to be able to work with Android Studio, in addition to the files that I used to link my BD with the application.
You can find the files in the android project folder
/main/java/dioxo/migi/Objets/PHP_Request

In addition, I have included the creation file of the BD (Migi.sql) that you will also find in the same folder as the PHP files mentioned above.


## Deployment

You can use the [postman](https://www.getpostman.com) application  to prove that your php files are working well on your server, or simply by compiling the application on your cell phone and trying to create a new user to confirm that everything works well in your development environment

## Versioning

Para el versionamiento, he usado el sistema git con el modelo de ramas descrito en el siguiente link : https://nvie.com/posts/a-successful-git-branching-model/

## Authors

* **Dioxo**

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE see the [LICENSE.md](LICENSE.md) file for details
