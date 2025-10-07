package com.reykel.peminjamanservice.repository;

import com.reykel.peminjamanservice.model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
}
