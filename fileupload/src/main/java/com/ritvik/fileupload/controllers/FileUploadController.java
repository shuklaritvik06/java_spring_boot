package com.ritvik.fileupload.controllers;

import com.ritvik.fileupload.utilities.UploadFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return new ResponseEntity<>("Request must have file", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            Boolean result = UploadFile.uploadFile(file);
            if(result) {
                return new ResponseEntity<>(ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                return new ResponseEntity<>("Some Error Occurred!",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
