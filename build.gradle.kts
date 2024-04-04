plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

group = "com.sheu"
version = "1.0-SNAPSHOT"

dependencies {
    compileOnly("io.confluent:kafka-connect-jdbc:10.7.4")
    compileOnly("org.apache.kafka:connect-api:2.8.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}