package cn.people.service;

import cn.people.domain.UserInfo;
import cn.people.domain.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午3:38
 * @description: 用户接口
 */
public interface UserService {

    /**
     * 新增用户
     * @param userVO
     */
    void save(@Valid UserVO userVO);

    /**
     * 更新用户
     * @param userVO
     */
    void update(UserVO userVO);

    /**
     * 根据id删除用户
     * @param uid
     */
    void deleteUser(String uid);

    /**
     * 查找所有用户
     * @return
     */
    List<UserInfo> findAllUser();

    /**
     * 根据id查找用户
     * @param uid
     * @return
     */
    UserInfo findUserById(String uid);

    /**
     * 下载用户文件
     */
    void downloadUserFile(HttpServletResponse response);

    /**
     * 文件预览
     * @param response
     */
    void preview(HttpServletResponse response);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(UserVO user);

    /**
     * 用户停用
     * @param uid
     */
    void stopUser(String uid);

    /**
     * 照片上传
     * @param file
     */
    void fileLoad(MultipartFile file) throws IOException;

    /**
     * 批量增加用户
     * @param file
     */
    List<Map<String,String>> LoadUserFile(MultipartFile file);
}
