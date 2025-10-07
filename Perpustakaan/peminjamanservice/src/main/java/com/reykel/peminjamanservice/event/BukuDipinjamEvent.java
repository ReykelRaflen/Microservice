package com.reykel.peminjamanservice.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BukuDipinjamEvent {
    private Long peminjamanId;
    private Long anggotaId;
    private Long bukuId;
}
