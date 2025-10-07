package com.reykel.anggotaservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "anggota") // nama collection MongoDB
public class AnggotaRead {

    @Id
    private String id; // MongoDB pakai String ID

    private String nama;
    private String email;
    private String telepon;

    // getter & setter
}
