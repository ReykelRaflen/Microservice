package com.sarah.pengembalianservice.model;

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
public class Pengembalian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tanggalDikembalikan;

    private Integer terlambat;  // jumlah hari keterlambatan
    private Long denda;         // nominal denda (misalnya 2000 * hari keterlambatan)

    private Long peminjamanId;
}
