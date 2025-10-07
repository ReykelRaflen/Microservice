package com.reykel.bukuservice.repository;

import com.reykel.bukuservice.model.BukuReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BukuReadRepository extends MongoRepository<BukuReadModel, Long> {
    BukuReadModel findByBukuId(Long bukuId);
}
