package io.redskap.swagger.brake.cli.options;

import static io.redskap.swagger.brake.cli.options.CliOption.*;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;
import io.redskap.swagger.brake.runner.Options;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CliOptionsValidator {
    public static final Map<CliOption, Function<Options, String>> MAVEN_CONFIG_MAP = ImmutableMap.of(
        MAVEN_REPO_URL, Options::getMavenRepoUrl,
        MAVEN_SNAPSHOT_REPO_URL, Options::getMavenSnapshotRepoUrl,
        CURRENT_ARTIFACT_VERSION, Options::getCurrentArtifactVersion,
        GROUP_ID, Options::getGroupId,
        ARTIFACT_ID, Options::getArtifactId
    );

    public void validate(Options options) {
        if (isBlank(options.getNewApiPath())) {
            throw new IllegalArgumentException(format("%s must be provided.", NEW_API_PATH.asCliOption()));
        }
        if (isNotBlank(options.getOldApiPath()) && isAnyMavenConfigurationSet(options)) {
            throw new IllegalArgumentException(format("Maven configuration is detected along with %s. Please use only one of them.", OLD_API_PATH.asCliOption()));
        }
        if (isBlank(options.getOldApiPath()) && !isAnyMavenConfigurationSet(options)) {
            throw new IllegalArgumentException(format("Either %s must be provided or a Maven configuration.", OLD_API_PATH.asCliOption()));
        }
        if (isAnyMavenConfigurationSet(options)) {
            if (!isFullMavenConfigurationSet(options)) {
                Collection<CliOption> missingMavenCliOptions = findMissingMavenConfiguration(options);
                List<String> mavenCliOptionsFormatted = missingMavenCliOptions.stream().map(CliOption::asCliOption).collect(toList());
                String joinedMavenCliOptions = StringUtils.join(mavenCliOptionsFormatted, ",");
                throw new IllegalArgumentException(format("Partial Maven configuration detected, make sure you set %s as well.", joinedMavenCliOptions));
            }
        }
    }

    private boolean isFullMavenConfigurationSet(Options options) {
        return findMissingMavenConfiguration(options).isEmpty();
    }

    private boolean isAnyMavenConfigurationSet(Options options) {
        return findMissingMavenConfiguration(options).size() < MAVEN_CONFIG_MAP.size();
    }

    private Collection<CliOption> findMissingMavenConfiguration(Options options) {
        return MAVEN_CONFIG_MAP.entrySet().stream()
            .filter(e -> isBlank(e.getValue().apply(options)))
            .map(Map.Entry::getKey)
            .collect(toList());
    }
}
