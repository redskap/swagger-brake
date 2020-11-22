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
By default, swagger-brake uses a console reporter, meaning the check result will be
simply printed out to the console. For other use-cases, like when you want to store the
report result in Jenkins and show it as an HTML, you can configure the tool to generate
other types of reports too.

At this moment, the following report types are supported (these are the exact values to be
passed):
* `STDOUT` (default)
* `JSON`
* `HTML`

To configure a custom reporting, the CLI accepts the `--output-formats` argument. The parameter
accepts a comma separated list of reporting formats, in case you want to use multiple reporters.

For file typed reporters like JSON or HTML, you must also pass the `--output-path` argument 
denoting the location where the reports should be saved.

An example configuration could look the following:
```bash
$ java -jar swagger-brake.jar --old-api=swagger.json --new-api=swagger2.json --output-formats=STDOUT,JSON,HTML
```

