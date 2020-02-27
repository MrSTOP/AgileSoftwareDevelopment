package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.Video;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过时间查找视频记录
     * @param parameter
     * @return
     */
    List<Video> selectVideoInfoByDate(Map<String, Object> parameter);

    /**
     * 通过时间名字查找视频
     * @param parameter
     * @return
     */
    List<Video> selectVideoInfoByDateAndId(Map<String, Object> parameter);

    /**
     *查找所有视频
     * @return
     */
    List<Video> selectVideo();
    /**
     * 将视频移到回收站
     * @param param
     */
    void moveVideoToRecycleBinProcByIdAndType(Map<String, Object> param);
}
