package uz.pdp.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "messages")
public class Message {
    @Id
    private UUID id =  UUID.randomUUID();

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User sender;

    @CreationTimestamp
    private Timestamp createdAt;

}
