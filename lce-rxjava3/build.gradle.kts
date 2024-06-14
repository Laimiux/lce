plugins {
    id("kotlin")
    id("org.jetbrains.dokka")
}

apply {
    from("$rootDir/.buildscript/configure-signing.gradle")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
kotlin {
    jvmToolchain(17)
}

dependencies {
    api(project(":lce"))
    api(libs.rxjava)

    testImplementation(libs.truth)
    testImplementation(libs.junit)
}
