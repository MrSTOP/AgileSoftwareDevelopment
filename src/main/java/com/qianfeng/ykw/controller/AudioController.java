package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.dao.IAudioDAO;
import com.qianfeng.ykw.pojo.Audio;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AudioController")
public class AudioController {

    @Autowired
    IAudioService audioService;

    @RequestMapping("/queryAudioByOther")
    public String queryAudioByOther(String selectbusiness_name, String startdate, String enddate, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();

        if(selectbusiness_name == null ||selectbusiness_name.isEmpty()){
            if(startdate == null||enddate == null ||startdate.isEmpty()||enddate.isEmpty()){
                map.put("selectType","0");
            }else{
                map.put("selectType","2");
            }
        }else{
            if(startdate == null||enddate == null||startdate.isEmpty()||enddate.isEmpty()){
                map.put("selectType","1");
            }else{
                map.put("selectType","3");
            }
        }
        map.put("business_name",selectbusiness_name);
        map.put("startdate",startdate);
        map.put("enddate",enddate);
        List<Audio> audioList = audioService.selectVideoByDateAndName(map);
        request.setAttribute("audioList",audioList);
        return "pages/audio/select_audio";

    }

}
