package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.UserRoleType;
import com.qianfeng.ykw.dao.BusinessDAO;
import com.qianfeng.ykw.dao.IAudioDAO;
import com.qianfeng.ykw.dao.IDeleteAudioDAO;
import com.qianfeng.ykw.pojo.Audio;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.DeleteAudio;
import com.qianfeng.ykw.pojo.DeleteVideo;
import com.qianfeng.ykw.service.IAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class IAudioServiceImpl implements IAudioService {
    @Autowired
    IAudioDAO audioDAO;
    @Autowired
    BusinessDAO businessDAO;
    @Autowired
    IDeleteAudioDAO deleteAudioDAO;

    /**
     * 上传音频
     * @param audio
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     */
    @Override
    public boolean upload(Audio audio, MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        int businessId = ((Business)request.getSession().getAttribute("business")).getBusinessId();
        //准备文件在服务器上保存的位置
        String uuid = UUID.randomUUID().toString();
        String rootPath = request.getServletContext().getRealPath("");
        String fileSrc = "audios/" + uuid + "-" +multipartFile.getOriginalFilename();
        String filePath = rootPath + fileSrc;
        File audioFile = new File(filePath);
        //判断文件夹是否存在，不存在则创建
        File audioFileParentFile = audioFile.getParentFile();
        if (!audioFileParentFile.exists()) {
            if (!audioFileParentFile.mkdirs()) {
                throw new IOException("Create Folder Failed");
            }
        }
        //创建空文件
        if (!audioFile.exists()) {
            if (!audioFile.createNewFile()) {
                throw new IOException("Create Empty File Failed");
            }
        }
        //将上传的文件拷贝到新文件夹
        multipartFile.transferTo(audioFile);
        //接收数据
        audio.setBusinessId(businessId);
        audio.setAudioSrc(fileSrc);
        //数据保存
        return audioDAO.insertNewAudio(audio) > 0;
    }

    @Override
    public List<Audio> selectAudioInfoByBusinessId(int businessId) {
        return audioDAO.selectAudioInfoByBusinessId(businessId);
    }

    @Override
    public List<Audio> selectAllAudio() {
        return audioDAO.selectAllAudio();
    }

    /**
     * 按指定要求查询用户音频
     * @param parameter
     * @return
     */
    @Override
    public List<Audio> selectAudioByDateAndName(Map<String, Object> parameter) {
        String selectType = (String)parameter.get("selectType");

        if(selectType == "0"){//没有条件

            return audioDAO.selectAudio();
        }else if(selectType == "1"){//通过名字查找
            String businessUsername = (String) parameter.get("business_name");

            return audioDAO.selectAudioInfoByBusinessId(businessDAO.selectByBusinessName(businessUsername).getBusinessId());
        }else if(selectType == "2"){//通过时间查找
            return audioDAO.selectAudioInfoByDate(parameter);
        }else if(selectType == "3"){//通过时间名字同时查找
            String businessUsername = (String) parameter.get("business_name");
            int business_id = businessDAO.selectByBusinessName(businessUsername).getBusinessId();
            parameter.put("business_id",business_id);
            return audioDAO.selectAudioInfoByDateAndId(parameter);
        }
        return null;
    }
    
    
    @Override
    public void moveAudioToRecycleBinProcByIdAndType(Map<String, Object> param) {
        deleteAudioDAO.moveAudioToRecycleBinProcByIdAndType(param);
    }
    
    @Override
    public boolean recoverAudioFromRecycleBinProcById(int audioId) {
        try {
            deleteAudioDAO.recoverAudioFromRecycleBinProcById(audioId);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<DeleteAudio> selectAllRecycleBinAudio(HttpServletRequest request) {
        UserRoleType userRoleType = (UserRoleType) request.getSession().getAttribute("UserRoleType");
        List<DeleteAudio> deleteAudioList = deleteAudioDAO.selectAllRecycleBinAudio();
        setAudioIsRecoverable(deleteAudioList, userRoleType);
        return deleteAudioList;
    }
    
    @Override
    public List<DeleteAudio> selectRecycleBinAudioByBusinessId(int businessId, HttpServletRequest request) {
        UserRoleType userRoleType = (UserRoleType) request.getSession().getAttribute("UserRoleType");
        List<DeleteAudio> deleteAudioList = deleteAudioDAO.selectRecycleBinAudioByBusinessId(businessId);
        setAudioIsRecoverable(deleteAudioList, userRoleType);
        return deleteAudioList;
    }
    
    @Override
    public boolean deleteAudioPermanently(int audioId, HttpServletRequest request) {
        DeleteAudio deleteAudio = deleteAudioDAO.selectDeleteAudioById(audioId);
        if (deleteAudioDAO.deleteAudioFromRecycleBinById(audioId) > 0) {
            String rootPath = request.getServletContext().getRealPath("");
            File audioFile = new File(rootPath, deleteAudio.getAudioSrc());
            if (!audioFile.delete()) {
                throw new IllegalStateException("Delete File Failed.");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 设置音频是否可以被还原
     * @param deleteAudioList
     * @param userRoleType
     */
    private void setAudioIsRecoverable(List<DeleteAudio> deleteAudioList, UserRoleType userRoleType) {
        for (DeleteAudio deleteAudio: deleteAudioList) {
            if (userRoleType == UserRoleType.ROLE_ADMINISTRATOR) {
                deleteAudio.setRecoverable(true);
            } else if (userRoleType == UserRoleType.ROLE_BUSINESS) {
                switch (deleteAudio.getDeleteType()) {
                    case DeleteAudio.DELETE_BY_BUSINESS:
                        deleteAudio.setRecoverable(true);
                        break;
                    case DeleteAudio.DELETE_BY_ADMINISTRATOR:
                    default:
                        deleteAudio.setRecoverable(false);
                        break;
                }
            }
        }
    }
}
