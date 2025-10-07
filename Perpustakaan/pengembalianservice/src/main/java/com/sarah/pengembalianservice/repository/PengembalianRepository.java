package com.sarah.pengembalianservice.repository;

import com.sarah.pengembalianservice.model.Pengembalian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengembalianRepository extends JpaRepository<Pengembalian, Long> {
}
