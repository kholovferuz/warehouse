package uz.pdp.springbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapp.Entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentReposiory extends JpaRepository<AttachmentContent,Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
}
