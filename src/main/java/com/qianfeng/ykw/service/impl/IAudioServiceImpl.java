package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.BusinessDAO;
import com.qianfeng.ykw.dao.IAudioDAO;
import com.qianfeng.ykw.pojo.Audio;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class IAudioServiceImpl implements IAudioService {

    @Autowired
    IAudioDAO audioDAO;
    @Autowired
    BusinessDAO businessDAO;

    /**
     * 按指定要求查询用户视频
     * @param parameter
     * @return
     */
    @Override
    public List<Audio> selectVideoByDateAndName(Map<String, Object> parameter) {
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
}
