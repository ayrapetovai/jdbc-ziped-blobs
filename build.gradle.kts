plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-compress:1.18")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation("org.postgresql:postgresql:42.3.2")

    testImplementation("net.steppschuh.markdowngenerator:markdowngenerator:1.3.1.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.testcontainers:postgresql:1.16.3")
    testImplementation("org.testcontainers:junit-jupiter:1.16.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}