# Troubleshooting
## CLI file reporting doesn't work
If you encounter the following message during an execution
```bash
No file reporting has been done since output file path is not set
```
It means you have configured file typed reporting like JSON or HTML but you did not pass the
`--output-path` argument so swagger-brake doesn't know where to store the reports. 

Relevant section: [Customizing reporting](./cli-interface.md#customizing-reporting)

