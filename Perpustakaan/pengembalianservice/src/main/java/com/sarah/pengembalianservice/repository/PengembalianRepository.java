package com.sarah.pengembalianservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarah.pengembalianservice.model.Pengembalian;

@Repository
public interface PengembalianRepository extends JpaRepository<Pengembalian, Long> {
}
