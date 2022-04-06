package uz.pdp.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileOriginalName;//pdp.jpg

    private long size;//1024000

    private String contentType;//image/png

    //bu file systemga saqlaganda kk boladi
    private String name;//papkani ichidan topish uchun

    public Attachment(String fileOriginalName, long size, String contentType) {
        this.fileOriginalName = fileOriginalName;
        this.size = size;
        this.contentType = contentType;
    }
}
