package uz.pdp.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserSavedPosts {
    @Id
    private UUID id = UUID.randomUUID();

    @OneToOne
    private User user;

    @OneToMany
    private Set<Post> savedPosts;





}
