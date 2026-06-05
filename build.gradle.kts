plugins {
    java
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "io.github.gleidsonmt"

repositories {
    mavenCentral()
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

javafx {
    version = "23.0.2"
    modules("javafx.controls", "javafx.fxml", "javafx.web", "javafx.swing")
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.1.0")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
