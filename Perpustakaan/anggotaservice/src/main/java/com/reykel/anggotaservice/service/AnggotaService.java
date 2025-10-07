package com.reykel.anggotaservice.service;

import com.reykel.anggotaservice.model.Anggota;
import com.reykel.anggotaservice.repository.AnggotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnggotaService {

    @Autowired
    private AnggotaRepository repo;

    // ===== CRUD =====
    public List<Anggota> getAll() {
        return repo.findAll();
    }

    public Anggota save(Anggota anggota) {
        return repo.save(anggota);
    }

    public Optional<Anggota> getById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
