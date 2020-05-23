package cn.thinking.signature.manager;

import cn.thinking.signature.pojo.SignatureData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MediaTypeHandleContext {
    private Map<MediaType, MediaTypeHandleService> mediaTypeMap;

    @Autowired
    private List<MediaTypeHandleService> strategyServices;

    private MediaTypeHandleService defaultStrategy;

    @PostConstruct
    private void init() {
        mediaTypeMap = new HashMap();
        strategyServices.forEach(strategy -> {
            List<MediaType> mediaTypes = strategy.getMediaTypes();
            if (CollectionUtils.isEmpty(mediaTypes)) {
                // if the strategy'strategy supported event is null or empty, means this is default strategy
                defaultStrategy = strategy;
                return;
            }
            mediaTypes.forEach(l -> mediaTypeMap.put(l, strategy));
        });
    }

    public void handle(MediaType mediaType, SignatureData requestData, HttpServletRequest httpRequest)  throws Exception{
        if (!mediaTypeMap.containsKey(mediaType)) {
            defaultStrategy.handleData(requestData, httpRequest);
            return;
        }
        mediaTypeMap.get(mediaType).handleData(requestData, httpRequest);
    }
}
