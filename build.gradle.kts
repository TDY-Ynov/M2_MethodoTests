plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.6"
	id("io.spring.dependency-management") version "1.1.6"
	id("jacoco")
	id("info.solidsoft.pitest") version "1.15.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
	testImplementation("io.kotest:kotest-assertions-core:5.9.1")
	testImplementation("io.kotest:kotest-property:5.9.1")
	testImplementation("io.mockk:mockk:1.13.13")
}

tasks.withType<Test> {
	useJUnitPlatform()

	jvmArgs = listOf(
		"--add-opens=java.base/sun.util.resources.cldr.provider=ALL-UNNAMED",
		"--add-opens=java.base/java.util=ALL-UNNAMED"
	)

	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)

	reports {
		xml.required.set(true)
		html.required.set(true)
	}

	doLast {
		val excludes = listOf("sun/.*", "java/.*")
	}
}

pitest {
	targetClasses.set(listOf("books_tp.domain.usecase.*"))
	targetTests.set(listOf("books_tp.domain.usecase.*Test"))
	junit5PluginVersion.set("1.1.0")
	testPlugin.set("junit5")

	mutationThreshold.set(75)

	reportDir.set(file("$buildDir/reports/pitest"))
}

