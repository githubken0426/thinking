package cn.thinking.web.mapper;


import cn.thinking.web.entity.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    List<Goods> queryDataOfAll(Map<String, Object> map);

    Goods selectByPrimaryKey(Integer id);

    int insert(Goods record);

    int deleteBatch(String[] ids);

    int updateByPrimaryKey(Goods record);

    int downGoodsById(String id);
    int pushGoodsById(String id);
}
