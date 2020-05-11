package cn.people.dao.provider;

import cn.people.domain.vo.DepVO;

/**
 * @author : FENGZHI
 * create at:  2020/5/11  下午10:04
 * @description:
 */
public class DepProvider {

    public String updateDep(DepVO dep){
        StringBuffer sql = null;
        if (dep.getLevel() != null){
            sql = new StringBuffer("update department d,user_info u set ");
            sql.append("d.level="+"'"+dep.getLevel()+"'"+"where id="+"'"+dep.getId()+"'");
            sql.append("set u.dep = " +"'" +dep.getLevel()+"'"+"where dep in (select id from where dep=did)");

        }
        return sql.toString();

    }
}
