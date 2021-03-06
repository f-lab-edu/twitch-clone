plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"

    id("io.gitlab.arturbosch.detekt").version("1.19.0")
}

group = "com.example"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Project dependencies
    implementation(project(":module-user"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-noarg")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(testFixtures(project(":module-user")))

    runtimeOnly("io.gitlab.arturbosch.detekt:detekt-api:1.19.0")
    runtimeOnly("com.h2database:h2")
}

tasks.register("prepareKotlinBuildScriptModel") {

}

afterEvaluate {
    detekt {
        buildUponDefaultConfig = true
        config.setFrom(files("$rootDir/detekt-config.yml"))
    }
}
