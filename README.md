# API Rest Spring Boot

## Description
Bienvenue dans ce projet d'API REST développé avec Spring Boot. Cette API permet, au travers de requête REST la gestion de joueurs et d'équipes de football.

## Prérequis
Avant de commencer, assurez-vous d'avoir installé les éléments suivants :
- [Java Developpment Kit](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Installation

### Production

1. Téléchargez le fichier JAR (Java archive) depuis le lien suivant : 
    -  [Release v1.0.0 (:sparkles: Macarena :sparkles:)](https://github.com/Andreaj42/API-REST/releases/tag/v1.0.0(Macarena))


2. Puis, exécutez le JAR 

   ```bash
    java -Dfile.encoding=UTF-8 -jar td_springboot-0.0.1-SNAPSHOT.jar
   ```

### Développement
1. Clonez le dépôt :
    ```bash
    git clone https://github.com/Andreaj42/API-REST.git
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

L'API devrait maintenant être accessible à l'adresse [http://localhost:8080/api/v1](http://localhost:8080/api/v1).

 
## Exemple d'utilisation

- Endpoint : `GET /api/v1/example`
- Description : Récupère la liste des équipes disponibles.
    ```bash
    curl http://localhost:8080/api/v1/teams
    ```

## Exemple d'utilisation via Axios
Dans le dossier scripts, il existe des scripts Node.js d'exemples d'utilisation de l'API.
Assurez-vous d'utiliser les commandes suivantes pour exécuter ces scripts.
   ```bash
    npm install
    node script/getTeams.js
   ``` 
