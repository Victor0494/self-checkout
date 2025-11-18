package com.checkout.mobile.infra.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileService {

    public List<String> getFiles(boolean carousel) throws IOException {
        String folder = carousel ? "static/images/carousel" : "static/images/popular";
        String imgPath = carousel ? "/images/carousel/" : "/images/popular/";

        URL url = getClass().getClassLoader().getResource(folder);

        if (url == null) {
            throw new FileNotFoundException("Pasta não encontrada: " + folder);
        }

        Path path;
        try {
            path = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new IOException("Erro ao converter URL para Path", e);
        }

        try (Stream<Path> paths = Files.list(path)) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(p -> imgPath + p.getFileName().toString())
                    .toList();
        }
    }
}
