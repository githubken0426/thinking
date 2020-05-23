package cn.thinking.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 公共全局配置类
 */
@Component
@ConfigurationProperties(prefix = "global")
public class GlobalProperties {
    private int pageSize;
    private String successAdd;
    private String successUpdate;
    private String successDelete;
    private String successImport;
    private String errorAdd;
    private String errorUpdate;
    private String errorDelete;
    private String errorImport;

    private String uploadPath;// 文件上传路径
    private String fileRequestUrl;// 文件访问路径

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSuccessAdd() {
        return successAdd;
    }

    public void setSuccessAdd(String successAdd) {
        this.successAdd = successAdd;
    }

    public String getSuccessUpdate() {
        return successUpdate;
    }

    public void setSuccessUpdate(String successUpdate) {
        this.successUpdate = successUpdate;
    }

    public String getSuccessDelete() {
        return successDelete;
    }

    public void setSuccessDelete(String successDelete) {
        this.successDelete = successDelete;
    }

    public String getSuccessImport() {
        return successImport;
    }

    public void setSuccessImport(String successImport) {
        this.successImport = successImport;
    }

    public String getErrorAdd() {
        return errorAdd;
    }

    public void setErrorAdd(String errorAdd) {
        this.errorAdd = errorAdd;
    }

    public String getErrorUpdate() {
        return errorUpdate;
    }

    public void setErrorUpdate(String errorUpdate) {
        this.errorUpdate = errorUpdate;
    }

    public String getErrorDelete() {
        return errorDelete;
    }

    public void setErrorDelete(String errorDelete) {
        this.errorDelete = errorDelete;
    }

    public String getErrorImport() {
        return errorImport;
    }

    public void setErrorImport(String errorImport) {
        this.errorImport = errorImport;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getFileRequestUrl() {
        return fileRequestUrl;
    }

    public void setFileRequestUrl(String fileRequestUrl) {
        this.fileRequestUrl = fileRequestUrl;
    }
}
