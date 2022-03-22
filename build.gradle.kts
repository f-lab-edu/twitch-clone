plugins {
    kotlin("jvm") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
}

allprojects {
    tasks.withType<Test> { useJUnitPlatform() }
}
