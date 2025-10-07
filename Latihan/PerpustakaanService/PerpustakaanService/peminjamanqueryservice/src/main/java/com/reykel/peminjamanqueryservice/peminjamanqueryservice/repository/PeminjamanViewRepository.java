package com.reykel.peminjamanqueryservice.peminjamanqueryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reykel.peminjamanqueryservice.peminjamanqueryservice.model.PeminjamanView;

@Repository
public interface PeminjamanViewRepository extends MongoRepository<PeminjamanView, Long> {
}