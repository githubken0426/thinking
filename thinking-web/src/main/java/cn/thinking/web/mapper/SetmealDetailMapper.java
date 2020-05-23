package cn.thinking.web.mapper;

import cn.thinking.web.entity.SetmealDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDetailMapper {
    List<SetmealDetail> selectByReferenceId(@Param("goodsId") Integer goodsId, @Param("setmealId") Integer setmealId);

    int insert(SetmealDetail detail);

    int insertBatch(@Param("goodsId") int goodsId, @Param("setmealIds") List<Integer> setmealIds);

    int deleteByGoodsId(int goodsId);
}
