package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.UserRoleType;
import com.qianfeng.ykw.dao.BusinessDAO;
import com.qianfeng.ykw.dao.IVideoDAO;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.DeleteVideo;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Service
public class IVideoServiceImpl implements IVideoService {
    
    @Autowired
    IVideoDAO videoDAO;
    @Autowired
    BusinessDAO businessDAO;
    
    /**
     * 上传视频
     * @param video
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     */
    @Override
    public boolean upload(Video video, MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        int businessId = ((Business)request.getSession().getAttribute("business")).getBusinessId();
        //准备文件在服务器上保存的位置
        String rootPath = request.getServletContext().getRealPath("");
        String filePath = rootPath + "/videos/" + multipartFile.getOriginalFilename();
        System.out.println(filePath);
        File videoFile = new File(filePath);
        //判断文件夹是否存在，不存在则创建
        File videoFileParentFile = videoFile.getParentFile();
        if (!videoFileParentFile.exists()) {
            if (!videoFileParentFile.mkdirs()) {
                throw new IOException("Create Folder Failed");
            }
        }
        //创建空文件
        if (!videoFile.exists()) {
            if (!videoFile.createNewFile()) {
                throw new IOException("Create Empty File Failed");
            }
        }
        //将上传的文件拷贝到新文件夹
        multipartFile.transferTo(videoFile);
        //接收数据
        video.setBusinessId(businessId);
        video.setVideoSrc("/videos/" + multipartFile.getOriginalFilename());
        //数据保存
        return videoDAO.insertNewVideo(video) > 0;
    }
    
    @Override
    public List<Video> selectVideoInfoByBusinessId(int businessId) {
        return videoDAO.selectVideoInfoByBusinessId(businessId);
    }

    @Override
    public List<Video> selectAllVideo() {
        return videoDAO.selectAllVideo();
    }

    @Override
    public List<Video> selectVideoByDateAndName(Map<String, Object> parameter) {

        String selectType = (String)parameter.get("selectType");

        if(selectType == "0"){//没有条件

            return videoDAO.selectVideo();
        }else if(selectType == "1"){//通过名字查找
            String businessUsername = (String) parameter.get("business_name");

            return videoDAO.selectVideoInfoByBusinessId(businessDAO.selectByBusinessName(businessUsername).getBusinessId());
        }else if(selectType == "2"){//通过时间查找
            return videoDAO.selectVideoInfoByDate(parameter);
        }else if(selectType == "3"){//通过时间名字同时查找
            String businessUsername = (String) parameter.get("business_name");
            int business_id = businessDAO.selectByBusinessName(businessUsername).getBusinessId();
            parameter.put("business_id",business_id);
            return videoDAO.selectVideoInfoByDateAndId(parameter);
        }
        return null;
    }
    
    @Override
    public void moveVideoToRecycleBinProcByIdAndType(Map<String, Object> param) {
        videoDAO.moveVideoToRecycleBinProcByIdAndType(param);
    }
    
    @Override
    public boolean recoverVideoFromRecycleBinProcById(int videoId) {
        try {
            videoDAO.recoverVideoFromRecycleBinProcById(videoId);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<DeleteVideo> selectAllRecycleBinVideo(HttpServletRequest request) {
        UserRoleType userRoleType = (UserRoleType) request.getSession().getAttribute("UserRoleType");
        List<DeleteVideo> deleteVideoList = videoDAO.selectAllRecycleBinVideo();
        setVideoIsRecoverable(deleteVideoList, userRoleType);
        return deleteVideoList;
    }
    
    @Override
    public List<DeleteVideo> selectRecycleBinVideoByBusinessId(int businessId, HttpServletRequest request) {
        UserRoleType userRoleType = (UserRoleType) request.getSession().getAttribute("UserRoleType");
        List<DeleteVideo> deleteVideoList = videoDAO.selectRecycleBinVideoByBusinessId(businessId);
        setVideoIsRecoverable(deleteVideoList, userRoleType);
        return deleteVideoList;
    }
    
    /**
     * 设置视频是否可以被还原
     * @param deleteVideoList
     * @param userRoleType
     */
    private void setVideoIsRecoverable(List<DeleteVideo> deleteVideoList, UserRoleType userRoleType) {
        for (DeleteVideo deleteVideo: deleteVideoList) {
            if (userRoleType == UserRoleType.ROLE_ADMINISTRATOR) {
                deleteVideo.setRecoverable(true);
            } else if (userRoleType == UserRoleType.ROLE_BUSINESS) {
                switch (deleteVideo.getDeleteType()) {
                    case DeleteVideo.DELETE_BY_BUSINESS:
                        deleteVideo.setRecoverable(true);
                        break;
                    case DeleteVideo.DELETE_BY_ADMINISTRATOR:
                    default:
                        deleteVideo.setRecoverable(false);
                        break;
                }
            }
        }
    }
}
