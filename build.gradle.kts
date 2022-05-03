plugins {
    `java-library`
    id("io.izzel.taboolib") version "1.38"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
}

taboolib {
    description {
        dependencies {
            name("DragonCore")
            name("KirraCoreBukkit")
            name("Adyeshach")
            name("Skript").optional(true)
        }
        contributors {
            name("闲蛋")
        }
    }
    install("common")
    install("common-5")
    install("module-configuration")
    install("module-chat")
    install("module-database")
    install("module-lang")
    install("module-nms")
    install("module-kether")
    install("module-nms-util")
    install("platform-bukkit")
    install("expansion-command-helper")
    classifier = null
    version = "6.0.7-52"
}

repositories {
    maven {
        credentials {
            username = "a5phyxia"
            password = "zxzbc13456"
        }
        url = uri("https://maven.ycraft.cn/repository/maven-snapshots/")
    }
    maven { url = uri("https://repo.tabooproject.org/repository/maven-releases/") }
    maven { url = uri("https://repo.skriptlang.org/releases") }
    mavenCentral()
}

dependencies {
    compileOnly("com.github.SkriptLang:Skript:2.6.1")
    compileOnly("ink.ptms:Adyeshach:1.5.7@jar")
    compileOnly("ink.ptms.core:v11200:11200")
    compileOnly("net.sakuragame.eternal:KirraCore-Bukkit:1.2.3-SNAPSHOT@jar")
    compileOnly("net.sakuragame.eternal:DragonCore:2.4.8-SNAPSHOT@jar")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}