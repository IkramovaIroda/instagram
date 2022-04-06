package uz.pdp.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.instagramclone.entity.Story;
import uz.pdp.instagramclone.entity.User;
import uz.pdp.instagramclone.payload.ApiResponse;
import uz.pdp.instagramclone.payload.StoryDTO;
import uz.pdp.instagramclone.repository.StoryRepository;
import uz.pdp.instagramclone.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoryService {

    final StoryRepository storyRepository;
    final UserRepository userRepository;

    public ApiResponse add(StoryDTO dto) {

        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (optionalUser.isPresent()) {
            Story story = new Story();
            story.setFiles(dto.getFiles());
            story.setUser(optionalUser.get());
            story.setHighlighted(dto.isHighlighted());
            storyRepository.save(story);
            return new ApiResponse("This story successfully created!", true);
        }
        return new ApiResponse("This story not created!", false);
    }

    public ApiResponse delete(Long id) {
        Optional<Story> optionalStory = storyRepository.findById(id);
        if (optionalStory.isPresent()) {
            storyRepository.deleteById(id);
            return new ApiResponse("This story successfully deleted!", true);
        }
        return new ApiResponse("This story not found on data base!", false);
    }
}
