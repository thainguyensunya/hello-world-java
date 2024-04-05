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

    vcsRoot(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster3)
    vcsRoot(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster1)
    vcsRoot(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster2)

    buildType(Build)
    buildType(Package_1)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster2)
    }

    steps {
        maven {
            name = "Build java app using mvn"
            id = "Maven2"
            goals = "clean compile"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    triggers {
        vcs {
            branchFilter = "+:dev"
        }
    }

    features {
        perfmon {
        }
    }
})

object Package_1 : BuildType({
    id("Package")
    name = "Package"

    vcs {
        root(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster3)
    }

    steps {
        maven {
            name = "Package java app using mvn"
            id = "Package_java_app_using_mvn"
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

object HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster1 : GitVcsRoot({
    name = "https://github.com/thainguyensunya/hello-world-java#refs/heads/master (1)"
    url = "https://github.com/thainguyensunya/hello-world-java"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "thainguyensunya"
        password = "credentialsJSON:bc09458d-2cc2-4134-b432-9103ac892b7f"
    }
})

object HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster2 : GitVcsRoot({
    name = "https://github.com/thainguyensunya/hello-world-java#refs/heads/master (2)"
    url = "https://github.com/thainguyensunya/hello-world-java"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "thainguyensunya"
        password = "credentialsJSON:bc09458d-2cc2-4134-b432-9103ac892b7f"
    }
})

object HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster3 : GitVcsRoot({
    name = "https://github.com/thainguyensunya/hello-world-java#refs/heads/master (3)"
    url = "https://github.com/thainguyensunya/hello-world-java"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "thainguyensunya"
        password = "credentialsJSON:bc09458d-2cc2-4134-b432-9103ac892b7f"
    }
})
