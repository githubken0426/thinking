package cn.thinking.web.service.user;

import cn.thinking.web.entity.User;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface IUserService {
    /**
     * 登录
     * @param account
     * @return
     */
    User selectByAccount(String account);

    int insert(User record);

    User selectByPrimaryKey(String id);

    int deleteBatch(String[] ids);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser(ModelMap map);

    int selectAccountExists(String account, String id);

}
