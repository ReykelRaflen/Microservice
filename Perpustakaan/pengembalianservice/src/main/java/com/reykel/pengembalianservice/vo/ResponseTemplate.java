package com.reykel.pengembalianservice.vo;

import com.reykel.pengembalianservice.model.Pengembalian;
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
}
