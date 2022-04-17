plugins {
    kotlin("jvm") version "1.6.10"

    id("io.gitlab.arturbosch.detekt").version("1.19.0")
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

    runtimeOnly("io.gitlab.arturbosch.detekt:detekt-api:1.19.0")
}

afterEvaluate {
    detekt {
        buildUponDefaultConfig = true
        config.setFrom(files("$rootDir/detekt-config.yml"))
    }
}
