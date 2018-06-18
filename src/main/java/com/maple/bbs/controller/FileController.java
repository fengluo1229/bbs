package com.maple.bbs.controller;


import com.maple.bbs.domain.Result;
import com.maple.bbs.domain.User;
import com.maple.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


//文件上传api
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    UserService userService;

    @PostMapping("/images")
    public Result singleFileUpload(@RequestParam("file")MultipartFile file,@RequestParam("fileName")String fileName){
        if(file.isEmpty()){
            return Result.resultMessage(500,"please select file");
        }try{
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            byte[] bytes = file.getBytes();
            Path path= Paths.get("web/upload/images/"+fileName+suffixName);
            Files.write(path,bytes);
            User user = userService.queryInfo(fileName);
            user.setHeadImg(fileName+suffixName);
            userService.modifyInfo(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.resultMessage(500,"upload error");
        }
        return Result.resultMessage(200,"upload success");
    }
}
