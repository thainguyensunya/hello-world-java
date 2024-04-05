import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.03"

project {

    vcsRoot(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster4)

    buildType(Build)
}

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

object HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster4 : GitVcsRoot({
    name = "https://github.com/thainguyensunya/hello-world-java#refs/heads/master (4)"
    url = "https://github.com/thainguyensunya/hello-world-java"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "thainguyensunya"
        password = "credentialsJSON:bc09458d-2cc2-4134-b432-9103ac892b7f"
    }
})
