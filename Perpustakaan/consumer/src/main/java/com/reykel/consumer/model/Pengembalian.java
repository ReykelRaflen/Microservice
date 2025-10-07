package com.sarah.consumer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "pengembalian_read")
public class Pengembalian {
    @Id
    private String id;

    private Long pengembalianId;
    private Long peminjamanId;
    private Integer terlambat;
    private Long denda;
    private LocalDate tanggalDikembalikan;

    // Getter & Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getPengembalianId() { return pengembalianId; }
    public void setPengembalianId(Long pengembalianId) { this.pengembalianId = pengembalianId; }

    public Long getPeminjamanId() { return peminjamanId; }
    public void setPeminjamanId(Long peminjamanId) { this.peminjamanId = peminjamanId; }

    public Integer getTerlambat() { return terlambat; }
    public void setTerlambat(Integer terlambat) { this.terlambat = terlambat; }

    public Long getDenda() { return denda; }
    public void setDenda(Long denda) { this.denda = denda; }

    public LocalDate getTanggalDikembalikan() { return tanggalDikembalikan; }
    public void setTanggalDikembalikan(LocalDate tanggalDikembalikan) { this.tanggalDikembalikan = tanggalDikembalikan; }
}
