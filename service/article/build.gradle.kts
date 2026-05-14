dependencies {
    implementation(project(":common:snowflake"))
    implementation(project(":service:api-docs-ui"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    runtimeOnly("com.mysql:mysql-connector-j")
    testRuntimeOnly("com.h2database:h2")
}