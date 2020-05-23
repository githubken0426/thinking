package cn.thinking.web.service.setmeal;

import cn.thinking.web.entity.SetmealDetail;
import cn.thinking.web.mapper.SetmealDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("setmealDetailService")
public class SetmealDetailServiceImpl implements  ISetmealDetailService{
    @Autowired
    private SetmealDetailMapper setmealDetailDao;
    @Override
    public List<SetmealDetail> selectByReferenceId(Integer goodsId, Integer setmealId) {
        return setmealDetailDao.selectByReferenceId(goodsId,setmealId);
    }

}
