plugins {
    kotlin("jvm") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    // TODO 추후 버전 및 공통 dependencies 분리
    implementation("org.apache.logging.log4j:log4j:2.17.1")
}
