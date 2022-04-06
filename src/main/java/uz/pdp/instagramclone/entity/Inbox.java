package uz.pdp.instagramclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "inboxes")
public class Inbox {
    @Id
    private UUID id = UUID.randomUUID();



    @ManyToOne(cascade = CascadeType.ALL)
    private User receiver;

    @ManyToOne
    private Direct direct;
}
