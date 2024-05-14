import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val coroutinesVersion by extra { "1.8.0" }
val mockitoKotlinVersion by extra { "4.1.0" }
val springShellVersion by extra { "3.2.4" }

plugins {
	val pluginsVersions = object {
		val springBootVersion = "3.2.5"
		val springDependencyManagementVersion = "1.1.4"
		val gradleKtlintVersion = "12.1.1"
		val kotlinVersion = "1.9.23"
		val pluginSpringVersion = "1.9.23"
	}

	id("org.springframework.boot") version pluginsVersions.springBootVersion
	id("io.spring.dependency-management") version pluginsVersions.springDependencyManagementVersion
	id("org.jlleitschuh.gradle.ktlint") version (pluginsVersions.gradleKtlintVersion)
	kotlin("jvm") version pluginsVersions.kotlinVersion
	kotlin("plugin.spring") version pluginsVersions.pluginSpringVersion
}

group = "com.example.kotlin.springboot"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.shell:spring-shell-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.shell:spring-shell-starter-test")

	implementation("org.springframework.retry:spring-retry")

	// Kotlinx (coroutines)
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.mockito:mockito-core")
	testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.shell:spring-shell-dependencies:${property("springShellVersion")}")
	}
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
