package com.pidev.restController;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@CrossOrigin
public class FileRestController {

     Cloudinary cloudinary;
    public FileRestController( Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @GetMapping("/getByPublicId/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        try {
            // Fetch file details from Cloudinary
            Map<String, Object> result = cloudinary.api().resource(id, ObjectUtils.asMap(
                    "resource_type", "raw" // You can specify image, video, etc.
            ));

            // Get the secure URL for the file (public files)
            String fileUrl = (String) result.get("secure_url");

            // Fetch the file as a byte array from the URL
            byte[] fileBytes = new java.net.URL(fileUrl).openStream().readAllBytes();

            if (fileBytes == null || fileBytes.length == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Convert to ByteArrayResource (Spring's resource wrapper)
            ByteArrayResource resource = new ByteArrayResource(fileBytes);

            // Set appropriate headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF); // Force the content type as PDF
            headers.setContentLength(fileBytes.length);
            headers.setContentDispositionFormData("attachment", id + ".pdf"); // Set file extension as .pdf


            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
