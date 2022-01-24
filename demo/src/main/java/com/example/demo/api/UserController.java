package com.example.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "uploadimage")
public class UserController {


    @PostMapping("/uploadimage")
    @ResponseStatus(HttpStatus.OK)
    public String saveUser(@RequestParam("image") MultipartFile imageFile) {
        String path = "";
        try {
            path = saveImage(imageFile);
        } catch (Exception e) {
            return e.getMessage() + "exception";
        }

        return path;
    }

    //service
    private String saveImage(MultipartFile imageFile) throws Exception {
        String folder = "photos/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        File file = new File("photos");
        System.out.println(file.getPath());
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        Path path1 =Files.write(path, bytes);
        System.out.println(path1.getFileSystem());
        System.out.println(path1.getRoot());
        return path + imageFile.getOriginalFilename();
    }
}