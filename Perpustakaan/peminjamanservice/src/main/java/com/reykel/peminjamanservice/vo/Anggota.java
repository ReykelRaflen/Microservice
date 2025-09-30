package com.reykel.peminjamanservice.vo;

public class Anggota {
    private Long id;
    private String nim;
    private String nama;
    private String alamat;
    private String jenisKelamin;
    private String email;

    public Anggota() {
    }

    public Anggota(Long id, String nim, String nama, String alamat, String jenisKelamin, String email) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getEmail() {   // ✅ getter email
        return email;
    }

    public void setEmail(String email) {   // ✅ setter email
        this.email = email;
    }

    @Override
    public String toString() {
        return "Anggota{" +
                "id=" + id +
                ", nim='" + nim + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
