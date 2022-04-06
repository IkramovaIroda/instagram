package uz.pdp.instagramclone.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.instagramclone.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowUser {

    private User user;

    private boolean isFollowed;

}
