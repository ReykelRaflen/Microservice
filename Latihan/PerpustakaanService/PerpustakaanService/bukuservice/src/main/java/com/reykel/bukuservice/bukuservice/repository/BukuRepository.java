package com.reykel.bukuservice.bukuservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reykel.bukuservice.bukuservice.model.Buku;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {
}