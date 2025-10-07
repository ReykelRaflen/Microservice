package com.reykel.anggotaservice.controller;

import com.reykel.anggotaservice.model.Anggota;
import com.reykel.anggotaservice.service.AnggotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anggota")
public class AnggotaController {

    @Autowired
    private AnggotaService service;

    @GetMapping
    public List<Anggota> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anggota> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Anggota save(@RequestBody Anggota anggota) {
        return service.save(anggota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
