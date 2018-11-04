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
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
@RequiredArgsConstructor
public class SwaggerFileJarResolver {
    private final JarScanner jarScanner;

    public File resolve(File jarFile) {
        try {
            URL swaggerFile = findSwaggerFile(jarFile);
            String fileName = new File(swaggerFile.getFile()).getName();
            File destination = Files.createTempFile("swagger-brake", fileName).toFile();
            FileCopyUtils.copy(swaggerFile.openConnection().getInputStream(), new FileOutputStream(destination));
            return destination;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL findSwaggerFile(File jarFile) throws IOException {
        return jarScanner.find(jarFile, this::isSwaggerFile)
            .map(e -> get(jarFile, e))
            .orElseThrow(() -> new IllegalStateException("Swagger file is not present in the artifact"));
    }

    private URL get(File jarFile, JarEntry entry) {
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
