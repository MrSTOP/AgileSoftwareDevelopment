package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.Video;

import java.util.List;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface IVideoDAO {
    
    /**
     * 插入视频相关信息
     * @param video
     * @return
     */
    int insertNewVideo(Video video);
    
    /**
     * 查询指定用户上传的视频数据
     * @param businessId
     * @return
     */
    List<Video> selectVideoInfoByBusinessId(int businessId);
}
