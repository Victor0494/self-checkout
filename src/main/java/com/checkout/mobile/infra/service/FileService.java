package com.checkout.mobile.infra.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileService {

    public static final String STATIC_IMAGES_CAROUSEL = "src/main/resources/static/images/carousel";
    public static final String STATIC_IMAGES_POPULAR = "src/main/resources/static/images/popular";

    @Value("${environment-url.url}")
    private String PATH;

    public List<String> getFiles(boolean carousel) throws IOException {
        Path folder;
        String imgPath;

        if (carousel) {
            folder = Paths.get(STATIC_IMAGES_CAROUSEL);
            imgPath = PATH + "/images/carousel/";
        } else {
            folder = Paths.get(STATIC_IMAGES_POPULAR);
            imgPath = PATH + "/images/popular/";
        }

        try (Stream<Path> paths = Files.list(folder)) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(path -> imgPath +  path.getFileName().toString())
                    .toList();
        }
    }
}
