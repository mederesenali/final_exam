package com.example.final_exam.controller;

import antlr.StringUtils;
import com.example.final_exam.model.Place;
import com.example.final_exam.model.Review;
import com.example.final_exam.model.User;
import com.example.final_exam.repository.PlaceRepository;
import com.example.final_exam.repository.ReviewRepository;
import com.example.final_exam.repository.UserRepository;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.UUID;

@Controller
public class PlaceController {
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

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


        return "redirect:/home";
    }
    @GetMapping("/placeDetail/{id}")
    public String bookDetail(@PathVariable("id") int id, Model model) {
        model.addAttribute("place", placeRepository.findById(id).get());
        model.addAttribute("reviews", reviewRepository.findAllByPlaceId(id));
        return "placeDetail";
    }
    @PostMapping("/createReview")
    public String reviews(Principal principal,@RequestParam String review,@RequestParam int rating,
                          @RequestParam int placeId,Model model){
        var user =userRepository.findByEmail(principal.getName());
        var place=placeRepository.findById(placeId).get();

        place.setReviewers(place.getReviewers()+1);
        placeRepository.save(place);

            var review1 = new Review();

            review1.setUser(user);
            review1.setReview(review);
            review1.setLocalDate(LocalDate.now());
            review1.setPlace(place);
            review1.setRating(rating);

            reviewRepository.save(review1);
        model.addAttribute("place", placeRepository.findById(placeId).get());
        model.addAttribute("reviews", reviewRepository.findAllByPlaceId(placeId));
        return "placeDetail";
    }

}
