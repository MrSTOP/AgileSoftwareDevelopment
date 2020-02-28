package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.Audio;

import java.util.List;

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
}
