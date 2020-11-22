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
  "pathDeletedBreakingChange" : [ {
    "path" : "/pet",
    "method" : "POST",
    "message" : "Path /pet POST has been deleted"
  } ]
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
version. 

Since Nexus, Artifactory and other artifact repositories can have authentication in place for access-control, 
swagger-brake has configuration parameters to provide username and password data to access the repository.  