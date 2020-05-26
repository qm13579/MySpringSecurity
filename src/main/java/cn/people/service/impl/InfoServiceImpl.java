package cn.people.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.people.dao.InfoMapper;
import cn.people.domain.Info;
import cn.people.domain.UserInfo;
import cn.people.domain.vo.InfoVO;
import cn.people.service.InfoService;
import cn.people.utils.common.IdWorker;
import cn.people.utils.common.SecurityUtils;
import cn.people.utils.websocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/5/23  下午3:17
 * @description:
 */
@Service
public class InfoServiceImpl implements InfoService {

    @Resource
    private InfoMapper infoMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private WebSocketService webSocketService;

    @Override
    public void increaseInfo(InfoVO infoVO) {

        Info info = new Info();
        BeanUtil.copyProperties(infoVO,info);
        info.setId(idWorker.nextId()+"");
        info.getUserInfo().setId(SecurityUtils.getCurrentUser().getId());
        info.setStatus(1);
        infoMapper.increaseInfo(info);
        newInfoPushUser();
    }

    @Override
    public void deleteInfo(String id) {
        infoMapper.deleteInfo(id);
    }

    @Override
    public void updateInfo(InfoVO info) {
        infoMapper.updateInfo(info);
    }

    @Override
    public List<InfoVO> selectRoughlyInfo() {
        UserInfo user = SecurityUtils.getCurrentUser();
        List<InfoVO>  infoVOS= infoMapper.selectRoughlyInfo(user.getId());
        return infoVOS;
    }

    @Override
    public Info selectInfo(String id) {
        return infoMapper.selectInfo(id);
    }

    @Override
    public List<InfoVO> selectUnreadInfo(String uid) {
        List<InfoVO> info= infoMapper.selectUnreadInfo(uid);
        return info;
    }


    @Override
    public List<Info> selectAllInfo() {
        return infoMapper.selectAllInfo();
    }

    @Override
    public Info userLookOverInfo(String uid, String iid) {
        infoMapper.increaseLookOverRegister(uid,iid);
        return infoMapper.userLookOverInfo(iid);

    }

    @Override
    public void newInfoPushUser() {
        webSocketService.sendInfo("info");
    }
}
