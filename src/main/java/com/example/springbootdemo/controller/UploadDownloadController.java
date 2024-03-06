package com.example.springbootdemo.controller;

import com.example.springbootdemo.dao.UploadDao;
import com.example.springbootdemo.domain.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequestMapping("/file")
public class UploadDownloadController {

    private final UploadDao uploadDao;
    private final Path rootPath = Path.of(System.getProperty("user.home"), "/download");

    @Autowired
    public UploadDownloadController(UploadDao uploadDao) {
        this.uploadDao = uploadDao;
    }

    @PostMapping("/upload/{item_id}")
    public HttpEntity<Upload> upload(@PathVariable("item_id") int id, MultipartFile file) {
        Upload upload = uploadDao.save(file, id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(upload);
    }

    @GetMapping("/download/{filename:.+}")
    public HttpEntity<Resource> download(@PathVariable("filename") String generatedName) {
        Upload upload = uploadDao.findByGeneratedName(generatedName);
        var fsr = new FileSystemResource(rootPath.resolve(upload.getGeneratedName()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(upload.getMimeType()))
                .contentLength(upload.getSize())
                .body(fsr);

    }
}
