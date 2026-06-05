
plugins {
    java
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.palantir.git-version") version "3.1.0"
}


group = "io.github.gleidsonmt"

// Get the version from git tags
val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
val details = versionDetails()
version = onlyNumberAndDots(details.lastTag)

fun onlyNumberAndDots(value: String): String {
    return value.replace(Regex("[^0-9.]"), "")
}

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
