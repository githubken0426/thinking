package cn.thinking.web.mapper;

import cn.thinking.web.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * 登录
     * @param account
     * @return
     */
    User selectByAccount(String account);

    User selectByPrimaryKey(String id);

    List<User> selectAllUser(ModelMap map);

    int insert(User record);

    int deleteBatch(String[] ids);

    int updateByPrimaryKey(User record);

    int selectAccountExists(@Param("account") String account, @Param("id") String id);

}
