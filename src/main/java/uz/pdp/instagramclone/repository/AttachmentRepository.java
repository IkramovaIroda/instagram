package uz.pdp.instagramclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.instagramclone.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
