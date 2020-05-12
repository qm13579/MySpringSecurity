package cn.people.service.impl;

import cn.people.dao.DepMapper;
import cn.people.domain.Dep;
import cn.people.domain.vo.DepVO;
import cn.people.service.DepService;
import cn.people.utils.aspect.annotation.DataScope;
import cn.people.utils.common.DepUtils;
import cn.people.utils.common.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 部门实现类
 */
@Service
public class DepServiceImpl implements DepService {

    @Autowired
    private IdWorker idWorker;

    @Resource
    private DepMapper depMapper;


    @Override
    @DataScope(deptAlias = "d")
    public List<DepVO> findALL() {

        List<DepVO> list = depMapper.findALL();
        List<DepVO> deps = DepUtils.parseDepTree(list);
        return deps;
    }

    /**
     * 删除部门，先判断该部门下是否有人员
     * @param did
     */
    @Override
    public Boolean deleteDep(String did) {
        Dep dep = depMapper.findDepById(did);
        if (dep.getUserInfos() != null){
          return false;
        }
        depMapper.deleteDep(did);
        return true;
    }


    @Override
    public void updateDep(DepVO dep) {
        Dep mapperDep = depMapper.findDepById(dep.getId());
        if (Objects.equals(mapperDep.getLevel(),dep.getLevel())){
            depMapper.updateDepName(dep);
        }else {
            depMapper.updateDep(dep);
        }


    }


    @Override
    public Boolean increase(DepVO dep) {

        if (Objects.equals(dep.getLevel(),dep.getUpperLevel())){
            return false;
        }
        dep.setId(idWorker.nextId()+"");
        depMapper.increase(dep);
        return true;
    }
}
