package cn.thinking.signature.manager;

import cn.thinking.signature.pojo.SignatureData;

import javax.servlet.http.HttpServletRequest;

public abstract class MediaTypeBaseStrategy implements MediaTypeHandleService {

    @Override
    public void handleData(SignatureData requestData, HttpServletRequest httpRequest)throws Exception {
        doHandle(requestData, httpRequest);
    }

    protected abstract void doHandle(SignatureData requestData, HttpServletRequest httpRequest) throws Exception;
}
