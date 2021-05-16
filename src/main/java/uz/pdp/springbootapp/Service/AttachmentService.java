package uz.pdp.springbootapp.Service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Entity.Attachment;
import uz.pdp.springbootapp.Entity.AttachmentContent;
import uz.pdp.springbootapp.Repository.AttachmentContentReposiory;
import uz.pdp.springbootapp.Repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentReposiory attachmentContentReposiory;

    // UPLOAD file to the database
    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContent_type(file.getContentType());
            Attachment savedAttachment = attachmentRepository.save(attachment);

            // attachment content
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentReposiory.save(attachmentContent);

            return new Result("Photo uploaded", true, savedAttachment.getId());
        }
        return new Result("Error in uploading a file", false);
    }

    // DOWNLOAD file from database
    @SneakyThrows
    public void downloadFiles(Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();

            Optional<AttachmentContent> attachmentId = attachmentContentReposiory.findByAttachmentId(id);
            if (attachmentId.isPresent()) {
                AttachmentContent attachmentContent = attachmentId.get();
                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContent_type());

                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }
    }
}
