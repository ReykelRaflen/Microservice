package com.reykel.bukuservice.controller;

import com.reykel.bukuservice.model.Buku;
import com.reykel.bukuservice.service.BukuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buku")
public class BukuController {

    private final BukuService bukuService;

    public BukuController(BukuService bukuService) {
        this.bukuService = bukuService;
    }

    @PostMapping
    public ResponseEntity<Buku> create(@RequestBody Buku buku) {
        Buku saved = bukuService.create(buku);
        return ResponseEntity.ok(saved);
    }
}
