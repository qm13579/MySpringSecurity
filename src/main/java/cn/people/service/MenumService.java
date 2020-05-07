package cn.people.service;

import cn.people.domain.Menum;

import java.util.List;

/**
 * 菜单接口
 * @author : FENGZHI
 * create at:  2020/5/7  下午9:28
 * @description:
 */
public interface MenumService {
    /**
     * 添加菜单
     * @param menum
     */
    void increaseMenum(Menum menum);

    /**
     * 更新菜单
     * @param menum
     */
    void updateMenum(Menum menum);

    /**
     * 根据ID删除id
     * @param mid
     */
    void deleteMenum(String mid);

    /**
     * 查找所有菜单
     * @return
     */
    List<Menum> findAllMenum();

    /**
     * 根据id批量删除菜单
     * @param menums
     */
    void deleteAllMenum(List<String> menums);

}
