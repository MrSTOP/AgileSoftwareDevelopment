package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.Audio;

import java.util.List;
import java.util.Map;

public interface IAudioService {
    /**
     * 按指定要求查询用户视频
     * @param parameter
     * @return
     */
    List<Audio> selectVideoByDateAndName(Map<String, Object> parameter);

}
