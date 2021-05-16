package uz.pdp.springbootapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.springbootapp.DTO.Result;
import uz.pdp.springbootapp.Service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    // uploading new photo
    @PostMapping("/upload")
    public Result uploadPhoto(MultipartHttpServletRequest request) {
        return attachmentService.uploadFile(request);
    }

    // downloading to the database
    @GetMapping("/{id}")
    public void downloadPhoto(@PathVariable Integer id, HttpServletResponse response) {
        attachmentService.downloadFiles(id, response);
    }
}
