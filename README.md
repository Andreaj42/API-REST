# Spring Mootse

## Description
Bienvenue dans le projet Spring Mootse, une API développée avec Spring Boot. Cette API permet, au travers de requête REST la gestion de joueurs et d'équipes de football.

## Prérequis
Avant de commencer, assurez-vous d'avoir installé les éléments suivants :
- [Java Developpment Kit](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Installation

1. Clonez le dépôt :
    ```bash
    git clone https://devops.telecomste.fr/joly.andrea/td-rest-api.git
    cd td-rest-api
    ```

2. Compilez le projet avec Maven :
    ```bash
    mvn clean install
    ```

3. Exécutez l'application :
    ```bash
    java -jar target/td-rest-api.jar
    ```

L'API devrait maintenant être accessible à l'adresse [http://localhost:8080](http://localhost:8080).


## Exemple d'utilisation

- Endpoint : `GET /api/v1/exemple`
- Description : Récupère la liste des équipes disponibles.

    ```bash
    curl http://localhost:8080/api/v1/teams
    ```