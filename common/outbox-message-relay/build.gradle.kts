dependencies {
    implementation(project(":common:snowflake"))
    implementation(project(":common:event"))
    implementation(project(":common:data-serializer"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.kafka:spring-kafka")
    runtimeOnly("com.mysql:mysql-connector-j")
}