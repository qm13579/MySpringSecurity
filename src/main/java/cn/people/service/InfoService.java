package cn.people.service;

import cn.people.domain.Info;

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
    void increaseInfo(Info info);

    /**
     * 删除信息
     * @param id
     */
    void deleteInfo(String id);

    /**
     * 更新信息
     * @param info
     */
    void updateInfo(Info info);

    /**
     * 查询信息概要
     * @return
     */
    List<Info> selectRoughlyInfo();

    /**
     * 查询信息内容
     * @return
     * @param id
     */
    Info selectInfo(String id);

    /**
     * 查询未读消息
     * @return
     * @param uid
     */
    long countInfo(String uid);

}
