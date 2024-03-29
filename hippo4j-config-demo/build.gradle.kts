plugins {
    java
    id("org.springframework.boot") version "2.3.8.RELEASE"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("io.micrometer:micrometer-registry-prometheus")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("cn.hippo4j:hippo4j-config-spring-boot-starter:1.5.0")
	implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:2.2.7.RELEASE")
    implementation("com.alibaba:fastjson:2.0.28")

//    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2.2.7.RELEASE")
//    implementation("com.alibaba.nacos:nacos-client:2.0.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
