package com.reykel.anggotaservice.repository;

import com.reykel.anggotaservice.model.AnggotaRead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnggotaReadRepository extends MongoRepository<AnggotaRead, String> {
}
