package com.pht.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Controller
public class FileUploadController {
    @RequestMapping("/upload")
    public String uoloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request instanceof MultipartHttpServletRequest ){
        new File("D://text22.txt");
            MultipartHttpServletRequest request1 =(MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = request1.getFileMap();
            for (Map.Entry<String,MultipartFile> filemap:fileMap.entrySet()){
                String fileName = filemap.getKey();
                MultipartFile multipartFile =filemap.getValue();
                multipartFile.transferTo(new File("D://text.txt"));
            }
        }
        return "1";
    }
}
