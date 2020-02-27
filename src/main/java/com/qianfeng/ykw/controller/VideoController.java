package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Controller
@RequestMapping("/VideoController")
public class VideoController {
    
    @Autowired
    IVideoService videoService;
    
    /**
     * 上传视频文件
     * @param video
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping("/uploadVideo")
    public String uploadVideo(Video video, MultipartFile multipartFile, HttpServletRequest request) {
        boolean result = false;
        try {
            result = videoService.upload(video, multipartFile, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result) {
            return "/pages/video/add_video";
        } else {
            return null;
        }
    }
    
    @RequestMapping("/queryVideo")
    public String queryVideo(HttpServletRequest request) {
        int businessId = ((Business) request.getSession().getAttribute("business")).getBusinessId();
        List<Video> videoList = videoService.selectVideoInfoByBusinessId(businessId);
        request.setAttribute("videoList", videoList);
        return "/pages/video/query_video";
    }
}
