package cn.thinking.web.service.goods;

import cn.thinking.web.entity.Goods;

import java.util.List;
import java.util.Map;

public interface IGoodsService {
    List<Goods> queryDataOfAll(Map<String, Object> map);

    Goods selectByPrimaryKey(Integer id);

    int insertGoods(Goods record, String[] mealIds);

    int updateGoods(Goods record, String[] mealIds);

    int downGoodsById(String id);

    int pushGoodsById(String id);
}
