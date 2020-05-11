package cn.people.utils.common;

import cn.people.domain.Dep;
import cn.people.domain.vo.DepVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 部门处理工具类
 */
public class DepUtils {
    /**
     *
     * @param deps
     * @return
     */
    public static List<DepVO> parseDepTree(List<DepVO> deps){
        List<DepVO> list = new ArrayList<>();
        //获取第一个节点
        for (DepVO dep :deps) {
            if (dep.getLevel() == "1"){
                list.add(dep);
            }
        }
        //递归获取子节点
        for (DepVO dep :list) {
            dep = recursiveTree(dep,deps);
        }
        return list;
    }

    /**
     *
     * @param parent
     * @param deps
     * @return
     */
    public static DepVO recursiveTree(DepVO parent, List<DepVO> deps){
        for (DepVO dep :deps) {
            if (Objects.equals(dep.getLevel(),parent.getId())){
                dep = recursiveTree(dep, deps);
                parent.getChildren().add(dep);
            }
        }
        return parent;
    }
}
