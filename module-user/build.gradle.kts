plugins {
    kotlin("jvm") version "1.6.10"
    id("io.gitlab.arturbosch.detekt").version("1.19.0")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

dependencies {
    implementation(kotlin("stdlib"))

    // TODO 추후 버전 및 공통 dependencies 분리
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.assertj:assertj-core:3.21.0")

    // detekt
    runtimeOnly("io.gitlab.arturbosch.detekt:detekt-api:1.19.0")
}

afterEvaluate {
    detekt {
        buildUponDefaultConfig = true
        config.setFrom(files("$rootDir/detekt-config.yml"))
    }
}
