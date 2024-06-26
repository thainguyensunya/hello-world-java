package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster1'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, GitVcsRoot({
    id("HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster1")
    name = "https://github.com/thainguyensunya/hello-world-java#refs/heads/master (1)"
    url = "https://github.com/thainguyensunya/hello-world-java"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/master"
    authMethod = password {
        userName = "thainguyensunya"
        password = "credentialsJSON:bc09458d-2cc2-4134-b432-9103ac892b7f"
    }
}))

