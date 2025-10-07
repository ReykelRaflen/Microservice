package com.reykel.anggotaservice.repository;

import com.reykel.anggotaservice.model.Anggota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnggotaRepository extends JpaRepository<Anggota, Long> {
}
