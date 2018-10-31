package io.redskap.swagger.brake.runner;

import lombok.Data;

@Data
public class Options {
    private String oldApiPath;
    private String newApiPath;

    private String apiServer;
    private boolean uploadEnabled;
    private String project;
}
