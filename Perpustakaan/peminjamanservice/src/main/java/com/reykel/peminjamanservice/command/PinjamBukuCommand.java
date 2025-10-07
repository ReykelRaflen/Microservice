package com.reykel.peminjamanservice.command;

import java.time.LocalDate;

public class PinjamBukuCommand {
    private Long anggotaId;
    private Long bukuId;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    // Constructor, getter & setter
    public PinjamBukuCommand() {}

    public PinjamBukuCommand(Long anggotaId, Long bukuId, LocalDate tanggalPinjam, LocalDate tanggalKembali) {
        this.anggotaId = anggotaId;
        this.bukuId = bukuId;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public Long getAnggotaId() { return anggotaId; }
    public void setAnggotaId(Long anggotaId) { this.anggotaId = anggotaId; }
    public Long getBukuId() { return bukuId; }
    public void setBukuId(Long bukuId) { this.bukuId = bukuId; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(LocalDate tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(LocalDate tanggalKembali) { this.tanggalKembali = tanggalKembali; }
}
