import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "com.mattmx"
version = "-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.10")
    implementation("net.kyori:adventure-api:4.12.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.12.0")
    implementation("net.kyori:adventure-text-serializer-gson:4.12.0")
    implementation("net.benwoodworth.knbt:knbt:0.11.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}