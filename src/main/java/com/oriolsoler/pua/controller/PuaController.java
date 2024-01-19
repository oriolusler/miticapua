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


    @GetMapping("/add")
    public String addProductUI(Model model) {
        model.addAttribute("pua", new Pua());
        model.addAttribute("pueros", PUEROS);
        return "add-pua";
    }

    // Add a product API
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

        return "redirect:/get-products";
    }

    //Get all Products
    @GetMapping("/get-products")
    public String listProducts(Model model) {
        var puas = new HashMap<Pua, List<Image>>();
        puaRepository.findAll().forEach(it -> {
                    var images = imageRepository.findImageByPua(it);
                    puas.put(it, images);
                }
        );

        model.addAttribute("puas", puas);
        return "get-products";
    }


    @GetMapping(value = "/get-image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        var image = imageRepository.findById(id).get();

        HttpHeaders headers = new HttpHeaders();
        byte[] media = image.getContent();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;

    }

    //Get Image using product ID
    /*@GetMapping(value = "/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        Optional<Product> productOptional = productService.getProduct(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            byte[] imageBytes = java.util.Base64.getDecoder().decode(product.getImage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }
    }*/
}
