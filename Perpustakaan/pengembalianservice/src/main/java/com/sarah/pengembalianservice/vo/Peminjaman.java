package com.sarah.pengembalianservice.vo;

import java.time.LocalDate;

public class Peminjaman {
    private Long id;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private Long anggotaId;
    private Long bukuId;

    // getter & setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }
    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }
    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Long getAnggotaId() {
        return anggotaId;
    }
    public void setAnggotaId(Long anggotaId) {
        this.anggotaId = anggotaId;
    }

    public Long getBukuId() {
        return bukuId;
    }
    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }
}
