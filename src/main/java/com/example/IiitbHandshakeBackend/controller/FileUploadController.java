package com.example.IiitbHandshakeBackend.controller;

import com.example.IiitbHandshakeBackend.Helper.FileUploadHelper;
import com.example.IiitbHandshakeBackend.entity.Donate;
import com.example.IiitbHandshakeBackend.entity.Room;
import com.example.IiitbHandshakeBackend.entity.Sell;
import com.example.IiitbHandshakeBackend.repo.DonateRepo;
import com.example.IiitbHandshakeBackend.repo.SellRepo;
import com.example.IiitbHandshakeBackend.repo.RoomRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@CrossOrigin("*")
@RestController
public class FileUploadController {

    public final String UPLOAD_DIR = (new ClassPathResource("static/image/")).getFile().getAbsolutePath();



    @Autowired
    private FileUploadHelper fileUploadHelper;

    @Autowired
    private DonateRepo donateRepo;

    @Autowired
    private SellRepo sellRepo;

    @Autowired
    private RoomRepo roomRepo;

    public FileUploadController() throws IOException{

    }

    @PostMapping({"/upload-file/{key}"})
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable("key") String key) {
        try {
            if (multipartFile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain file");
            }

            if (!multipartFile.getContentType().equals("image/jpeg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG content type are allowed");
            }

            boolean flag = this.fileUploadHelper.uploadFile(multipartFile, key);
            if(key.charAt(0) == 'd'){
                String donateId = key.substring(2);
                Donate donate = donateRepo.findById(Integer.parseInt(donateId)).orElse(null);
                if(donate != null){
                    donate.setImage(key + multipartFile.getOriginalFilename());
                    donateRepo.save(donate);
                }
            } else if(key.charAt(0) == 's') {
                String sellId = key.substring(2);
                Sell sell = sellRepo.findById(Integer.parseInt(sellId)).orElse(null);
                if(sell != null){
                    sell.setImage(key + multipartFile.getOriginalFilename());
                    sellRepo.save(sell);
                }
            } else if(key.charAt(0) == 'r') {
                String roomId = key.substring(2);
                Room room = roomRepo.findById(Integer.parseInt(roomId)).orElse(null);
                if(room != null){
                    room.setImage(key + multipartFile.getOriginalFilename());
                    roomRepo.save(room);
                }
            }
//            System.out.println(multipartFile.getOriginalFilename());
//            System.out.println(multipartFile.getContentType());
//            System.out.println(multipartFile.getName());
//            System.out.println(multipartFile.getBytes());
            if (flag) {
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(multipartFile.getOriginalFilename()).toUriString());
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try again");
    }

    @GetMapping("/getDonateImage")
    public ResponseEntity<byte[]> getImage() throws IOException{


        File img = new File( UPLOAD_DIR + "d_3120211006_174956.jpg");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    @GetMapping("/getImage/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException{
        File img = new File(UPLOAD_DIR +"/" +name);
        System.out.println(name);
        System.out.println(img);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }
}
