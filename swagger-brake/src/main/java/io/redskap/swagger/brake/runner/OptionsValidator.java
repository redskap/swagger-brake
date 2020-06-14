package io.redskap.swagger.brake.runner;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class OptionsValidator {
    public final Map<String, Function<Options, String>> mavenConfigMap;

    public OptionsValidator() {
        mavenConfigMap = ImmutableMap.of(
            getMavenRepoUrlName(), Options::getMavenRepoUrl,
            getMavenSnapshotRepoUrlName(), Options::getMavenSnapshotRepoUrl,
            getCurrentArtifactVersionName(), Options::getCurrentArtifactVersion,
            getGroupIdName(), Options::getGroupId,
            getArtifactIdName(), Options::getArtifactId
        );
    }

    public void validate(Options options) {
        if (isBlank(options.getNewApiPath())) {
            throw new IllegalArgumentException(format("%s must be provided.", getNewApiPathName()));
        }
        if (isNotBlank(options.getOldApiPath()) && isAnyMavenConfigurationSet(options)) {
            throw new IllegalArgumentException(format("Maven configuration is detected along with %s. Please use only one of them.", getOldApiPathName()));
        }
        if (isBlank(options.getOldApiPath()) && !isAnyMavenConfigurationSet(options)) {
            throw new IllegalArgumentException(format("Either %s must be provided or a Maven configuration.", getOldApiPathName()));
        }
        if (isAnyMavenConfigurationSet(options)) {
            if (!isFullMavenConfigurationSet(options)) {
                Collection<String> missingMavenCliOptions = findMissingMavenConfiguration(options);
                String joinedMavenCliOptions = StringUtils.join(missingMavenCliOptions, ",");
                throw new IllegalArgumentException(format("Partial Maven configuration detected, make sure you set %s as well.", joinedMavenCliOptions));
            }
        }
    }

    private boolean isFullMavenConfigurationSet(Options options) {
        return findMissingMavenConfiguration(options).isEmpty();
    }

    private boolean isAnyMavenConfigurationSet(Options options) {
        return findMissingMavenConfiguration(options).size() < mavenConfigMap.size();
    }

    private Collection<String> findMissingMavenConfiguration(Options options) {
        return mavenConfigMap.entrySet().stream()
            .filter(e -> isBlank(e.getValue().apply(options)))
            .map(Map.Entry::getKey)
            .collect(toList());
    }

    protected String getMavenRepoUrlName() {
        return "mavenRepoUrl";
    }

    protected String getMavenSnapshotRepoUrlName() {
        return "mavenSnapshotRepoUrl";
    }

    protected String getCurrentArtifactVersionName() {
        return "currentArtifactVersion";
    }

    protected String getGroupIdName() {
        return "groupId";
    }

    protected String getArtifactIdName() {
        return "artifactId";
    }

    protected String getOldApiPathName() {
        return "oldApiPath";
    }

    protected String getNewApiPathName() {
        return "newApiPath";
    }
}
