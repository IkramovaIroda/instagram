package uz.pdp.instagramclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.instagramclone.entity.Comment;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
