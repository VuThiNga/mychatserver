package com.ngagerrard.controller;

import com.ngagerrard.model.response.BaseResponse;
import com.ngagerrard.model.response.ResponseUtils;
import com.ngagerrard.storage.service.FireBaseStoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping(value = "/image")
public class ControllerImage {
    @Autowired
    private FireBaseStoreManager firebaseStoreManager;


    @GetMapping(value = "image")
    @ResponseBody
    public ResponseEntity getImage(@RequestParam(required = true, value = "nameImage") String nameImage) {
        try {
            File file = new File("/Users/ngagerrard/Desktop/" + nameImage);
            InputStream inputStreaminputStream = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource(inputStreaminputStream);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .lastModified(file.lastModified())
                    .contentLength(file.length())
                    .body(inputStreamResource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.accepted()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseUtils.getBaseResponse(404, "find not found"));
        }
    }

    @PostMapping(value = "/postImage")
    public ResponseEntity postImage(
            @RequestParam(value = "image") MultipartFile image
    ) {
        try {
            return new ResponseEntity(new BaseResponse(BaseResponse.CODE_SUCCESS, "Succeed",
                    firebaseStoreManager.uploadFile(image)), HttpStatus.OK) ;
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(new BaseResponse(BaseResponse.ERROR_PARAM, "Failed",
                    "null"), HttpStatus.OK) ;
        }
    }

    @GetMapping(
            value = "/getImage", produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType(
            @RequestParam(value = "image") String image
    ) {
        return firebaseStoreManager.getImage(image);

    }
}
