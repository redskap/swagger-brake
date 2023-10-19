(window.webpackJsonp=window.webpackJsonp||[]).push([[27],{308:function(t,e,a){"use strict";a.r(e);var s=a(14),n=Object(s.a)({},(function(){var t=this,e=t._self._c;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"gradle-plugin"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#gradle-plugin"}},[t._v("#")]),t._v(" Gradle Plugin")]),t._v(" "),e("h2",{attrs:{id:"installation"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#installation"}},[t._v("#")]),t._v(" Installation")]),t._v(" "),e("p",[t._v("Using the Gradle plugin is easy. Similarly, to any other plugins, you just need apply the plugin in your "),e("code",[t._v("build.gradle")]),t._v(".")]),t._v(" "),e("p",[t._v("With plugins DSL:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[t._v("plugins "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n  id "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"io.redskap.swagger-brake"')])]),t._v(" version "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"2.4.0"')])]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),e("p",[t._v("Or legacy plugin application:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[t._v("buildscript "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n  repositories "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    maven "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n      url "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"https://plugins.gradle.org/m2/"')])]),t._v("\n    "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n  "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n  dependencies "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    classpath "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"io.redskap:swagger-brake-gradle:2.4.0"')])]),t._v("\n  "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n\napply plugin"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"io.redskap.swagger-brake"')])]),t._v("\n")])])]),e("h2",{attrs:{id:"basics"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#basics"}},[t._v("#")]),t._v(" Basics")]),t._v(" "),e("p",[t._v("swagger-brake is hooked into the "),e("code",[t._v("check")]),t._v(" task, and the goal that swagger-brake uses is called "),e("code",[t._v("checkBreakingChanges")]),t._v(", so\nafter configuring the plugin the only thing left is to configure a few settings to the plugin, the "),e("code",[t._v("newApi")]),t._v(" and the\n"),e("code",[t._v("mavenRepoUrl")]),t._v(" parameters:")]),t._v(" "),e("p",[t._v("Example:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\nswaggerBrake "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    newApi "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("project"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("buildDir")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/resources/main/swagger.yaml"')])]),t._v("\n    mavenRepoUrl "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("REPO_URL")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/artifactory/libs-release-local"')])]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\n")])])]),e("p",[t._v("Executing a simple "),e("code",[t._v("gradle clean build")]),t._v(" command will result in the following output:")]),t._v(" "),e("div",{staticClass:"language-bash extra-class"},[e("pre",{pre:!0,attrs:{class:"language-bash"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("..")]),t._v(".\n"),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v(">")]),t._v(" Task :checkBreakingChanges\nNo breaking API changes detected\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("..")]),t._v(".\n")])])]),e("p",[t._v("Of course in case this is the first time the artifact is getting built, swagger-brake will notice that\nand skip the check:")]),t._v(" "),e("div",{staticClass:"language-bash extra-class"},[e("pre",{pre:!0,attrs:{class:"language-bash"}},[e("code",[e("span",{pre:!0,attrs:{class:"token operator"}},[t._v(">")]),t._v(" Task :checkBreakingChanges\nLatest version of the artifact could not be retrieved from http://"),e("span",{pre:!0,attrs:{class:"token variable"}},[t._v("${REPO_URL}")]),t._v("/artifactory/libs-release-local with io.redskap.example:swagger-brake-gradle-example\nAssuming this is the first version of the artifact, skipping check "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("for")]),t._v(" breaking changes\n")])])]),e("h2",{attrs:{id:"customizing-reporting"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#customizing-reporting"}},[t._v("#")]),t._v(" Customizing reporting")]),t._v(" "),e("p",[t._v("The custom reporting functionality is described in the\n"),e("RouterLink",{attrs:{to:"/configuration/#customizing-reporting"}},[t._v("Customizing reporting")]),t._v(" section.")],1),t._v(" "),e("p",[t._v("By default, there's going to be console reporting enabled as well as HTML. At the end of the execution, you should\nsee the HTML report generated under the "),e("code",[t._v("build/swagger-brake")]),t._v(" folder. For reconfiguring the target directory, use the\n"),e("code",[t._v("outputFilePath")]),t._v(" parameter.")]),t._v(" "),e("p",[t._v("Overriding the reporting configuration is simple. In the plugin configuration, just set the "),e("code",[t._v("outputFormats")]),t._v(" attribute with\nan array of values.")]),t._v(" "),e("p",[t._v("Example:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\nswaggerBrake "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    newApi "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("project"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("buildDir")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/resources/main/swagger.yaml"')])]),t._v("\n    mavenRepoUrl "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("REPO_URL")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/artifactory/libs-release-local"')])]),t._v("\n    outputFormats "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'HTML'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'JSON'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\n")])])]),e("h2",{attrs:{id:"deprecating-apis"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#deprecating-apis"}},[t._v("#")]),t._v(" Deprecating APIs")]),t._v(" "),e("p",[t._v("The API deprecation support is described in the\n"),e("RouterLink",{attrs:{to:"/configuration/#deprecating-apis"}},[t._v("Deprecating APIs")]),t._v(" section.")],1),t._v(" "),e("p",[t._v("Overriding the default deprecation rule - which is to allow the deletion of deprecated APIs, just like for the CLI -\nhas never been easier, just set the "),e("code",[t._v("deprecatedApiDeletionAllowed")]),t._v(" value to "),e("code",[t._v("false")]),t._v(":")]),t._v(" "),e("p",[t._v("Example:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\nswaggerBrake "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    newApi "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("project"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("buildDir")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/resources/main/swagger.yaml"')])]),t._v("\n    mavenRepoUrl "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("REPO_URL")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/artifactory/libs-release-local"')])]),t._v("\n    deprecatedApiDeletionAllowed "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token boolean"}},[t._v("false")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\n")])])]),e("h2",{attrs:{id:"latest-maven-artifact-resolution"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#latest-maven-artifact-resolution"}},[t._v("#")]),t._v(" Latest Maven artifact resolution")]),t._v(" "),e("p",[t._v("Latest Maven artifact resolution is described in detail within the\n"),e("RouterLink",{attrs:{to:"/configuration/#latest-maven-artifact-resolution"}},[t._v("Latest Maven artifact resolution")]),t._v(" section.")],1),t._v(" "),e("p",[t._v("By default, most of the configuration values are picked up from the project configuration itself, meaning that\nyou don't need to set the "),e("code",[t._v("groupId")]),t._v(", "),e("code",[t._v("artifactId")]),t._v(", "),e("code",[t._v("currentVersion")]),t._v(" attributes. Respectively they will\nuse the project's "),e("code",[t._v("groupId")]),t._v(", "),e("code",[t._v("artifactId")]),t._v(" and "),e("code",[t._v("version")]),t._v(" values. However, in case there's a need to override\nany of those, you can do that within the configuration.")]),t._v(" "),e("p",[t._v("There's a single setting you got to provide for the resolution to work, either "),e("code",[t._v("mavenRepoUrl")]),t._v(" or "),e("code",[t._v("mavenSnapshotRepoUrl")]),t._v(".\nRespectively they represent the release and snapshot repositories that holds the previous version of your artifact.")]),t._v(" "),e("p",[t._v("In addition, you can configure authentication to your repository with the "),e("code",[t._v("mavenRepoUsername")]),t._v(" and "),e("code",[t._v("mavenRepoPassword")]),t._v("\noptions.")]),t._v(" "),e("p",[t._v("Example configuration with authentication:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\nswaggerBrake "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    newApi "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("project"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("buildDir")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/resources/main/swagger.yaml"')])]),t._v("\n    mavenRepoUrl "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("REPO_URL")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/artifactory/libs-release-local"')])]),t._v("\n    mavenRepoUsername "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'admin'")]),t._v("\n    mavenRepoPassword "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'artifactory'")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\n")])])]),e("p",[t._v("You can also configure the packaging of your artifact using the "),e("code",[t._v("artifactPackaging")]),t._v(" property.")]),t._v(" "),e("p",[t._v("Possible values are:")]),t._v(" "),e("ul",[e("li",[t._v("jar")]),t._v(" "),e("li",[t._v("war")])]),t._v(" "),e("p",[t._v("However, keep in mind that the plugin tries to automatically resolve which packaging is most appropriate.\nOnly set it if you experience issues.")]),t._v(" "),e("h2",{attrs:{id:"beta-api-support"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#beta-api-support"}},[t._v("#")]),t._v(" Beta API support")]),t._v(" "),e("p",[t._v("For further reference, check out "),e("RouterLink",{attrs:{to:"/configuration/#beta-api-support"}},[t._v("Beta API support")]),t._v("\nin the Configuration section.")],1),t._v(" "),e("p",[t._v("For overriding the attribute that is used for denoting beta APIs, use the "),e("code",[t._v("betaApiExtensionName")]),t._v(" configuration.")]),t._v(" "),e("p",[t._v("Example:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\nswaggerBrake "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    newApi "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("project"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("buildDir")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/resources/main/swagger.yaml"')])]),t._v("\n    mavenRepoUrl "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("REPO_URL")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/artifactory/libs-release-local"')])]),t._v("\n    betaApiExtensionName "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'x-something'")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\n")])])]),e("h2",{attrs:{id:"excluding-paths-from-the-scan"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#excluding-paths-from-the-scan"}},[t._v("#")]),t._v(" Excluding paths from the scan")]),t._v(" "),e("p",[t._v("For detailed description on the feature, see "),e("RouterLink",{attrs:{to:"/configuration/#excluding-paths-from-the-scan"}},[t._v("Excluding paths from the scan")]),t._v(".")],1),t._v(" "),e("p",[t._v("Similarly, to other configurations, use the "),e("code",[t._v("excludedPaths")]),t._v(" parameter.")]),t._v(" "),e("p",[t._v("Example:")]),t._v(" "),e("div",{staticClass:"language-groovy extra-class"},[e("pre",{pre:!0,attrs:{class:"language-groovy"}},[e("code",[e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\nswaggerBrake "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    newApi "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("project"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("buildDir")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/resources/main/swagger.yaml"')])]),t._v("\n    mavenRepoUrl "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token interpolation-string"}},[e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"')]),e("span",{pre:!0,attrs:{class:"token interpolation"}},[e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("${")]),e("span",{pre:!0,attrs:{class:"token expression"}},[t._v("REPO_URL")]),e("span",{pre:!0,attrs:{class:"token interpolation-punctuation punctuation"}},[t._v("}")])]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('/artifactory/libs-release-local"')])]),t._v("\n    excludedPaths "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'/auth'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("...")]),t._v("\n")])])]),e("h2",{attrs:{id:"default-parameter-values"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#default-parameter-values"}},[t._v("#")]),t._v(" Default parameter values")]),t._v(" "),e("table",[e("thead",[e("tr",[e("th",{staticStyle:{"text-align":"center"}},[t._v("Parameter")]),t._v(" "),e("th",{staticStyle:{"text-align":"center"}},[t._v("Default value")])])]),t._v(" "),e("tbody",[e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("outputFormats")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("HTML")])])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("outputFilePath")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("${project.buildDir}/swagger-brake")])])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("groupId")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("${project.groupId}")])])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("artifactId")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("${project.artifactId}")])])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("currentArtifactVersion")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("${project.version}")])])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("artifactPackaging")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("jar")])])])])]),t._v(" "),e("h2",{attrs:{id:"full-list-of-parameters"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#full-list-of-parameters"}},[t._v("#")]),t._v(" Full list of parameters")]),t._v(" "),e("table",[e("thead",[e("tr",[e("th",{staticStyle:{"text-align":"center"}},[t._v("Parameter")]),t._v(" "),e("th",{staticStyle:{"text-align":"center"}},[t._v("Description")])])]),t._v(" "),e("tbody",[e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("oldApi")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("Denotes the path of the baseline API. Can be a relative path and an absolute one.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("newApi")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("Denotes the path of the new, changed API. Can be a relative path and an absolute one.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("outputFormats")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("Specifies which reports shall be generated. Possible values: "),e("code",[t._v("STDOUT")]),t._v(", "),e("code",[t._v("JSON")]),t._v(", "),e("code",[t._v("HTML")])])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("outputFilePath")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("Denotes the folder where the file reports shall be saved. Can be a relative path and an absolute one. In case the path doesn't exist, it will be created.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("mavenRepoUrl")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("Specifies the release repository base URL. Might be optional in case "),e("code",[t._v("mavenSnapshotRepoUrl")]),t._v(" is provided.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("mavenSnapshotRepoUrl")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("Specifies the snapshot repository base URL. Might be optional in case "),e("code",[t._v("mavenRepoUrl")]),t._v(" is provided.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("mavenRepoUsername")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("The username for the Maven repository.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("mavenRepoPassword")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("The password for the Maven repository.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("groupId")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("The groupId of the artifact.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("artifactId")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("The artifactId of the artifact.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("currentArtifactVersion")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("The version of the artifact that contains the new API. This is used to determine if the snapshot, or the release repository needs to be used.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("artifactPackaging")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("Specifies the artifact packaging. Could be jar or war. Defaults to jar. If the Gradle War plugin is applied, war is used.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("apiFilename")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("The filename to search for within the artifact.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("betaApiExtensionName")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("The name of the custom vendor extension attribute that denotes beta APIs.")])]),t._v(" "),e("tr",[e("td",{staticStyle:{"text-align":"center"}},[e("code",[t._v("excludedPaths")])]),t._v(" "),e("td",{staticStyle:{"text-align":"center"}},[t._v("A  list of path prefixes that shall be excluded from the scan.")])])])])])}),[],!1,null,null,null);e.default=n.exports}}]);