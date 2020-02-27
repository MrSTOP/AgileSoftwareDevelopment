package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.IVideoDAO;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Service
public class IVideoServiceImpl implements IVideoService {
    
    @Autowired
    IVideoDAO videoDAO;
    
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
            if (videoFileParentFile.mkdirs()) {
                throw new IOException("Create Folder Failed");
            }
        }
        //创建空文件
        if (!videoFile.exists()) {
            if (videoFile.createNewFile()) {
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
}
