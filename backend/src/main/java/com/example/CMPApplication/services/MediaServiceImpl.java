package com.example.CMPApplication.services;

import com.example.CMPApplication.exceptions.ETMediaException;
import com.example.CMPApplication.models.Media;
import com.example.CMPApplication.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class MediaServiceImpl implements MediaService{

    @Autowired
    MediaRepository mediaRepository;

    @Override
    public Media uploadMedia(int userId, MultipartFile file) throws ETMediaException, IOException {
        String name = (String) file.getOriginalFilename();
        String contentType = (String) file.getContentType();
        byte[] data = file.getBytes();
        Long size = file.getSize();

        System.out.print("Name -> "+name + " contentType =>"+contentType+ " size => "+size+" data => "+data);

        Integer mediaId = mediaRepository.create(userId,name,size,contentType,data);

        return mediaRepository.findById(mediaId,userId);
    }
}
