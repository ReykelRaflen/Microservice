package com.reykel.peminjamanservice.repository;

import com.reykel.peminjamanservice.model.Peminjaman;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeminjamanReadRepository extends MongoRepository<Peminjaman, Long> {
}
