package com.reykel.bukuservice.model;

public class BukuReadModel {

    private Long id;
    private Long bukuId; // field baru
    private String judul;
    private String pengarang;
    private String penerbit;
    private Integer tahunTerbit;

    public BukuReadModel() {}

    public BukuReadModel(Long id, Long bukuId, String judul, String pengarang, String penerbit, Integer tahunTerbit) {
        this.id = id;
        this.bukuId = bukuId;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBukuId() { return bukuId; }
    public void setBukuId(Long bukuId) { this.bukuId = bukuId; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getPengarang() { return pengarang; }
    public void setPengarang(String pengarang) { this.pengarang = pengarang; }

    public String getPenerbit() { return penerbit; }
    public void setPenerbit(String penerbit) { this.penerbit = penerbit; }

    public Integer getTahunTerbit() { return tahunTerbit; }
    public void setTahunTerbit(Integer tahunTerbit) { this.tahunTerbit = tahunTerbit; }
}
