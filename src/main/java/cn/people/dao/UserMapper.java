package cn.people.dao;

import cn.people.dao.provider.UserProvider;
import cn.people.domain.Dep;
import cn.people.domain.UserInfo;
import cn.people.domain.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午4:28
 * @description:
 */
public interface UserMapper {
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Select("select * from user_info where username = #{username}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "authorities",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.dao.RoleMapper.findRoleByUserId") ),
            @Result(property = "dep",column = "dep",javaType = Dep.class,one = @One(select = "cn.people.dao.DepMapper.findDepById"))
    })
    UserInfo findUserByUsername(String username);

    /**
     * 插入用户
     * @param userVO
     */
    @Insert("insert into user_info(id,username,password,email,mobile) values(#{id},#{username},#{password},#{email},#{mobile})")
    void save(UserVO userVO);

    @Select("select * from user_info where id=#{did} ")
    List<UserInfo> findUserById(String did);

    @SelectProvider(type = UserProvider.class,method = "selectAllUser")
    List<UserInfo> findUser();
}
