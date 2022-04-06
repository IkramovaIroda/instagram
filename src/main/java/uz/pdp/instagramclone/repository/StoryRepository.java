package uz.pdp.instagramclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.instagramclone.entity.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
