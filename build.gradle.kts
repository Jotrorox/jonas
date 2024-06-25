plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "com.jotrorox.jonas"
version = "0.1"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation("dev.kord:kord-core:0.14.0")
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "com.jotrorox.jonas.MainKt")
    }
}

tasks.shadowJar {
    manifest {
        attributes("Main-Class" to "com.jotrorox.jonas.MainKt")
    }
}