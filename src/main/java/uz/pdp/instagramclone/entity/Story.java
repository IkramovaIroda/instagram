package uz.pdp.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user; // qaysi user uni ochgani

    private boolean isHighlighted = false;

    @CreationTimestamp // doim new bo'lganda saqlaydi
    private Timestamp createdAt;  // 24 hours check

    @OneToMany
    private List<Attachment> files;
}
