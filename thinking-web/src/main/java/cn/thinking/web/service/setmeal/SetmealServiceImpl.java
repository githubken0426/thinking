package cn.thinking.web.service.setmeal;

import cn.thinking.web.entity.Setmeal;
import cn.thinking.web.mapper.SetmealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("setmealService")
public class SetmealServiceImpl implements ISetmealService {
    @Autowired
    private SetmealMapper setmealDao;

    @Override
    public List<Setmeal> selectDataOfAll(String category, String weight, String deliverys) {
        return setmealDao.selectDataOfAll(category, weight, deliverys);
    }

    @Override
    public List<Setmeal> selectParentSetmeal() {
        return setmealDao.selectParentSetmeal();
    }

    @Override
    public Setmeal selectByPrimaryKey(String id) {
        return setmealDao.selectByPrimaryKey(id);
    }

}
