package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.Audio;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IAudioService {
    /**
     * 上传音频
     * @param multipartFile
     * @param request
     * @return
     */
    boolean upload(Audio audio, MultipartFile multipartFile, HttpServletRequest request) throws IOException;

    /**
     * 查询指定用户上传的视频数据
     * @param businessId
     * @return
     */
    List<Audio> selectAudioInfoByBusinessId(int businessId);

    /**
     * 查询指定用户上传的视频数据
     * @return
     */
    List<Audio> selectAllAudio();
}
