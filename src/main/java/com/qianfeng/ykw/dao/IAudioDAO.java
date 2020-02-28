package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.Audio;

import java.util.List;
import java.util.Map;

public interface IAudioDAO {
    /**
     * 插入新音频信息
     * @param audio
     * @return
     */
    int insertNewAudio(Audio audio);

    /**
     * 查询指定用户上传的视频数据
     * @param businessId
     * @return
     */
    List<Audio> selectAudioInfoByBusinessId(int businessId);

    /**
     * 查询所有用户上传的视频数据
     * @return
     */
    List<Audio> selectAllAudio();

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
