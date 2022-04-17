plugins {
    kotlin("jvm") version "1.6.10"

    id("io.gitlab.arturbosch.detekt").version("1.19.0")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly("io.gitlab.arturbosch.detekt:detekt-api:1.19.0")
}

allprojects {
    tasks.withType<Test> { useJUnitPlatform() }
}

afterEvaluate {
    detekt {
        buildUponDefaultConfig = true
        config.setFrom(files("$rootDir/detekt-config.yml"))
    }
}
