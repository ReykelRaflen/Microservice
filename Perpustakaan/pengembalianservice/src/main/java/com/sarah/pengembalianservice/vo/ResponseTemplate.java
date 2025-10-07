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
    private long denda;
}
