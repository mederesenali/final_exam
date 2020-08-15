package com.example.final_exam.controller;

import com.example.final_exam.model.Place;
import com.example.final_exam.model.User;
import com.example.final_exam.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class PlaceController {
    @Autowired
    PlaceRepository placeRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/addPlace")
    public String addPlace(){
        return "addPlace";
    }
    @PostMapping("/addPlace")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String description,
            @RequestParam String tag, Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Place message = new Place();
        message.setTag(tag);
        message.setDescription(description);


        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }

        placeRepository.save(message);


        return "userPage";
    }

}
