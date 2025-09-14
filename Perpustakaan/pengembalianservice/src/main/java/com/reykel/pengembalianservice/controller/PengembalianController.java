package com.reykel.pengembalianservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reykel.pengembalianservice.model.Pengembalian;
import com.reykel.pengembalianservice.service.PengembalianService;
import com.reykel.pengembalianservice.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/pengembalian")
public class PengembalianController {

    @Autowired
    private PengembalianService service;

    // 🔹 Get all pengembalian
    @GetMapping
    public List<Pengembalian> getAll() {
        return service.getAll();
    }

    // 🔹 Save pengembalian baru
    @PostMapping
    public Pengembalian save(@RequestBody Pengembalian pengembalian) {
        return service.save(pengembalian);
    }

    // 🔹 Get pengembalian by ID (hanya data pengembalian)
    @GetMapping("/{id}")
    public ResponseEntity<Pengembalian> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Delete pengembalian
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 🔹 Get pengembalian detail (dengan peminjaman, anggota, buku)
    @GetMapping("/{id}/detail")
    public ResponseEntity<ResponseTemplate> getPengembalianWithDetail(@PathVariable Long id) {
        ResponseTemplate response = service.getPengembalianWithDetail(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
