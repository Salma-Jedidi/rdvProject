package com.example.RDV.entities;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Attachment {
    @Column(name = "attachment_attachment_data")
    private byte[] attachmentData;
}
