package uz.pdp.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "comments")
public class Comment {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @CreationTimestamp // doim new bo'lganda saqlaydi
    private Timestamp createdAt;

    private boolean isDeleted = false;

    @ManyToMany
    private List<User> commentLikedUsers;
}
