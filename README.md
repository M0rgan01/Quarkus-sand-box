# quarkus-sand-box Project

## Quarkus c'est quoi ?

Quarkus est un framework Java, conçu pour les machines virtuelles Java (JVM) et la compilation native.

Il tente de répondre aux problèmes liés à Java et son utilisation dans le Cloud, comme des applications légéres au démarrage rapide.
Il permet de **réduire d’environ 99% le temps de boot et d’environ 86% l’empreinte mémoire des applications** Java classiques en utilisant la compilation native proposée par GraalVM.

<p align="center">
    <img src="./img/memoryfootprint2.jpg"
    alt="memory"
    width="100%"
    />
</p>

<p align="center">
    <img src="./img/startuptime2.jpg"
    alt="startup"
    width="100%"
    />
</p>

Cette amélioration à cependant un coup : **une augmentation du temps de la phase de build** non négligeable pour du natif. 

Quarkus fait également renaître une fonctionnalité qui avait plus ou moins disparu avec l’arrivée de Spring Boot : le livereload.

---

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-sand-box-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html
.

## Related Guides

- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
