package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.DeleteVideo;

import java.util.List;
import java.util.Map;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface IDeleteVideoDAO {
    
    /**
     * 将视频移到回收站
     * @param param
     */
    void moveVideoToRecycleBinProcByIdAndType(Map<String, Object> param);
    
    /**
     * 获取回收站中所有视频
     * @return
     */
    List<DeleteVideo> selectAllRecycleBinVideo();
    
    /**
     * 根据商户id获取回收站中的视频
     * @param businessId
     * @return
     */
    List<DeleteVideo> selectRecycleBinVideoByBusinessId(int businessId);
    
    /**
     * 将指定的视频还原
     * @param videoId
     */
    void recoverVideoFromRecycleBinProcById(int videoId);
    
    /**
     * 将指定的视频从回收站删除
     * @param videoId
     * @return
     */
    int deleteVideoFromRecycleBinById(int videoId);
    
    /**
     * 查询回收站中指定Id的视频信息
     * @param videoId
     * @return
     */
    DeleteVideo selectDeleteVideoById(int videoId);
}
