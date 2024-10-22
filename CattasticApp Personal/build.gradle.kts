buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.google.android.gms:play-services-auth:21.2.0@aar")

    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
   // api ("androidx.security:security-crypto:1.0.0")
}
dependencies {
    api ("androidx.security:security-crypto:1.0.0")
}

fun api(s: String) {

}
