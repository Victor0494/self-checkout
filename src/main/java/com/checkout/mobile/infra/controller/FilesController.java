package com.checkout.mobile.infra.controller;

import com.checkout.mobile.infra.service.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(value = "/files")
public class FilesController {

    private final FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getFiles(@RequestParam boolean carousel) throws IOException {
        return fileService.getFiles(carousel);
    }

    @GetMapping(
            value = "/images/{filename:.+}"
    )
    public ResponseEntity<byte[]> getImage(@PathVariable String filename, @RequestParam boolean carousel) throws IOException {
        String imgPath;

        if (carousel) {
            imgPath = "static/images/carousel/";
        } else {
            imgPath = "static/images/popular/";
        }

        ClassPathResource imgFile = new ClassPathResource(imgPath + filename);

        if (!imgFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        MediaType mediaType = filename.endsWith(".png")
                ? MediaType.IMAGE_PNG
                : MediaType.IMAGE_JPEG;

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(bytes);
    }

}
