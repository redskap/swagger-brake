# Changelog

## 2.2.0-SNAPSHOT
**Under development**

TBD

Planned release date TBD

## 2.1.0 
* [#41](https://github.com/redskap/swagger-brake/issues/41) Recursive schema throws NPE
* [#40](https://github.com/redskap/swagger-brake/issues/40) old-api should take precedence over maven configuration
* [#39](https://github.com/redskap/swagger-brake/issues/39) Support Nexus based maven metadata
* [#36](https://github.com/redskap/swagger-brake/issues/36) Add route path exclusion parameter support

Released at 2020-09-06

## 2.0.0
* [#35](https://github.com/redskap/swagger-brake/issues/35) Switch from JAXB to Jackson XMLMapper to support Java 11+
* [#29](https://github.com/redskap/swagger-brake/issues/29) allOf in Swagger contract results in NPE
* [#28](https://github.com/redskap/swagger-brake/issues/28) Null output for formData parameter
* [#26](https://github.com/redskap/swagger-brake/issues/26) SNAPSHOT releases should be optional
* OpenAPI V3 oneOf, anyOf schema support

Released at 2020-07-22

## 1.1.0
* [#27](https://github.com/redskap/swagger-brake/issues/27) Exitcode for CLI when API has breaking changes

Released at 2020-04-15

## 1.0.0
* [#25](https://github.com/redskap/swagger-brake/issues/25) Upgrade jackson-databind from 2.9.7 to 2.10.3
* [#24](https://github.com/redskap/swagger-brake/issues/24) Change in type of response attribute is not detected
* [#22](https://github.com/redskap/swagger-brake/issues/22) Extend README with link to release page for easier access
* [#19](https://github.com/redskap/swagger-brake/issues/19) Support for beta API
* [#18](https://github.com/redskap/swagger-brake/issues/18) Allow configuring the filename scanned from maven

Released at 2020-03-15

## 0.3.0
* [#17](https://github.com/redskap/swagger-brake/issues/17) Add ability to allow removal of deprecated APIs

Released at 2019-03-31

## 0.2.1
* [#16](https://github.com/redskap/swagger-brake/issues/16) Can you provide a simple entrypoint to compare 2 API instances
* [#15](https://github.com/redskap/swagger-brake/issues/15) The attempt to fix recursive schemas causes NPE
* [#14](https://github.com/redskap/swagger-brake/issues/14) Shouldn't removing a request body type be a breaking change?
* [#12](https://github.com/redskap/swagger-brake/issues/12) Don't disable all logging
* [#11](https://github.com/redskap/swagger-brake/issues/11) Starter should return results

Released at 2018-12-19

## 0.2.0
* [#9](https://github.com/redskap/swagger-brake/issues/9) Add support for $ref to all types that allow reference objects
* [#8](https://github.com/redskap/swagger-brake/issues/8) Stack overflow on recursive schemas
* [#7](https://github.com/redskap/swagger-brake/issues/7) Grammar error on ResponseTypeChangedBreakingChange
* [#5](https://github.com/redskap/swagger-brake/issues/5) Document usage as gradle/maven plugin
* [#4](https://github.com/redskap/swagger-brake/issues/4) Authentication support for Maven repositories
* [#3](https://github.com/redskap/swagger-brake/issues/3) Fix 8.14 checkstyle version usage
* [#2](https://github.com/redskap/swagger-brake/issues/2) JSON reporter is not providing any information about report location
* [#1](https://github.com/redskap/swagger-brake/issues/1) Ability to use more than one reporter

Released at 2018-12-10