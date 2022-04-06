package uz.pdp.instagramclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.instagramclone.entity.AttachmentContent;

import java.util.Optional;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);

}
