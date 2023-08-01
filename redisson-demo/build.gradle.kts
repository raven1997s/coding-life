plugins {
    java
    id("org.springframework.boot") version "2.3.8.RELEASE"
    id("io.spring.dependency-management") version "1.1.2"
    id("com.google.osdetector") version "1.7.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    java.sourceCompatibility = JavaVersion.VERSION_1_8
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.alibaba:fastjson:2.0.28")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.3.8.RELEASE")
    implementation("org.apache.commons:commons-pool2:2.8.1")
    implementation("org.redisson:redisson-spring-boot-starter:3.20.1")
    implementation("de.ruedigermoeller:fst:2.57")

    // 解决mac环境报错 id("com.google.osdetector") version "1.7.0"  jahva.lang.ClassNotFoundException: io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider
    implementation(group = "io.netty",name = "netty-resolver-dns-native-macos", version = "4.1.70.Final", classifier = "osx-aarch_64")
    if (osdetector.arch.equals("aarch_64")) {
        implementation("io.netty:netty-all")
    }

}

tasks.withType<Test> {
    useJUnitPlatform()
}
