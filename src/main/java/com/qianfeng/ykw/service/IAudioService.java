package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.Audio;
import com.qianfeng.ykw.pojo.DeleteAudio;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IAudioService {
    /**
     * 上传音频
     * @param multipartFile
     * @param request
     * @return
     */
    boolean upload(Audio audio, MultipartFile multipartFile, HttpServletRequest request) throws IOException;

    /**
     * 查询指定用户上传的音频数据
     * @param businessId
     * @return
     */
    List<Audio> selectAudioInfoByBusinessId(int businessId);

    /**
     * 查询指定用户上传的音频数据
     * @return
     */
    List<Audio> selectAllAudio();

    /**
     * 按指定要求查询用户音频
     * @param parameter
     * @return
     */
    List<Audio> selectAudioByDateAndName(Map<String, Object> parameter);
    
    
    /**
     * 将指定的音频移动到回收站
     * @param param
     */
    void moveAudioToRecycleBinProcByIdAndType(Map<String, Object> param);
    
    /**
     * 将回收站中指定的音频还原
     * @param audioId
     */
    boolean recoverAudioFromRecycleBinProcById(int audioId);
    
    /**
     * 获取回收站中所有音频
     * @param request
     * @return
     */
    List<DeleteAudio> selectAllRecycleBinAudio(HttpServletRequest request);
    
    /**
     * 根据商户id获取回收站中的音频
     * @param businessId
     * @return
     */
    List<DeleteAudio> selectRecycleBinAudioByBusinessId(int businessId, HttpServletRequest request);
    
    /**
     * 将指定的音频从回收站删除
     * @param audioId
     * @param request
     * @return
     */
    boolean deleteAudioPermanently(int audioId, HttpServletRequest request);

}
