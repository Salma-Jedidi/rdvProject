package com.example.RDV.entities;

import jakarta.persistence.*;
import lombok.Data;
@Embeddable
@Data
public class DossierMedical {
    @Column(name = "dossier_attachment_data")

    private byte[] attachmentData;
}
