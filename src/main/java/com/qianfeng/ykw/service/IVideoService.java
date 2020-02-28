package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.DeleteVideo;
import com.qianfeng.ykw.pojo.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    /**
     * 按指定要求查询用户视频
     * @param parameter
     * @return
     */
    List<Video> selectVideoByDateAndName(Map<String, Object> parameter);
    
    /**
     * 将指定的视频移动到回收站
     * @param param
     */
    void moveVideoToRecycleBinProcByIdAndType(Map<String, Object> param);
    
    /**
     * 将回收站中指定的视频还原
     * @param videoId
     */
    boolean recoverVideoFromRecycleBinProcById(int videoId);
    
    /**
     * 获取回收站中所有视频
     * @param request
     * @return
     */
    List<DeleteVideo> selectAllRecycleBinVideo(HttpServletRequest request);
}
