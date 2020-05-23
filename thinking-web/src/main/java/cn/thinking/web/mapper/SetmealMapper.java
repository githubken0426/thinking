package cn.thinking.web.mapper;

import cn.thinking.web.entity.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealMapper {
    List<Setmeal> selectDataOfAll(@Param("category") String category, @Param("weight") String weight,
                                  @Param("deliverys") String deliverys);

    List<Setmeal> selectParentSetmeal();

    List<Integer> selectIdsByParent(String[] parentId);

    Setmeal selectByPrimaryKey(String id);

}
