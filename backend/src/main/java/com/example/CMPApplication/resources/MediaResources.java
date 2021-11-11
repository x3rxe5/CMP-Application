package com.example.CMPApplication.resources;


import com.example.CMPApplication.exceptions.ETMediaException;
import com.example.CMPApplication.models.Media;
import com.example.CMPApplication.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/chatroom/media")
public class MediaResources {

    @Autowired
    MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadMedia(HttpServletRequest request,
                                                          @RequestParam("file") MultipartFile file) throws ETMediaException {

        Map<String,String> map = new HashMap<>();

        try{
            int userId = (Integer) request.getAttribute("userId");
            Media up = mediaService.uploadMedia(userId,file);
            map.put("file",file.getOriginalFilename());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch(Exception e){
            System.out.print("Error log -> "+e);
            throw new ETMediaException("Error while uploading file");
        }
    }

}
