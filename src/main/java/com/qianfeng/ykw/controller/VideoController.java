package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.UserRoleType;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.SystemUser;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Controller
@RequestMapping("/VideoController")
public class VideoController {
    public static final int DELETE_BY_ADMINISTRATOR = 0;
    public static final int DELETE_BY_BUSINESS = 1;
    
    
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
    
    @RequestMapping("/deleteVideo")
    public String deleteVideo(int videoId, HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        UserRoleType userRoleType = (UserRoleType) request.getSession().getAttribute("UserRoleType");
        param.put("videoId", videoId);
        if (userRoleType == UserRoleType.ROLE_BUSINESS) {
            param.put("deleteType", DELETE_BY_ADMINISTRATOR);
            param.put("uid", null);
        } else if (userRoleType == UserRoleType.ROLE_ADMINISTRATOR) {
            SystemUser systemUser = (SystemUser) request.getSession().getAttribute("sustemUser");
            param.put("deleteType", DELETE_BY_BUSINESS);
            param.put("uid", systemUser.getUid());
        }
        videoService.moveVideoToRecycleBinProcByIdAndType(param);
        return "redirect:VideoController/queryVideo";
    }
}
