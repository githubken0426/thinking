package cn.thinking.signature.manager;

import cn.thinking.signature.pojo.SignatureData;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MediaTypeHandleService {
    void handleData(SignatureData requestData, HttpServletRequest httpRequest) throws Exception;

    List<MediaType> getMediaTypes();
}
