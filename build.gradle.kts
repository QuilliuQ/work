import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "mgs.bet"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val koin_version = "3.2.0"
    implementation(fileTree("lib"))
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("org.json:json:20220320")
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}