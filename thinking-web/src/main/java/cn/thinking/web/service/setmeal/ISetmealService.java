package cn.thinking.web.service.setmeal;

import cn.thinking.web.entity.Setmeal;

import java.util.List;

public interface ISetmealService {
    List<Setmeal> selectDataOfAll(String category, String weight, String deliverys);
    List<Setmeal> selectParentSetmeal();
    Setmeal selectByPrimaryKey(String id);
}
