# Maven Plugin
## Installation

## Basics

## Customizing reporting
The custom reporting functionality is described in the 
[Customizing reporting](../configuration/README.md#customizing-reporting) section.

## Deprecating APIs
The API deprecation support is described in the 
[Deprecating APIs](../configuration/README.md#deprecating-apis) section.

## Latest Maven artifact resolution
Latest Maven artifact resolution is described in detail within the 
[Latest Maven artifact resolution](../configuration/README.md#latest-maven-artifact-resolution) section.

## Beta API support
For further reference, check out [Beta API support](../configuration/README.md#beta-api-support) 
in the Configuration section.

## Excluding paths from the scan
For detailed description on the feature, see [Excluding paths from the scan](../configuration/README.md#excluding-paths-from-the-scan).

## Full list of parameters
`<oldApi>`: Denotes the path of the baseline API. Can be a relative path and an absolute one.<br>
`<newApi>`: Denotes the path of the new, changed API. Can be a relative path and an absolute one.<br>
`<outputFormats>`: Specifies which reports shall be generated. Possible values: `STDOUT`, `JSON`, `HTML`<br>
`<outputPath>`: Denotes the folder where the file reports shall be saved. Can be a relative path and an absolute one. 
In case the path doesn't exist, it will be created.<br>
`<mavenRepoUrl>`: Specifies the release repository base URL. Might be optional in case `<mavenSnapshotRepoUrl>` 
is provided.<br>
`<mavenSnapshotRepoUrl>`: Specifies the snapshot repository base URL. Might be optional in case `<mavenRepoUrl>` 
is provided.<br>
`<groupId>`: The groupId of the artifact.<br>
`<artifactId>`: The artifactId of the artifact.<br>
`<currentArtifactVersion>`: The version of the artifact that contains the new API. This is used to determine if the 
snapshot, or the release repository needs to be used.<br>
`<apiFilename>`: The filename to search for within the artifact.<br> 
`<mavenRepoUsername>`: The username for the Maven repository.<br>
`<mavenRepoPassword>`: The password for the Maven repository.<br>
`<betaApiExtensionName>`: The name of the custom vendor extension attribute that denotes beta APIs.<br>
`<excludedPaths>`: A comma separated list of path prefixes that shall be excluded from the scan.<br>