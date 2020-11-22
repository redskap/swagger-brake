# Command-line interface
## Basics
The idea of the command-line interface is to provide a way to integrate swagger-brake into
any type of system, pipeline. There are several configuration parameters available that you 
could use to customize the behavior of swagger-brake.

The most basic usage is to compare 2 Swagger files. 

```bash
$ java -jar swagger-brake.jar --old-api=swagger.json --new-api=swagger.json
Loading old API from swagger.json
Loading new API from swagger.json
Successfully loaded APIs
Starting the check for breaking API changes
Check is finished
No breaking API changes detected
```

## Customizing reporting
The custom reporting functionality is described in the 
[Customizing reporting](../configuration/README.md#customizing-reporting) section.

Note that whatever custom reporting type you set for the command-line interface, console
reporting will always be enabled. 

To configure a custom reporting, the CLI accepts the `--output-formats` argument. The parameter
accepts a comma separated list of reporting formats, in case you want to use multiple reporters.

For file typed reporters like JSON or HTML, you must also pass the `--output-path` argument 
denoting the location where the reports should be saved.

An example configuration could look the following:
```bash
$ java -jar swagger-brake.jar --old-api=swagger.json --new-api=swagger2.json --output-formats=STDOUT,JSON,HTML
```

## Deprecating APIs
The API deprecation support is described in the 
[Deprecating APIs](../configuration/README.md#deprecating-apis) section.

The `--deprecated-api-deletion-allowed` parameter is responsible for setting this behavior. 
The default value is `true`, so **there's no additional configuration is required**, 
but it can be overridden to `false` anytime.

## Latest Maven artifact resolution
Latest Maven artifact resolution is described in detail within the 
[Latest Maven artifact resolution](../configuration/README.md#latest-maven-artifact-resolution) section.

The functionality requires the following parameters:
* `--maven-repo-url`
  * Specifies the release repository base URL. Might be optional in case `--maven-snapshot-repo-url` is provided.
    * Example: `https://oss.jfrog.org/oss-release-local/`
* `--maven-snapshot-repo-url`
  * Specifies the snapshot repository base URL. Might be optional in case `--maven-repo-url` is provided.
    * Example: `https://oss.jfrog.org/oss-snapshot-local/`
* `--groupId`
  * The groupId of the artifact 
* `--artifactId`
  * The artifactId of the artifact
* `--current-artifact-version`
  * The version of the artifact that contains the new API. This is used to determine if the snapshot, 
or the release repository needs to be used. 
    * Example values are: 1.0.0, 1.0.0-SNAPSHOT

## Full list of parameters
`--old-api`: Denotes the path of the baseline API. Can be a relative path and an absolute one.<br>
`--new-api`: Denotes the path of the new, changed API. Can be a relative path and an absolute one.<br>
`--output-formats`: Specifies which reports shall be generated. Possible values: `STDOUT`, `JSON`, `HTML`<br>
`--output-path`: Denotes the folder where the file reports shall be saved. Can be a relative path and an absolute one. 
In case the path doesn't exist, it will be created.<br>
`--maven-repo-url`: Specifies the release repository base URL. Might be optional in case `--maven-snapshot-repo-url` 
is provided.<br>
`--maven-snapshot-repo-url`: Specifies the snapshot repository base URL. Might be optional in case `--maven-repo-url` 
is provided.<br>
 `--groupId`: The groupId of the artifact.<br>
 `--artifactId`: The artifactId of the artifact.<br>
 `--current-artifact-version`: The version of the artifact that contains the new API. This is used to determine if the 
 snapshot, or the release repository needs to be used.<br>
 
 