package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.IAudioDAO;
import com.qianfeng.ykw.pojo.Audio;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.service.IAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class IAudioServiceImpl implements IAudioService {
    @Autowired
    IAudioDAO audioDAO;

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
        String rootPath = request.getServletContext().getRealPath("");
        String filePath = rootPath + "/audios/" + multipartFile.getOriginalFilename();
        System.out.println(filePath);
        File audioFile = new File(filePath);
        //判断文件夹是否存在，不存在则创建
        File audioFileParentFile = audioFile.getParentFile();
        if (!audioFileParentFile.exists()) {
            if (audioFileParentFile.mkdirs()) {
                throw new IOException("Create Folder Failed");
            }
        }
        //创建空文件
        if (!audioFile.exists()) {
            if (audioFile.createNewFile()) {
                throw new IOException("Create Empty File Failed");
            }
        }
        //将上传的文件拷贝到新文件夹
        multipartFile.transferTo(audioFile);
        //接收数据
        audio.setBusinessId(businessId);
        audio.setAudioSrc("/audios/" + multipartFile.getOriginalFilename());
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
}
