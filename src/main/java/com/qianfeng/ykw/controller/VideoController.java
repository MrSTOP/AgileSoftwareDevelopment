package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.UserRoleType;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.DeleteVideo;
import com.qianfeng.ykw.pojo.SystemUser;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
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
            param.put("deleteType", DeleteVideo.DELETE_BY_BUSINESS);
            param.put("uid", null);
        } else if (userRoleType == UserRoleType.ROLE_ADMINISTRATOR) {
            SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
            param.put("deleteType", DeleteVideo.DELETE_BY_ADMINISTRATOR);
            param.put("uid", systemUser.getUid());
        }
        videoService.moveVideoToRecycleBinProcByIdAndType(param);
        return "redirect:/VideoController/queryVideo";
    }
    
    @RequestMapping("/queryAllRecycleBinVideo")
    public String queryAllRecycleBinVideo(HttpServletRequest request) {
        List<DeleteVideo> recycleBinVideoList = videoService.selectAllRecycleBinVideo(request);
        request.setAttribute("recycleBinVideoList", recycleBinVideoList);
        return "/pages/video/video_recycle_bin";
    }
    
    @RequestMapping("/queryRecycleBinVideoByBusinessId")
    public String queryRecycleBinVideoByBusinessId(int businessId, HttpServletRequest request) {
        List<DeleteVideo> recycleBinVideoList = videoService.selectRecycleBinVideoByBusinessId(businessId, request);
        request.setAttribute("recycleBinVideoList", recycleBinVideoList);
        return "/pages/video/video_recycle_bin";
    }
    
    @RequestMapping("/recoverVideo")
    @ResponseBody
    public Map<String, Object> recoverVideo(int videoId) {
        Map<String, Object> result = new HashMap<>();
        if (videoService.recoverVideoFromRecycleBinProcById(videoId)) {
            result.put("result", true);
        } else {
            result.put("result", false);
        }
        return result;
    }
    
    @RequestMapping("/queryVideoByOther")
    public String queryVideoByOther(String selectbusiness_name,String startdate,String enddate,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();

        if(selectbusiness_name == null ||selectbusiness_name.isEmpty()){
            if(startdate == null||enddate == null ||startdate.isEmpty()||enddate.isEmpty()){
                map.put("selectType","0");
            }else{
                map.put("selectType","2");
            }
        }else{
            if(startdate == null||enddate == null||startdate.isEmpty()||enddate.isEmpty()){
                map.put("selectType","1");
            }else{
                map.put("selectType","3");
            }
        }
        map.put("business_name",selectbusiness_name);
        map.put("startdate",startdate);
        map.put("enddate",enddate);
        List<Video> videoList = videoService.selectVideoByDateAndName(map);
        request.setAttribute("videoList",videoList);
        return "pages/video/select_video";
    }

}
