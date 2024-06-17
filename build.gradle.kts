buildscript {

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(libs.kotlin.gradle)
        classpath(libs.dokka.gradle)
        classpath(libs.maven.publish.gradle)
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}
