package cn.thinking.web.service.user;

import cn.thinking.web.entity.User;
import cn.thinking.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service(value="userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userDao;

    @Override
    public User selectByAccount(String account) {
        return userDao.selectByAccount(account);
    }

    @Override
    public int insert(User record) {
        return userDao.insert(record);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return userDao.deleteBatch(ids);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

    @Override
    public List<User> selectAllUser(ModelMap map) {
        return userDao.selectAllUser(map);
    }

    @Override
    public int selectAccountExists(String account, String id) {
        return userDao.selectAccountExists(account,id);
    }
}
