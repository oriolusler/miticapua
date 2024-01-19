package com.oriolsoler.pua.controller;

import com.oriolsoler.pua.entities.Image;
import com.oriolsoler.pua.entities.Pua;
import com.oriolsoler.pua.repository.ImageRepository;
import com.oriolsoler.pua.repository.PuaRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static com.oriolsoler.pua.entities.Pua.PUEROS;

@Controller
public class PuaController {

    private final PuaRepository puaRepository;
    private final ImageRepository imageRepository;

    public PuaController(PuaRepository puaRepository, ImageRepository imageRepository) {
        this.puaRepository = puaRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/")
    public String addProductUI(Model model) {
        model.addAttribute("pua", new Pua());
        model.addAttribute("pueros", PUEROS);
        return "add-pua";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Pua pua, @RequestParam("imageFile") List<MultipartFile> images) throws java.io.IOException, IOException {
        var puaSaved = puaRepository.save(pua);

        images.forEach(it -> {
            try {
                imageRepository.save(new Image(it.getBytes(), puaSaved, it.getContentType()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return "redirect:/resume";
    }

    @GetMapping("/resume")
    public String listProducts(Model model) {
        int year = LocalDate.now().getYear();
        var count = puaRepository.findAll()
                .stream()
                .filter(it -> it.getOccurredAt().getYear() == year)
                .count();

        model.addAttribute("puesCount", count);
        model.addAttribute("year", year);
        return "resume";
    }

    @GetMapping(value = "/get-image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        var image = imageRepository.findById(id).get();

        HttpHeaders headers = new HttpHeaders();
        byte[] media = image.getContent();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(media, headers, HttpStatus.OK);

    }
}
