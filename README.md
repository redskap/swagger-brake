# Swagger Brake [![Build Status](https://travis-ci.com/redskap/swagger-brake.svg?branch=master)](https://travis-ci.com/redskap/swagger-brake)
The main focus of the project is to have a clear view whether any breaking change
is getting introduced with the new version of a Swagger API definition.

The following changes are classified as breaking:
- If any path is removed or the HTTP verb is changed
- If any request parameter is removed
- If any new request parameter is required or an existing one set as required 
- If any request attribute is removed
- If any response attribute is removed

The tool is very useful in case it's necessary not to introduce any backward
incompatible API change unintentionally.

The available releases can be found [here](https://github.com/redskap/swagger-brake/releases)

The master branch represents the ongoing development of the tool. For the
release versions, you can check out the corresponding tags.

On top of the CLI, there are Maven and Gradle plugins also available for 
easier integration into any existing pipeline.

Maven plugin can be found [here](https://github.com/redskap/swagger-brake-maven-plugin).

Gradle plugin can be found [here](https://github.com/redskap/swagger-brake-gradle).

## Usage
The check can be started by executing the following command:
```bash
$ java -jar swagger-brake.jar --old-api=/home/user/something.yaml --new-api=/home/user/something_v2.yaml [parameters]
```
The available parameters can be checked by executing the help command:
```bash
$ java -jar swagger-brake.jar --help
```
After executing the check, the application will print the results out to the standard out
in the following format:
```text
$ java -jar swagger-brake.jar --old-api=/home/user/petstore.yaml --new-api=/home/user/petstore_v2.yaml
There were breaking API changes

Path /pet/findByStatus GET has been deleted
```

## Reporting
There are multiple output formats for the results. 

Three types are supported:
- standard output
- JSON
- HTML

It can be configured via the `--output-formats` argument. The values can be `STDOUT`,`JSON`,`HTML`.
For file types (e.g. `JSON`,`HTML`) it's necessary to set the `--output-path` argument as well, where
to save the results.

Multiple reporters can be set up at the same time by separating the types with commas:
```bash
--output-formats=JSON,HTML
```

## API deprecation handling
The [OpenAPI specification](https://swagger.io/specification/#fixed-fields-8) 
defines the `deprecated` attribute on operation level for marking an API deprecated. 
Swagger Brake utilizes this property so that it's possible to delete a deprecated API 
without marking the API broken.

The default behavior is that deprecated APIs can be deleted without any issue but there is
a possibility to override this behavior so that the new API will be considered as broken.

The `--deprecated-api-deletion-allowed` parameter is responsible for setting this behavior
in Swagger Brake. The default value is `true` but it can be overridden to `false` anytime. 

## Latest artifact resolution
For easier CI integration, there is a possibility not to provide the old API path directly 
but to resolve the latest artifact containing the Swagger definition file from any Maven2
repository (including Nexus and Artifactory as well). This feature requires that 
the API definition file is packed into a JAR.

Using the functionality requires the following parameters:
- `--maven-repo-url`
  - Specifies the release repository base URL. Might be optional in case `--maven-snapshot-repo-url` is provided.
Example: `https://oss.jfrog.org/oss-release-local/`
- `--maven-snapshot-repo-url`
  - Specifies the snapshot repository base URL. Might be optional in case `--maven-repo-url` is provided.
Example: `https://oss.jfrog.org/oss-snapshot-local/`
- `--groupId`
  - The groupId of the artifact 
- `--artifactId`
  - The artifactId
- `--current-artifact-version`
  - The version of the artifact that contains the new API. This is mostly used to determine if the snapshot
  or the release repository needs to be used. Example values are: 1.0.0, 1.0.0-SNAPSHOT

Example command:
```bash
$ java -jar swagger-brake.jar --new-api=/home/user/petstore_v2.yaml --maven-repo-url=https://oss.jfrog.org/oss-snapshot-local --maven-snapshot-repo-url=https://oss.jfrog.org/oss-snapshot-local --groupId=com.example --artifactId=petstore-api --current-artifact-version=1.0.0-SNAPSHOT
```

By default, Swagger Brake scans the downloaded artifact for one of the following 3 files:
- swagger.json
- swagger.yaml
- swagger.yml

This behavior can be customized by providing the `--api-filename` parameter.
Note that it's enough to provide the filename without the extension, in that case the JSON, YAML and YML
extension will be used for scanning.

Example command:
```bash
$ java -jar swagger-brake.jar --new-api=/home/user/petstore_v2.yaml --maven-repo-url=https://oss.jfrog.org/oss-snapshot-local --maven-snapshot-repo-url=https://oss.jfrog.org/oss-snapshot-local --groupId=com.example --artifactId=petstore-api --current-artifact-version=1.0.0-SNAPSHOT --api-filename=something.yaml
```

Note: if you provide both `--old-api` and Maven parameters, `--old-api` will take precedence. 

#### Secured Maven repository
It's also possible that the repository is secured with username and password. The following
2 parameters can be used to provide the credentials to access the repository:
- `--maven-repo-username`
- `--maven-repo-password`

#### Implementation details
The mechanism under the hood is to resolve the latest artifact based on the `maven-metadata.xml`
for a given groupId and artifactId. After the latest version has been determined, it will be 
downloaded to the temp directory. The downloaded JAR will be scanned for the Swagger file.

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

### Beta API vendor extension customization
There is no standard way of denoting an API beta in the OpenAPI specification yet. Due to this
there might be a need in different projects to use various vendor extension attribute names
to mark APIs beta.

There is a customization option that can be utilized for these cases.

By providing the `--beta-api-extension-name`, one can override the default `x-beta-api` attribute name
and the provided one will be used.

Example command:

```bash
$ java -jar swagger-brake.jar --old-api=/home/user/something.yaml --new-api=/home/user/something_v2.yaml --beta-api-extension-name=x-custom-beta-attributes
```

## Building
The application is using Gradle as a build system and building it can be done 
by executing the following command:
```bash
$ ./gradlew clean build
```

## License
```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
