package top.itlq.spring.guides.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

@Component
@EnableConfigurationProperties(UploadProperties.class)
public class FileSystemStorageService implements StorageService{

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(UploadProperties uploadProperties){


        rootLocation = Paths.get(uploadProperties.getUploadLocation());
        init();
    }


    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("创建目录失败", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        // 对文件名做处理
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        if(file.isEmpty()){
            throw new StorageException("不能上传空文件");
        }
        if(filename.contains("..")){
            throw new StorageException("不能上传到相对路径");
        }
        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("上传失败", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("读取上传文件失败", e);
        }
    }

    public Stream<String> loadAllAsUrl() {
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(Path::toString);
        } catch (IOException e) {
            throw new StorageException("读取上传文件失败", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() && resource.isReadable()){
                return resource;
            }
            throw new StorageException("文件读取失败:" + filename);
        } catch (MalformedURLException e) {
            throw new StorageException("文件读取失败:" + filename);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
