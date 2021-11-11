package com.example.CMPApplication.services;

import com.example.CMPApplication.exceptions.ETMediaException;
import com.example.CMPApplication.models.Media;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface MediaService {
    Media uploadMedia(int userId, MultipartFile file) throws ETMediaException, IOException;
}
