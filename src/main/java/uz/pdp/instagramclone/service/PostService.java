package uz.pdp.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.instagramclone.entity.Post;
import uz.pdp.instagramclone.entity.User;
import uz.pdp.instagramclone.payload.ApiResponse;
import uz.pdp.instagramclone.repository.PostRepository;
import uz.pdp.instagramclone.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ApiResponse getOnePost(Long id) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.getById(id);
            // frontga ketadigan
            return new ApiResponse("Found", true, post);
        }
        return new ApiResponse("Something went wrong", false);
    }

    public ApiResponse getUserPosts(Long user_id, Pageable pageable) {

        Page<Post> allByUser_id = postRepository.findAllByUser_Id(user_id, pageable);

        int pageNumber = pageable.getPageNumber();

        int totalPages = allByUser_id.getTotalPages();
        int number = pageable.getPageNumber();

        if (number > totalPages) {
            return new ApiResponse("wrong page number is written", false);
        }

        return new ApiResponse("Found all pages count:" + allByUser_id.getTotalPages(), true, allByUser_id);
    }

    public ApiResponse addToSavedPosts(Long post_id, Long user_id) {
        if (userRepository.existsById(user_id)) {
            User user = userRepository.getById(user_id);
            Set<Post> savedPosts = user.getSavedPosts();

            if (postRepository.existsById(post_id)) {
                Post post = postRepository.getById(post_id);

                // agar avvaldan mavjud bo'lsa uni savedddan chiqaradi saved degan tugmachasi istagramda
                if (!savedPosts.add(post)) {
                    savedPosts.remove(post);

                    userRepository.save(user);

                    return new ApiResponse("saved to saved posts", true, post);
                }
            }
        }
        return new ApiResponse("something went wrong", false);
    }

    public ApiResponse addToLikedPosts(Long post_id, Long user_id) {
        if (userRepository.existsById(user_id)) {
            User user = userRepository.getById(user_id);
            Set<Post> likedPosts = user.getLikedPosts();

            if (postRepository.existsById(post_id)) {
                Post post = postRepository.getById(post_id);

                // agar avvaldan mavjud bo'lsa uni savedddan chiqaradi saved degan tugmachasi istagramda
                if (likedPosts.add(post)) {
                    post.getPostLikedUsers().add(user);
                    postRepository.save(post);
                    return new ApiResponse("saved to saved posts", true, post);
                } else {
                    likedPosts.remove(post);
                    userRepository.save(user);
                }
            }
        }
        return new ApiResponse("something went wrong", false);
    }
}
