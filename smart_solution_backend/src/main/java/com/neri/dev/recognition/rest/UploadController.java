package com.neri.dev.recognition.rest;

import com.dropbox.core.v2.files.FileMetadata;
import com.neri.dev.recognition.facerecognition.FaceDetectionService;
import com.neri.dev.recognition.service.DropboxServiceImpl;

import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    DropboxServiceImpl dropbox;
    
    @Autowired
    private FaceDetectionService faceDetectionService;


    @PostMapping(value ="/",  produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] upload(@RequestParam("file") MultipartFile file, String path) {
        try {
        	if ( !validateImage(file))
            {
               return new byte[1];
            }
        	byte[] bytes = faceDetectionService.detectFace(file).toImage();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            dropbox.uploadFile(path,inputStream);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @ResponseBody
    @PostMapping(value = "/faceDetect/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] detectFaceImage(@RequestParam("file") MultipartFile file) throws IOException {

       if ( !validateImage(file))
       {
          return new byte[1];
       }
        return faceDetectionService.detectFace(file).toImage();
    }


    @GetMapping("/")
    public ResponseEntity<InputStreamResource>  download(String path){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        FileMetadata metadata = dropbox.getFileDetails(path);
        InputStreamResource resource = new InputStreamResource(dropbox.downloadFile(path));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + metadata.getName())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(metadata.getSize())
                .body(resource);
    }
    
    private Boolean validateImage(MultipartFile image) {
        return image.getContentType().equals("image/jpeg");
    }
}
