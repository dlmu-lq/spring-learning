package top.itlq.spring.guides.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("upload")
public class UploadFileController {

    private final FileSystemStorageService storageService;

    @Autowired
    public UploadFileController(FileSystemStorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/")
    public List<String> uploadedList(){
        return storageService.loadAllAsUrl()
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public ResponseEntity upload(@RequestParam(name = "foo") MultipartFile file){
        storageService.store(file);
        return ResponseEntity.ok("上传成功");
    }
}
