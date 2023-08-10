package com.example.lightserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    public String uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("D:\\#. lab_401_image\\test\\"+file.getOriginalFilename()));
        return file.getOriginalFilename()+" Upload Completed";
    }
}
