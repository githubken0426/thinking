package cn.thinking.web.service.setmeal;

import cn.thinking.web.entity.SetmealDetail;

import java.util.List;

public interface ISetmealDetailService {
    List<SetmealDetail> selectByReferenceId(Integer goodsId, Integer setmealId);
}
