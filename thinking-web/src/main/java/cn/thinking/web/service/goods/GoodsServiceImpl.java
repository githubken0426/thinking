package cn.thinking.web.service.goods;

import cn.thinking.web.entity.Goods;
import cn.thinking.web.mapper.GoodsMapper;
import cn.thinking.web.mapper.SetmealDetailMapper;
import cn.thinking.web.mapper.SetmealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "goodsService")
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsDao;
    @Autowired
    private SetmealMapper setmealDao;
    @Autowired
    private SetmealDetailMapper setmealDetailDao;

    @Override
    public List<Goods> queryDataOfAll(Map<String, Object> map) {
        return goodsDao.queryDataOfAll(map);
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsDao.selectByPrimaryKey(id);
    }

    @Override
    public int insertGoods(Goods record, String[] mealIds) {
        goodsDao.insert(record);
        List<Integer> setmealIds = setmealDao.selectIdsByParent(mealIds);
        return setmealDetailDao.insertBatch(record.getId(), setmealIds);
    }


    @Override
    public int updateGoods(Goods record, String[] mealIds) {
        goodsDao.updateByPrimaryKey(record);
        setmealDetailDao.deleteByGoodsId(record.getId());
        List<Integer> setmealIds = setmealDao.selectIdsByParent(mealIds);
        return setmealDetailDao.insertBatch(record.getId(), setmealIds);
    }

    @Override
    public int downGoodsById(String id) {
        return goodsDao.downGoodsById(id);
    }

    @Override
    public int pushGoodsById(String id) {
        return goodsDao.pushGoodsById(id);
    }
}
