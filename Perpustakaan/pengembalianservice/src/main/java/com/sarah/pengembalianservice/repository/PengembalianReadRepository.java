package com.sarah.pengembalianservice.repository;

import com.sarah.pengembalianservice.model.Pengembalian;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengembalianReadRepository extends MongoRepository<Pengembalian, Long> {
}
