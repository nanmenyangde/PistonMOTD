import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("pm.java-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    jar {
        archiveClassifier.set("unshaded")
        from(project.rootProject.file("LICENSE"))
    }

    shadowJar {
        exclude("META-INF/SPONGEPO.SF", "META-INF/SPONGEPO.DSA", "META-INF/SPONGEPO.RSA")
        minimize()
        configureRelocations()
    }

    build {
        dependsOn(shadowJar)
    }
}


fun ShadowJar.configureRelocations() {
    relocate("io.papermc.lib", "net.pistonmaster.pistonmotd.shadow.paperlib")
    relocate("org.apache", "net.pistonmaster.pistonmotd.shadow.apache")
    relocate("org.bstats", "net.pistonmaster.pistonmotd.shadow.bstats")
    relocate("net.pistonmaster.pistonutils", "net.pistonmaster.pistonmotd.shadow.pistonutils")
    relocate("net.skinsrestorer.axiom", "net.pistonmaster.pistonmotd.shadow.axiom")
    relocate("org.yaml.snakeyaml", "net.pistonmaster.pistonmotd.shadow.snakeyaml")
    relocate("org.intellij.lang.annotations", "net.pistonmaster.pistonmotd.shadow.annotations.intellij")
    relocate("org.jetbrains.annotations", "net.pistonmaster.pistonmotd.shadow.annotations.jetbrains")
}
