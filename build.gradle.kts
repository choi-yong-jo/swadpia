//import com.ewerk.gradle.plugins.tasks.QuerydslCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "3.2.1-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.hibernate.orm") version "6.3.1.Final"
    id("org.graalvm.buildtools.native") version "0.9.28"
//    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
}

group = "kr.co.swadpia"
//version = ""

val queryDslVersion = "5.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    //aop추가
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.mobile:spring-mobile-starter:2.0.0.M3")
    implementation("com.auth0:java-jwt:3.19.2")
    implementation("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")

    // add swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    // add apache org.apache.commons commons-lang3
    implementation("org.apache.commons:commons-lang3:3.12.0")
    // add minio v4
    implementation("io.minio:minio:4.0.0")
    // add ImageIO
//    implementation("javax.media:jai_imageio:1.1")
    // add net.coobird thumbnailator
    implementation("net.coobird:thumbnailator:0.4.14")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
    // add FilenameUtils
    implementation("org.apache.commons:commons-io:1.3.2")
    // add JSONObject
    // add SchedulerLock
    implementation("net.javacrumbs.shedlock:shedlock-spring:4.23.0")
    // add org.json.simple.parser.JSONParser
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    // add org.apache.commons commons-collections4
    implementation("org.apache.commons:commons-collections4:4.4")

    //add org.apache.poi
    implementation("org.apache.poi:poi:5.2.5")
    implementation("org.apache.poi:poi-ooxml:5.2.5")
    // redis provider
    implementation("net.javacrumbs.shedlock:shedlock-provider-redis-spring:4.27.0")
    // sql log
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")
    implementation("org.reflections:reflections:0.9.12")
    // popbill
    implementation ("kr.co.linkhub:popbill-spring-boot-starter:1.13.0")
    // postgresql connector
    implementation("org.postgresql:postgresql:42.3.1")

    // Spring Boot Data Elasticsearch
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

    // QR Code Zxing
    implementation("com.google.zxing:core:3.4.1")
    implementation("com.google.zxing:javase:3.4.1")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta")

    annotationProcessor ("jakarta.persistence:jakarta.persistence-api")
    annotationProcessor ("jakarta.annotation:jakarta.annotation-api")
    compileOnly("org.projectlombok:lombok")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.projectlombok:lombok")
    testImplementation("org.springframework.security:spring-security-test")
}

//tasks.withType<Test> {
//    useJUnitPlatform()
//}

tasks.clean {
    delete("build")
}

hibernate {
    enhancement {
        enableAssociationManagement.set(true)
    }
}

