# Configuration
## Customizing reporting
By default, swagger-brake uses only a console reporter, meaning the check result will be
simply printed out to the console. For other use-cases, like when you want to store the
report result in Jenkins and show it as an HTML, you can configure the tool to generate
other types of reports too.

At this moment, the following report types are supported (these are the exact values to be
passed):
* `STDOUT` (default)
* `JSON`
* `HTML`

CLI configuration [here](../cli/README.md#customizing-reporting).

Maven configuration [here](../maven/README.md#customizing-reporting).

Gradle configuration [here](../gradle/README.md#customizing-reporting).

An example JSON report looks the following:
```json
{
  "info": {
    "title": "Example API title",
    "description": "API description",
    "version": "1.0.0"
  },
  "breakingChanges": {
    "pathDeletedBreakingChange" : [ {
      "path" : "/pet",
      "method" : "POST",
      "message" : "Path /pet POST has been deleted"
    } ]  
  }
}
```
In case of no breaking changes, the JSON report will be an empty JSON.
```json
{ }
```

## Deprecating APIs
As an API evolves and time passes, some APIs might simply become unused and old.
In that case, there's no point keeping the API, in fact you rather just get rid of it.
Since swagger-brake reports an API removal as a breaking change, somehow we need to
mark these APIs, so it's not going to be considered as a breaking change anymore. 

The [OpenAPI specification](https://swagger.io/specification/#fixed-fields-8) 
defines the `deprecated` attribute for marking an API deprecated, 
so that it's possible to delete a deprecated API without reporting breaking changes.

The default behavior is that deprecated APIs can be deleted without any issue. 
 
However it's possible to override this behavior, so that a deprecated API deletion will also
be considered as a breaking change.

CLI configuration [here](../cli/README.md#deprecating-apis).

Maven configuration [here](../maven/README.md#deprecating-apis).

Gradle configuration [here](../gradle/README.md#deprecating-apis).

Example `swagger1.json`:
```yaml
---
swagger: "2.0"
info:
  version: "1.0.0"
  title: "Swagger Petstore"
basePath: "/v2"
paths:
  /pet/findByStatus:
    get:
      deprecated: true
      summary: "Finds Pets by status"
      operationId: "findPetsByStatus"
      produces:
      - "application/json"
      responses:
        200:
          description: "successful operation"
  /pet/findByTags:
    get:
      summary: "Finds Pets by tags"
      operationId: "findPetsByTags"
      produces:
        - "application/json"
      responses:
        200:
          description: "successful operation"
definitions:
  Pet:
    type: "object"
    properties:
      id:
        type: "integer"
        format: "int64"
```

Example `swagger2.json`:
```yaml
---
swagger: "2.0"
info:
  version: "1.0.0"
  title: "Swagger Petstore"
basePath: "/v2"
paths:
  /pet/findByTags:
    get:
      summary: "Finds Pets by tags"
      operationId: "findPetsByTags"
      produces:
        - "application/json"
      responses:
        200:
          description: "successful operation"
definitions:
  Pet:
    type: "object"
    properties:
      id:
        type: "integer"
        format: "int64"
```

Note that `/pet/findByStatus` API has been marked as deprecated in `swagger1.json` and `swagger2.json` doesn't contain
the API anymore.

## Latest Maven artifact resolution
For easier CI integration, there is a possibility not to provide the old API path directly 
but to resolve it from an artifact containing the Swagger definition file from any Maven2
repository (including Nexus and Artifactory as well). This feature requires that 
the API definition file has been packed into a JAR.

The feature is especially useful when it comes to Maven and Gradle plugin integration. In that case within a build 
process, often only the "new" API is available and the old one is stored elsewhere, like in an older version of
the same artifact.

Let's say you want to make sure your API is always backward compatible. You can simply create a Maven artifact from the
Swagger definition and just release it as any other artifact with a proper versioning scheme (i.e. using release 
and snapshot versions). When the release process is nailed down, you can configure swagger-brake to use your
artifact repository and pass the artifact information required for the artifact resolution.

The resolution always tries to find the latest version of your artifact, depending on if it's a release or a snapshot
version. The resolution is based on the `maven-metadata.xml` file available in the Maven repository when an
artifact gets uploaded. As soon as the resolved JAR artifact is found, it will be downloaded to the system's 
temporary directory for further scanning.

Since Nexus, Artifactory and other artifact repositories can have authentication in place for access-control, 
swagger-brake has configuration parameters to provide username and password data to access the repository.  

By default, swagger-brake scans the downloaded artifact for one of the following 3 files:
- swagger.json
- swagger.yaml
- swagger.yml

If needed, you can override the filename to look for. This is described on the sections of the various interfaces.

CLI configuration [here](../cli/README.md#latest-maven-artifact-resolution).

Maven configuration [here](../maven/README.md#latest-maven-artifact-resolution).

Gradle configuration [here](../gradle/README.md#latest-maven-artifact-resolution).

## Beta API support
There might be a need to work with beta APIs. In those use-cases you might want to release a version of your API
to receive quick feedback from the clients. Usually it means incremental changes, knowing the fact that
they might not be backward compatible but this is acceptable.

Swagger Brake provides a way to mark APIs beta, in this case those API operation changes are not going to be 
considered as breaking and will be ignored.

The following table shows the rules:

| Use-case                     | Old API operation  | New API operation                 | Breaking change?  |
|:----------------------------:|:------------------:|:---------------------------------:|:-----------------:|
| Beta API created             | Does not exist     | Exists as beta                    | No                |
| Beta API modified            | Exists as beta     | Exists as beta and modified       | No                |
| Beta API is not beta anymore | Exists as beta     | Exists as non-beta (flag removed) | No                |
| Beta API removed             | Exists as beta     | Does not exists                   | No                |
| Standard API marked as beta  | Exists as non-beta | Exists as beta                    | Yes               |

A beta API can be marked with the `x-beta-api` vendor extension in the specification file on
operation level. It can have a boolean value only.

Example snippet:

```text
...
paths:
  /pet:
    post:
      operationId: "addPet"
      x-beta-api: true
...
```

As of today, there is no standard way of denoting an API beta in the OpenAPI specification yet. Due to this
there might be a need in different projects to use various vendor extension attribute names
to mark APIs beta.

There is a customization option available that you can utilize for these cases.

CLI configuration [here](../cli/README.md#beta-api-support).

Maven configuration [here](../maven/README.md#beta-api-support).

Gradle configuration [here](../gradle/README.md#beta-api-support).

## Excluding paths from the scan
There might be a need to exclude specific APIs from the scan completely. 

The exclusion works based on prefix-matching, so in case you'd like to exclude all paths that
starts with `/auth` for example, you can pass it as a parameter.

CLI configuration [here](../cli/README.md#excluding-paths-from-the-scan).

Maven configuration [here](../maven/README.md#excluding-paths-from-the-scan).

Gradle configuration [here](../gradle/README.md#excluding-paths-from-the-scan).