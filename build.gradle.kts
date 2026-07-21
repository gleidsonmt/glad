plugins {
    java
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "io.github.gleidsonmt"
version = "1.1.225"

repositories {
    mavenCentral()
}

val junitVersion = "5.10.2"

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

javafx {
    version = "25.0.3"
    modules = listOf(
        "javafx.controls",
        "javafx.fxml",
        "javafx.web",
        "javafx.swing"
    )
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}