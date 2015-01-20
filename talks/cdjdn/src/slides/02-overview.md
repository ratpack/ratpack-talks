# Overview

## What is Ratpack?

Ratpack is a set of Java libraries that facilitate fast, efficient, evolvable and well tested HTTP applications.

It is built on the highly performant and efficient Netty event-driven networking engine.

Ratpack focuses on allowing HTTP applications to be efficient, modular, adaptive to new requirements and technologies, and well-tested over time.

Ratpack is a new project, but stands on the shoulders of well established technologies and is built by developers with substantial experience in web application frameworks and tooling.
It is currently pre-1.0 but under very active development.

## Modularity

* A set of composable libraries for building un-opinionated, rich web apps
* Pick-and-choose what aspects of the framework you want
* No lock-in to a single way of doing things
* No plugins; modularity through dependency-injected modules
    * DI supported via a Registry abstraction
    * Out-of-the-box integration with Guice (preferred) and Spring

## Current Modules

* Asynch: reactor, rx
* Authentication: pac4j
* Build/Packaging: gradle
* Common: config, session
* Database: h2, hikari
* Dependency Injection: guice, spring-boot
* JSON: jackson
* Language: groovy
* Reliability: hystrix, codehale-metrics, newrelic
* Templates: handlebars, thymeleaf, groovy
* Testing: test, groovy-test

## Gradle Java Build

    buildscript {
      repositories { jcenter() }
      dependencies {
        classpath "io.ratpack:ratpack-gradle:0.9.11"
      }
    }
    apply plugin: "io.ratpack.ratpack-java"
    repositories { jcenter() }

## Gradle Groovy Build

    buildscript {
      repositories { jcenter() }
      dependencies {
        classpath "io.ratpack:ratpack-gradle:0.9.11"
      }
    }
    apply plugin: "io.ratpack.ratpack-groovy"
    repositories { jcenter() }

## More Features

* A full-stack, high throughput, non-blocking web framework
* Built entirely on Java 8, based on Netty


* Emphasis on Performance and Efficiency
    * Both in runtime and development experience
    * Hot-reloading available during development time
    * Emphasis on developer testing, especially functional and integration
* Specialized support for writing apps in Groovy
    * Why Groovy?
    * Similar performance to Java using Invoke Dynamic or Static Compilation
    * Supports static compilation, compile-time type checking, dynamic typing
    * Can pick-and-choose between these modes depending on the use case
