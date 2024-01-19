package com.oriolsoler.pua.controller;


import com.oriolsoler.pua.entities.Pua;
import com.oriolsoler.pua.repository.PuaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final PuaRepository puaRepository;

    public EmployeeController(PuaRepository puaRepository) {
        this.puaRepository = puaRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/employees")
    public Iterable<Pua> findAllEmployees() {
        return this.puaRepository.findAll();
    }

    @PostMapping("/employees")
    public Pua addOneEmployee(@RequestBody Pua pua) {
        return this.puaRepository.save(pua);
    }
}
