package cn.people.dao.provider;

/**
 * 用户提供类
 */
public class UserProvider {

    public String selectAllUser(){
        return "select * from user_info";
    }

}
