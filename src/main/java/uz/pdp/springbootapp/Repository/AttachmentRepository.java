package uz.pdp.springbootapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapp.Entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
