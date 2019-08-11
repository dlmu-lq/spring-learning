package top.itlq.spring.exampleBeans.applicationContextAdditional;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

@Component
public class ResourceLoaderTest implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void loadResource(String url) throws IOException {
        Resource resource = resourceLoader.getResource(url);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        resource.readableChannel().read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.print((char) buffer.get());
        }
        System.out.println();
        buffer.flip();
        CharBuffer charBuffer = Charset.forName("utf-8").decode(buffer);
        while (charBuffer.hasRemaining()){
            System.out.print(charBuffer.get());
        }
    }
}
