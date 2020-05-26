package cn.people.service;

import cn.people.domain.Info;
import cn.people.domain.vo.InfoVO;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/23  下午2:46
 * @description:
 */
public interface InfoService {
    /**
     * 新增信息
     * @param info
     */
    void increaseInfo(InfoVO info);

    /**
     * 删除信息
     * @param id
     */
    void deleteInfo(String id);

    /**
     * 更新信息
     * @param info
     */
    void updateInfo(InfoVO info);

    /**
     * 查询信息概要
     * @return
     */
    List<InfoVO> selectRoughlyInfo();

    /**
     * 查询信息内容
     * @return
     * @param id
     */
    Info selectInfo(String id);

    /**
     * 查询用户未读消息
     * @return
     * @param uid
     */
    List<InfoVO> selectUnreadInfo(String uid);

    /**
     * 获取信息情况
     * @return
     */
    List<Info> selectAllInfo();

    /**
     * 用户获取信息详情
     * @param uid
     * @param iid
     * @return
     */
    Info userLookOverInfo(String uid, String iid);

    /**
     * 推送新消息
     * @return
     */
    void newInfoPushUser();

}
