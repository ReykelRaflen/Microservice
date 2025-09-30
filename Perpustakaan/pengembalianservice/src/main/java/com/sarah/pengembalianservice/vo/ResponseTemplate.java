package com.sarah.pengembalianservice.vo;

import com.sarah.pengembalianservice.model.Pengembalian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {

    private Pengembalian pengembalian;
    private Peminjaman peminjaman;
    private Anggota anggota;
    private Buku buku;
    private long denda; // ✅ field baru untuk denda

    // Getter & Setter
    public Pengembalian getPengembalian() {
        return pengembalian;
    }

    public void setPengembalian(Pengembalian pengembalian) {
        this.pengembalian = pengembalian;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public long getDenda() {
        return denda;
    }

    public void setDenda(long denda) {
        this.denda = denda;
    }
}