package com.gucarsoft.bulutmdyemek.controller;

import com.gucarsoft.bulutmdyemek.model.Food;
import com.gucarsoft.bulutmdyemek.repository.FoodRepository;
import com.gucarsoft.bulutmdyemek.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/food")
public class FoodController extends BaseController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    OrderRepository orderRepository;

    String basePath = "src/resources/uploads";

    @PostMapping("/upload")
    public ModelAndView uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        orderRepository.deleteAll();

        Food existing = foodRepository.findByName("food");
        if (existing != null) {
            existing.setTimestamp(new Timestamp(System.currentTimeMillis()));
            foodRepository.save(existing);
        } else {
            Food food = new Food();
            food.setName("food");
            foodRepository.save(food);
        }

        Files.createDirectories(Paths.get(basePath));

        Path path = Paths.get(basePath + "/");
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve("food.jpeg"), StandardCopyOption.REPLACE_EXISTING);

        return new ModelAndView("redirect:" + "http://localhost:8090/");
    }

    @GetMapping("/get")
    public ResponseEntity<ByteArrayResource> getImage() throws IOException {
        Path path = Paths.get(basePath + "/");
        Path targetLocation = path.resolve("food.jpeg");
        byte[] buffer = Files.readAllBytes(targetLocation);
        ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);

        String exist = new SimpleDateFormat("yyyy.MM.dd").format(foodRepository.findByName("food").getTimestamp());
        String now =new SimpleDateFormat("yyyy.MM.dd").format(new Timestamp(System.currentTimeMillis()));

        System.out.println(exist);

        if (exist.equals(now)){
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpeg")).body(byteArrayResource);
        }
        return null;
    }


}
