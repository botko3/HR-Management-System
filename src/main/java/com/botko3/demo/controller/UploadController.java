package com.botko3.demo.controller;

import com.botko3.demo.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        //log.info("文件上傳：{},{},{}",username,age,image);

        //unique file name
          //file type
        String fileName = image.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String extname = fileName.substring(index);
        String newFileName = UUID.randomUUID().toString()+extname;
        image.transferTo(new File("/Users/afrachan/Desktop/Javaweb/"+newFileName));
        return Result.success();
    }


}
