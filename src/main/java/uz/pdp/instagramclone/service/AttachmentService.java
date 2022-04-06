package uz.pdp.instagramclone.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.instagramclone.entity.Attachment;
import uz.pdp.instagramclone.entity.AttachmentContent;
import uz.pdp.instagramclone.payload.ApiResponse;
import uz.pdp.instagramclone.repository.AttachmentContentRepository;
import uz.pdp.instagramclone.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public ApiResponse upload(MultipartHttpServletRequest request) {
        //faqat nomlari
        Iterator<String> fileNames = request.getFileNames();
        //realniy fayllar

        while (fileNames.hasNext()) {
            List<MultipartFile> files = request.getFiles(fileNames.next());
            for (MultipartFile file : files) {
                Attachment attachment = new Attachment(
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType());

                Attachment save = attachmentRepository.save(attachment);
                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setAttachment(save);
                try {
                    attachmentContent.setAsosiyContent(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachmentContentRepository.save(attachmentContent);
            }
        }
        return new ApiResponse("Mana", true);
    }

    public void download(Integer id, HttpServletResponse response) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + attachment.getFileOriginalName());
                response.setContentType(attachment.getContentType());
                try {
                    FileCopyUtils.copy(attachmentContent.getAsosiyContent(), response.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
