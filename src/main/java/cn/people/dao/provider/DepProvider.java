package cn.people.dao.provider;

import cn.people.domain.UserInfo;
import cn.people.domain.vo.DepVO;
import cn.people.utils.aspect.FieldScopeAspect;

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

    public String findALL(UserInfo user){

        Object params = user.getParams().get(FieldScopeAspect.FIELD_SCOPE);
        StringBuffer sql = null;

        if (params == null){
             sql = new StringBuffer("select * from department ");
        }else {
            String alias = (String) user.getParams().get(FieldScopeAspect.ALIAS);
            sql = new StringBuffer("select");
            sql.append( ((String)params) + String.format("from department %s",alias) );
        }
        return sql.toString();
    }
}
