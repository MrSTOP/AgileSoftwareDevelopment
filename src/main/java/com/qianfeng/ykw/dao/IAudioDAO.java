package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.Audio;

import java.util.List;
import java.util.Map;

public interface IAudioDAO {

    /**
     * 通过用户ID查询音频
     * @param businessId
     * @return
     */
    List<Audio> selectAudioInfoByBusinessId(int businessId);


    /**
     * 通过时间段查询音频
     * @param parameter
     * @return
     */
    List<Audio> selectAudioInfoByDate(Map<String, Object> parameter);

    /**
     * 通过时间和Id查询音频
     * @param parameter
     * @return
     */
    List<Audio> selectAudioInfoByDateAndId(Map<String, Object> parameter);

    /**
     * 获取所有音频
     * @return
     */
    List<Audio> selectAudio();
}
