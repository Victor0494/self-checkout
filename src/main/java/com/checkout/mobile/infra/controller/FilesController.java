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

}
