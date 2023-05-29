val ktor_version = "1.6.7"
val logback_version = "1.2.8"
val exposed_version = "0.36.2"
val h2_version = "1.4.200"
val hikaricp_version = "5.0.0"
val postgresql_version = "42.3.1"
val ehcache_version = "3.9.7"
val testContainers_version = "1.16.3"

plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "com.example"
version = "0.0.1"
//application {
//
//    mainClass.set("com.example.Client")
//}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
}

tasks.withType<Test> {
    maxParallelForks = Runtime.getRuntime().availableProcessors() / 2
}
