package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster4)
    }
    steps {
        maven {
            name = "Build and test the app"
            id = "Build_and_test_the_app"
            goals = "clean test"
        }
        maven {
            name = "Build and package the app"
            id = "Build_and_package_the_app"
            goals = "package"
        }
    }
    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

