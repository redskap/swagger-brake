# Gradle Plugin
## Installation
Using the Gradle plugin is easy. Similarly, to any other plugins, you just need apply the plugin in your `build.gradle`.

With plugins DSL:
```groovy
plugins {
  id "io.redskap.swagger-brake" version "2.1.0"
}
```

Or legacy plugin application:
```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "io.redskap:swagger-brake-gradle:2.1.0"
  }
}

apply plugin: "io.redskap.swagger-brake"
```

## Basics
swagger-brake is hooked into the `check` task, and the goal that swagger-brake uses is called `checkBreakingChanges`, so 
after configuring the plugin the only thing left is to configure a few settings to the plugin, the `newApi` and the
`mavenRepoUrl` parameters:

Example:
```groovy
...
swaggerBrake {
    newApi = "${project.buildDir}/resources/main/swagger.yaml"
    mavenRepoUrl = "${REPO_URL}/artifactory/libs-release-local"
}
...
```

Executing a simple `gradle clean build` command will result in the following output:
```bash
...
> Task :checkBreakingChanges
No breaking API changes detected
...
```

Of course in case this is the first time the artifact is getting built, swagger-brake will notice that
and skip the check:

```bash
> Task :checkBreakingChanges
Latest version of the artifact could not be retrieved from http://${REPO_URL}/artifactory/libs-release-local with io.redskap.example:swagger-brake-gradle-example
Assuming this is the first version of the artifact, skipping check for breaking changes
```

## Customizing reporting
The custom reporting functionality is described in the 
[Customizing reporting](../configuration/README.md#customizing-reporting) section.

By default, there's going to be console reporting enabled as well as HTML. At the end of the execution, you should
see the HTML report generated under the `build/swagger-brake` folder. For reconfiguring the target directory, use the
`outputFilePath` parameter.

Overriding the reporting configuration is simple. In the plugin configuration, just set the `outputFormats` attribute with
an array of values.

Example:
```groovy
...
swaggerBrake {
    newApi = "${project.buildDir}/resources/main/swagger.yaml"
    mavenRepoUrl = "${REPO_URL}/artifactory/libs-release-local"
    outputFormats = ['HTML', 'JSON']
}
...
```

## Deprecating APIs
The API deprecation support is described in the 
[Deprecating APIs](../configuration/README.md#deprecating-apis) section.

Overriding the default deprecation rule - which is to allow the deletion of deprecated APIs, just like for the CLI - 
has never been easier, just set the `deprecatedApiDeletionAllowed` value to `false`:

Example:
```groovy
...
swaggerBrake {
    newApi = "${project.buildDir}/resources/main/swagger.yaml"
    mavenRepoUrl = "${REPO_URL}/artifactory/libs-release-local"
    deprecatedApiDeletionAllowed = false
}
...
```

## Latest Maven artifact resolution
Latest Maven artifact resolution is described in detail within the 
[Latest Maven artifact resolution](../configuration/README.md#latest-maven-artifact-resolution) section.

By default, most of the configuration values are picked up from the project configuration itself, meaning that
you don't need to set the `groupId`, `artifactId`, `currentVersion` attributes. Respectively they will
use the project's `groupId`, `artifactId` and `version` values. However, in case there's a need to override
any of those, you can do that within the configuration.

There's a single setting you got to provide for the resolution to work, either `mavenRepoUrl` or `mavenSnapshotRepoUrl`.
Respectively they represent the release and snapshot repositories that holds the previous version of your artifact.

In addition, you can configure authentication to your repository with the `mavenRepoUsername` and `mavenRepoPassword`
options.

Example configuration with authentication:
```groovy
...
swaggerBrake {
    newApi = "${project.buildDir}/resources/main/swagger.yaml"
    mavenRepoUrl = "${REPO_URL}/artifactory/libs-release-local"
    mavenRepoUsername = 'admin'
    mavenRepoPassword = 'artifactory'
}
...
```

## Beta API support
For further reference, check out [Beta API support](../configuration/README.md#beta-api-support) 
in the Configuration section.

For overriding the attribute that is used for denoting beta APIs, use the `betaApiExtensionName` configuration.

Example:
```groovy
...
swaggerBrake {
    newApi = "${project.buildDir}/resources/main/swagger.yaml"
    mavenRepoUrl = "${REPO_URL}/artifactory/libs-release-local"
    betaApiExtensionName = 'x-something'
}
...
```

## Excluding paths from the scan
For detailed description on the feature, see [Excluding paths from the scan](../configuration/README.md#excluding-paths-from-the-scan).

Similarly, to other configurations, use the `excludedPaths` parameter.

Example:
```groovy
...
swaggerBrake {
    newApi = "${project.buildDir}/resources/main/swagger.yaml"
    mavenRepoUrl = "${REPO_URL}/artifactory/libs-release-local"
    excludedPaths = ['/auth']
}
...
```

## Default parameter values

| Parameter                  | Default value                              |
|:--------------------------:|:------------------------------------------:|
| `outputFormats`            | `HTML`                                     |
| `outputFilePath`           | `${project.buildDir}/swagger-brake`        |
| `groupId`                  | `${project.groupId}`                       |
| `artifactId`               | `${project.artifactId}`                    |
| `currentArtifactVersion`   | `${project.version}`                       |

## Full list of parameters
| Parameter                  | Description                                                                                                                                               |
|:--------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------:|
| `oldApi`                   | Denotes the path of the baseline API. Can be a relative path and an absolute one.                                                                         |
| `newApi`                   | Denotes the path of the new, changed API. Can be a relative path and an absolute one.                                                                     |
| `outputFormats`            | Specifies which reports shall be generated. Possible values: `STDOUT`, `JSON`, `HTML`                                                                     |
| `outputFilePath`           | Denotes the folder where the file reports shall be saved. Can be a relative path and an absolute one. In case the path doesn't exist, it will be created. |
| `mavenRepoUrl`             | Specifies the release repository base URL. Might be optional in case `mavenSnapshotRepoUrl` is provided.                                                  |
| `mavenSnapshotRepoUrl`     | Specifies the snapshot repository base URL. Might be optional in case `mavenRepoUrl` is provided.                                                         |
| `mavenRepoUsername`        | The username for the Maven repository.                                                                                                                    |
| `mavenRepoPassword`        | The password for the Maven repository.                                                                                                                    |
| `groupId`                  | The groupId of the artifact.                                                                                                                              |
| `artifactId`               | The artifactId of the artifact.                                                                                                                           |
| `currentArtifactVersion`   | The version of the artifact that contains the new API. This is used to determine if the snapshot, or the release repository needs to be used.             |
| `apiFilename`              | The filename to search for within the artifact.                                                                                                           |
| `betaApiExtensionName`     | The name of the custom vendor extension attribute that denotes beta APIs.                                                                                 |
| `excludedPaths`            | A  list of path prefixes that shall be excluded from the scan.                                                                                            |