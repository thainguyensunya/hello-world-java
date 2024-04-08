import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

version = "2024.03"

project {

    vcsRoot(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster)

    buildType(Build)
    buildType(Package)
    buildType(Deploy)

    sequential {
        buildType(Build)
        buildType(Package)
        buildType(Deploy)
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster)
    }

    steps {
        maven {
            name = "Build java app using mvn"
            id = "Maven"
            goals = "clean compile"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    features {
        perfmon {
        }
    }
})

object Package : BuildType({
    id("Package")
    name = "Package"
    artifactRules = "target/*.jar"

    vcs {
        root(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster)
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
            branchFilter = "+:test"
        }
    }

    features {
        perfmon {
        }
    }
})

object Deploy : BuildType({
    id("Deploy")
    name = "Deploy"

    params {
        password("env.AWS_SECRET_ACCESS_KEY", "credentialsJSON:178c777e-0fb8-4d69-a5af-76c7bdb5c8c2")
        password("env.AWS_ACCESS_KEY_ID", "credentialsJSON:a7938976-7bc6-4bcb-94e2-172540860d7f")
    }

    vcs {
        root(HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster)
    }

    steps {
        script {
            name = "Deploy jar file to S3"
            id = "Deploy_jar_file_to_S3"
            scriptContent = "aws s3 cp *.jar s3://teamcity-demo-hello-world-app/"
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

    dependencies {
        artifacts(Package) {
            artifactRules = "gs-maven-*.jar"
        }
    }
})

object HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/thainguyensunya/hello-world-java#refs/heads/master"
    url = "https://github.com/thainguyensunya/hello-world-java"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "thainguyensunya"
        password = "credentialsJSON:bc09458d-2cc2-4134-b432-9103ac892b7f"
    }
})
