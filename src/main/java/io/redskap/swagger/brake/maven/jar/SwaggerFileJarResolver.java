package io.redskap.swagger.brake.maven.jar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.jar.JarEntry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class SwaggerFileJarResolver {
    private final JarScanner jarScanner;

    public File resolve(File jarFile) {
        try {
            log.debug("Attempting to resolve swagger file from external JAR {}", jarFile.getAbsolutePath());
            URL swaggerFile = findSwaggerFile(jarFile);
            String fileName = new File(swaggerFile.getFile()).getName();
            File destination = Files.createTempFile("swagger-brake", fileName).toFile();
            log.debug("Extracting swagger file from {} to {}", swaggerFile, destination.getAbsolutePath());
            FileCopyUtils.copy(swaggerFile.openConnection().getInputStream(), new FileOutputStream(destination));
            log.debug("Extraction done");
            return destination;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL findSwaggerFile(File jarFile) throws IOException {
        return jarScanner.find(jarFile, this::isSwaggerFile)
            .map(e -> getSwaggerFileURL(jarFile, e))
            .orElseThrow(() -> new IllegalStateException("Swagger file is not present in the artifact"));
    }

    private URL getSwaggerFileURL(File jarFile, JarEntry entry) {
        try {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {jarFile.toURI().toURL()});
            return urlClassLoader.getResource(entry.getName());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isSwaggerFile(JarEntry entry) {
        String entryName = entry.getName();
        return entryName.endsWith("swagger.json")
            || entryName.endsWith("swagger.yml")
            || entryName.endsWith("swagger.yaml");
    }
}
