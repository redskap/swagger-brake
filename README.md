# swagger-brake [![Build Status](https://travis-ci.com/redskap/swagger-brake.svg?branch=master)](https://travis-ci.com/redskap/swagger-brake)
The main focus of the project is to have a clear view whether any breaking change
is getting introduced with the new version of a Swagger API definition.

The following changes are classified as breaking:
- If any path is removed or the HTTP verb is changed
- If any request parameter is removed
- If any new request parameter is required or an existing one set as required 
- If any request attribute is removed
- If any response attribute is removed

The tool is very useful in case it's necessary not to introduce any backward
incompatible API change.

You can find the detailed documentation [here](https://redskap.github.io/swagger-brake/introduction/).

The available releases can be found [here](https://github.com/redskap/swagger-brake/releases)

The master branch represents the ongoing development of the tool. For the
release versions, you can check out the corresponding tags.

On top of the CLI, there are Maven and Gradle plugins also available for 
easier integration into any existing pipeline.

Maven plugin can be found [here](https://github.com/redskap/swagger-brake-maven-plugin).

Gradle plugin can be found [here](https://github.com/redskap/swagger-brake-gradle).

## Building
The application is using Gradle as a build system and building it can be done 
by executing the following command:
```bash
$ ./gradlew clean build shadowJar
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
