package cn.people.service.impl;

import cn.people.dao.InfoMapper;
import cn.people.domain.Info;
import cn.people.service.InfoService;
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

    @Override
    public void increaseInfo(Info info) {

    }

    @Override
    public void deleteInfo(String id) {

    }

    @Override
    public void updateInfo(Info info) {

    }

    @Override
    public List<Info> selectRoughlyInfo() {
        return null;
    }

    @Override
    public Info selectInfo(String id) {
        return null;
    }

    @Override
    public long countInfo(String uid) {
        return 0;
    }
}
