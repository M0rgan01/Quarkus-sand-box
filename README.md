# quarkus-sand-box Project

## Quarkus c'est quoi ?

- _Le site du projet : https://quarkus.io/_
- _Le github du projet : https://github.com/quarkusio/quarkus_
- _Un exemple de projet : https://github.com/M0rgan01/Quarkus-sand-box_

Quarkus est un framework Java, conçu pour les machines virtuelles Java (JVM) et la compilation native.

Il tente de répondre aux problèmes liés à Java et son utilisation dans le Cloud, en concevant des applications 
légères (**subatomic**) au démarrage rapide (**supersonic**).

Il permet de **réduire d’environ 99% le temps de boot et d’environ 86% l’empreinte mémoire des applications** Java 
classiques en utilisant la compilation native proposée par GraalVM.

<p align="center">
    <img src="./img/startuptime2.jpg"
    alt="startup"
    width="100%"
    />
</p>

<p align="center">
    <img src="./img/memoryfootprint2.jpg"
    alt="memory"
    width="100%"
    />
</p>

Cette amélioration à cependant des désavantages : 
- **une augmentation du temps de la phase de build** non négligeable pour du natif. 
- Certaines **fonctionnalités ne sont pas disponibles** en exécution native, par exemple, Reflection et Java Native Interface (JNI).
  Pour plus d'infos voir https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/Limitations.md

## Des extensions ?

Plus de 90 extensions Quarkus sont disponibles et couvrent la plupart des fonctionnalités requises pour créer des applications Cloud natives :

- Web : RESTEasy JAX-RS/JSON-B, GraphQL, OpenAPI avec Swagger-UI, RESTEasy Qute…
- Données : Hibernate, JDBC, MongoDB, Neo4j, Liquibase…
- Messages asynchrones : Kafka, AMQP…
- Reactive : Vert.x, Mutiny, Reactive client (DB2, MySQL, PostgreSQL)…
- Cloud : Health, Fault Tolerance, Kubernetes, OpenShit, Amazon, Minikube…
- Observabilité : Metrics & OpenTracing par SmallRye
- Sécurité : OpenID, JSON Web Token, OAuth 2.0, Keycloak, Vault
- Intégration avec Apache Camel

Egalement une nouvelle extension « **Hibernate ORM with Panache** » simplifie l’accès à la couche de persistance en utilisant 
le design pattern Active Record sur l’entité JPA.

Une compatibilité est également possible avec l’écosystème Spring (Spring Boot, Spring Security, Spring Web, Spring Data JPA, Spring DI).
Quarkus offrant des extensions pour convertir le code Spring en code natif.

Certaines fonctionnalités sont néanmoins incompatibles :
- Le support de Spring dans Quarkus ne démarre pas le contexte. 
- Les classes et annotations Spring ne sont utilisées que pour lire les métadonnées et/ou sont utilisées comme types 
- de retour de méthodes dans le code ou de types de paramètres. Ceci implique que l’ajout d’autres librairies Spring n’aura aucun effet. 
- Les classes d’infrastructure Spring (comme org.springframework.beans.factory.config.BeanPostProcessor par exemple) ne seront pas exécutées.

## Livereload

Quarkus fait également renaître une fonctionnalité qui avait plus ou moins disparu avec l’arrivée de Spring Boot : le livereload.

Basé sur le plugin Maven io.quarkus:quarkus-maven-plugin (ou équivalent sous Gradle), la simple commande `mvn compile quarkus:dev` 
permet de lancer l’application Java en mode développement et de coder en direct sans avoir à relancer/déployer son application.

## Unification de la programmation impérative et réactive

Le modèle de développement de Quarkus se transforme pour s’adapter au type des applications que vous développez,
le framework permet d’unifier la programmation impérative et réactive.

Le développeur Java pourra ainsi dans ses services standards JAX-RS développer avec une seule API des microservices HTTP, 
des applications réactives et des applications message-driven ; seule la réponse change : trois paradigmes, une seule implémentation.

<p align="center">
    <img src="./img/Prog.png"
    alt="memory"
    width="100%"
    />
</p>


## Création d'un projet

Il est possible de créer le squelette d'une nouvelle application grâce à une interface web : [https://code.quarkus.io/](https://code.quarkus.io/)

Une fois le projet créé, un dossier `docker` est visible dans `src/main`, il contient 4 Dockerfile :

- Dockerfile.jvm -> pour l'exécutable JAR (Fast jar packaging)
- Dockerfile.legacy-jar -> pour l'exécutable JAR (packaging standard)
- Dockerfile.native -> pour l'exécutable natif
- Dockerfile.native-distroless -> contient uniquement les packages requis pour un exécutable natif

### Démarrer le project en dev mode 

Vous pouvez exécuter votre application en mode dev avec le liveCoding en utilisant :

```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Quarkus met à disposition une Dev UI, uniquement en environnement de développement sur http://localhost:8080/q/dev/.


### Packaging de l'application

L'application peut être packagée en utilisant :

```shell script
./mvnw package
```

L'application peut maintenant être démarrée en utilisant la commande suivante :

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

### Packaging de l'application (Natif)

Il est possible de créer un exécutable natif avec la commande suivante (nécessite GraalVM) :

```shell script
./mvnw package -Pnative
```

Si aucune installation de GraalVM, il est également possible de créer un exécutable natif dans un conteneur 
avec la commande suivante :

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

La commande suivante permet de démarrer votre executable natif :

```shell script
./target/quarkus-sand-box-1.0-SNAPSHOT-runner
```