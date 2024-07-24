plugins {
    id("java")
}

group = "com.jotrorox"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.1")
    implementation("ch.qos.logback:logback-classic:1.5.6")

    // Needed for the JDA library
    implementation("net.sf.trove4j:trove4j:3.0.3")
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

tasks.register("fatJar", Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "com.jotrorox.Jonas"
    }
    archiveBaseName.set(rootProject.name)
    from({
        configurations.compileClasspath.get().filter { it.exists() }.map { if (it.isDirectory) it else zipTree(it) }
    })
    with(tasks.named("jar").get() as CopySpec)
}