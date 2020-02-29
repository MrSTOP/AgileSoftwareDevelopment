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
     *
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
            request.setAttribute("msg","上传成功");
            return "/pages/video/add_video";
        } else {
            request.setAttribute("msg","上传失败");
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

    /**
     * 查询所有商户所有视频
     * @param request
     * @return
     */
    @RequestMapping("/queryAllVideo")
    public String queryAllVideo(HttpServletRequest request) {
        List<Video> videoList = videoService.selectAllVideo();
        request.setAttribute("videoList", videoList);
        return "/pages/video/query_video";
    }

    /**
     * 根据获取用户businessId查询对应视频
     * @param businessId
     * @param request
     * @return
     */
    @RequestMapping("/queryVideoById")
    public String queryVideoById(int businessId,HttpServletRequest request){
        List<Video> videoList = videoService.selectVideoInfoByBusinessId(businessId);
        request.setAttribute("videoList", videoList);
        return "/pages/video/query_video";
    }
    
    /**
     * 将Id对应的视频移入回收站
     * @param videoId
     * @param request
     * @return
     */
    @RequestMapping("/deleteVideo")
    @ResponseBody
    public Map<String, Object> deleteVideo(int videoId, HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
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
        try {
            videoService.moveVideoToRecycleBinProcByIdAndType(param);
            result.put("result", true);
        } catch (DataAccessException e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }
    
    /**
     * 获取回收站中所有视频
     * @param request
     * @return
     */
    @RequestMapping("/queryAllRecycleBinVideo")
    public String queryAllRecycleBinVideo(HttpServletRequest request) {
        List<DeleteVideo> recycleBinVideoList = videoService.selectAllRecycleBinVideo(request);
        request.setAttribute("recycleBinVideoList", recycleBinVideoList);
        return "/pages/video/video_recycle_bin";
    }
    
    /**
     * 获取回收站中对应businessId的所有视频
     * @param businessId
     * @param request
     * @return
     */
    @RequestMapping("/queryRecycleBinVideoByBusinessId")
    public String queryRecycleBinVideoByBusinessId(int businessId, HttpServletRequest request) {
        List<DeleteVideo> recycleBinVideoList = videoService.selectRecycleBinVideoByBusinessId(businessId, request);
        request.setAttribute("recycleBinVideoList", recycleBinVideoList);
        return "/pages/video/video_recycle_bin";
    }
    
    /**
     * 根据用户登录身份确定查询视频的范围
     * @param request
     * @return
     */
    @RequestMapping("/queryRecycleBinVideo")
    public String queryRecycleBinVideo(HttpServletRequest request) {
        UserRoleType userRoleType = (UserRoleType) request.getSession().getAttribute("UserRoleType");
        if (userRoleType == UserRoleType.ROLE_ADMINISTRATOR) {
            return queryAllRecycleBinVideo(request);
        } else if (userRoleType == UserRoleType.ROLE_BUSINESS) {
            int businessId = ((Business) request.getSession().getAttribute("business")).getBusinessId();
            return queryRecycleBinVideoByBusinessId(businessId, request);
        }
        return null;
    }
    
    /**
     * 将回收站中对应Id的视频还原
     * @param videoId
     * @return
     */
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
    
    /**
     * 将回收站中对应Id的视频彻底删除
     * @param videoId
     * @param request
     * @return
     */
    @RequestMapping("/deleteVideoPermanently")
    @ResponseBody
    public Map<String, Object> deleteVideoPermanently(int videoId, HttpServletRequest request) {
        //TODO
        Map<String, Object> result = new HashMap<>();
        try {
            if (videoService.deleteVideoPermanently(videoId, request)) {
                result.put("result", true);
            } else {
                result.put("result", false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return result;
    }
    
    @RequestMapping("/queryVideoByOther")
    public String queryVideoByOther(String selectbusiness_name, String startdate, String enddate, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if (selectbusiness_name == null || selectbusiness_name.isEmpty()) {
            if (startdate == null || enddate == null || startdate.isEmpty() || enddate.isEmpty()) {
                map.put("selectType", "0");
            } else {
                map.put("selectType", "2");
            }
        } else {
            if (startdate == null || enddate == null || startdate.isEmpty() || enddate.isEmpty()) {
                map.put("selectType", "1");
            } else {
                map.put("selectType", "3");
            }
        }
        map.put("business_name", selectbusiness_name);
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        List<Video> videoList = videoService.selectVideoByDateAndName(map);
        request.setAttribute("videoList", videoList);
        return "pages/video/select_video";
    }
    
}
