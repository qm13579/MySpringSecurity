package cn.people.service;

import cn.people.domain.Dep;
import cn.people.domain.vo.DepVO;

import java.util.List;

public interface DepService {
    /**
     * 查找全部
     * @return
     */
    List<DepVO> findALL();

    /**
     * 删除部门
     */
    Boolean deleteDep(String did);

    /**
     * 更新部门
     */
    void updateDep(DepVO dep);

    /**
     * 增加部门
     * @param dep
     */
    Boolean increase(DepVO dep);
}
