package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.pojo.Audio;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.dao.IAudioDAO;
import com.qianfeng.ykw.pojo.Audio;
import com.qianfeng.ykw.pojo.Video;
import com.qianfeng.ykw.service.IAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AudioController")
public class AudioController {
    @Autowired
    IAudioService audioService;

    /**
     * 上传音频文件
     * @param audio
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping("/uploadAudio")
    public String uploadAudio(Audio audio, MultipartFile multipartFile, HttpServletRequest request) {
        boolean result = false;
        try {
            result = audioService.upload(audio, multipartFile, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result) {
            request.setAttribute("msg","上传成功");
            return "/pages/audio/add_audio";
        } else {
            request.setAttribute("msg","上传失败");
            return null;
        }
    }

    /**
     * 查询当前商户所有音频
     * @param request
     * @return
     */
    @RequestMapping("/queryAudio")
    public String queryAudio(HttpServletRequest request) {
        int businessId = ((Business) request.getSession().getAttribute("business")).getBusinessId();
        List<Audio> audioList = audioService.selectAudioInfoByBusinessId(businessId);
        request.setAttribute("audioList", audioList);
        return "/pages/audio/query_audio";
    }

    /**
     * 查询所有商户所有音频
     * @param request
     * @return
     */
    @RequestMapping("/queryAllAudio")
    public String queryAllAudio(HttpServletRequest request) {
        List<Audio> audioList = audioService.selectAllAudio();
        request.setAttribute("audioList", audioList);
        return "/pages/audio/query_audio";
    }

    /**
     * 根据获取用户businessId查询对应音频
     * @param businessId
     * @param request
     * @return
     */
    @RequestMapping("/queryAudioById")
    public String queryAudioById(int businessId,HttpServletRequest request) {
        List<Audio> audioList = audioService.selectAudioInfoByBusinessId(businessId);
        request.setAttribute("audioList", audioList);
        return "/pages/audio/query_audio";
    }

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
