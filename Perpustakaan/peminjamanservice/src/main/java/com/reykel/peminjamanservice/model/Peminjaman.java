package com.reykel.peminjamanservice.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private Long anggotaId;
    private Long bukuId;

    // tambahan langsung di entity
    private String namaPeminjam;
    private String emailPeminjam;
    private String namaBuku;

    // Getter & Setter (atau pakai Lombok @Data)
}
