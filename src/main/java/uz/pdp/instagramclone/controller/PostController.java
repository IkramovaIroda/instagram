package uz.pdp.instagramclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.instagramclone.payload.ApiResponse;
import uz.pdp.instagramclone.service.PostService;

@RequestMapping("/api/comments")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    /**
     * return one post using id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        ApiResponse apiResponse  = postService.getOnePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200:404).body(apiResponse);
    }

    /**
     * userning barcha postlari
     * @param page
     * @param user_id
     * @return
     */
    @GetMapping("/{user_id}/list") // user ning postlari ko'rini kerak
    public HttpEntity<?> listOfPosts(@RequestParam(defaultValue = "1",required = false) int page,
                                     @PathVariable Long user_id){
        Pageable pageable = PageRequest.of(page,10, Sort.by("createdAt")); // 10 dana chiqaradi

       ApiResponse apiResponse = postService.getUserPosts(user_id,pageable);

       return ResponseEntity.ok(apiResponse); // doim ok qaytadi chunki user bo'lsa yetadi
    }

    /**
     * adding posts to saved posts of user
     * @param post_id
     * @param user_id
     * @return
     */
    @PutMapping("/save_post/{user_id}/{post_id}")
    public HttpEntity<?> savePost(@PathVariable Long post_id,@PathVariable Long user_id){

        ApiResponse apiResponse = postService.addToSavedPosts(post_id,user_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200:409).body(apiResponse);
    }

      @PutMapping("/like_post/{user_id}/{post_id}")
    public HttpEntity<?> likePost(@PathVariable Long post_id,@PathVariable Long user_id){

        ApiResponse apiResponse = postService.addToLikedPosts(post_id,user_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200:409).body(apiResponse);
    }





}
