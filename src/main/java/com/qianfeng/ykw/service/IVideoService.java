package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface IVideoService {
    
    /**
     * 上传视频
     * @param multipartFile
     * @param request
     * @return
     */
    boolean upload(Video video, MultipartFile multipartFile, HttpServletRequest request) throws IOException;
    
    /**
     * 查询指定用户上传的视频数据
     * @param businessId
     * @return
     */
    List<Video> selectVideoInfoByBusinessId(int businessId);
}
