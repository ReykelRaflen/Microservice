package com.sarah.pengembalianservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pengembalian")
public class Pengembalian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;                 // id pengembalian

    private Long peminjamanId;       // referensi ke peminjaman
    private LocalDate tanggalPinjam; // tanggal pinjam
    private LocalDate tanggalKembali;       // jatuh tempo
    private LocalDate tanggalDikembalikan;  // real dikembalikan
    private Integer terlambat;       // jumlah hari keterlambatan
    private Long denda;              // jumlah uang (Rp)

    public Pengembalian() {
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPeminjamanId() { return peminjamanId; }
    public void setPeminjamanId(Long peminjamanId) { this.peminjamanId = peminjamanId; }

    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(LocalDate tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }

    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(LocalDate tanggalKembali) { this.tanggalKembali = tanggalKembali; }

    public LocalDate getTanggalDikembalikan() { return tanggalDikembalikan; }
    public void setTanggalDikembalikan(LocalDate tanggalDikembalikan) { this.tanggalDikembalikan = tanggalDikembalikan; }

    public Integer getTerlambat() { return terlambat; }
    public void setTerlambat(Integer terlambat) { this.terlambat = terlambat; }

    public Long getDenda() { return denda; }
    public void setDenda(double denda2) { this.denda = (long) denda2; }
}
