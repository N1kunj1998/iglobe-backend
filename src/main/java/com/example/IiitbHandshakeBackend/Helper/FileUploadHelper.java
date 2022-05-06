package com.example.IiitbHandshakeBackend.Helper;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    public final String UPLOAD_DIR = (new ClassPathResource("static/image/")).getFile().getAbsolutePath();
//    public final String UPLOAD_DIR = "/home/nikunj/Documents/iiitb-handshake/Iiitb-Handshake-Backend/src/main/resources/static/image";
    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile multipartFile, String key) {
        boolean flag = false;

        try {
            Files.copy(multipartFile.getInputStream(), Paths.get(this.UPLOAD_DIR + File.separator + key +multipartFile.getOriginalFilename()), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            flag = true;
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return flag;
    }

//    @GetMapping("/{filename}")
//    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
//        byte[] image = new byte[0];
//        try {
//            image = FileUtils.readFileToByteArray(new File(UPLOAD_DIR+filename));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
//    }
}
