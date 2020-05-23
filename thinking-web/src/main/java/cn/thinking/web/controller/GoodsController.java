package cn.thinking.web.controller;

import cn.thinking.commons.CommonUtil;
import cn.thinking.web.GlobalProperties;
import cn.thinking.web.entity.Goods;
import cn.thinking.web.entity.Setmeal;
import cn.thinking.web.entity.SetmealDetail;
import cn.thinking.web.search.GoodsSearch;
import cn.thinking.web.service.goods.IGoodsService;
import cn.thinking.web.service.setmeal.ISetmealDetailService;
import cn.thinking.web.service.setmeal.ISetmealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    private GlobalProperties global;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISetmealService setmealService;
    @Autowired
    private ISetmealDetailService setmealDetailService;

    /**
     * 查询所有数据
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/list")
    public String list(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding,
                       Map<String, Object> map) {
        map.put("skuCode", goodsSearch.getSkuCode());
        map.put("setmealId", goodsSearch.getSetmealId());
        Integer currentPage = goodsSearch.getPno();// 当前页
        PageHelper.startPage(currentPage, global.getPageSize());
        List<Goods> goodsList = goodsService.queryDataOfAll(map);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);

        List<Setmeal> setmeals = setmealService.selectParentSetmeal();
        map.put("setmeals", setmeals);
        map.put("pageInfo", pageInfo);
        return "goods/goods-list";
    }

    // 商品详情
    @GetMapping(value = "/detail")
    public String pageDetail(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding,
                             @RequestParam(value = "goodsId") String id, ModelMap modelMap) {
        List<Setmeal> setmeals = setmealService.selectParentSetmeal();
        Goods goods = goodsService.selectByPrimaryKey(Integer.valueOf(id));
        List<SetmealDetail> setmealDetail=setmealDetailService.selectByReferenceId(Integer.valueOf(id),null);
        setmeals.forEach(setmeal -> {
            for (SetmealDetail detail : setmealDetail) {
                if (detail.getSetmealId() != setmeal.getId())
                    continue;
                setmeal.setIsSelected(true);
                break;
            }
        });
        modelMap.addAttribute("setmeals", setmeals);
        modelMap.addAttribute("obj", goods);
        return "goods/goods-detail";
    }

    @GetMapping(value = "/page/add")
    public String pageAdd(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding,
                          @RequestParam(value = "id", required = false) String id,
                          ModelMap modelMap) {
        List<Setmeal> setmeals = setmealService.selectParentSetmeal();
        modelMap.addAttribute("setmeals", setmeals);
        return "goods/goods-add";
    }

    // 新增商品
    @PostMapping(value = "/add")
    public String add(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding,
                      @Valid Goods goods, HttpServletRequest request,
                      ModelMap modelMap, RedirectAttributes attributes) {
        String pictures[] = request.getParameterValues("pictures");
        goods.setPictures(CommonUtil.arrayToString(pictures));
        goods.setSkuCode("HSH" + System.currentTimeMillis());
        String mealId[] = request.getParameterValues("mealId");
        attributes.addFlashAttribute("PROMPT_RESULT", global.getSuccessAdd());
        int result = goodsService.insertGoods(goods, mealId);
        if (result == 0)
            attributes.addFlashAttribute("PROMPT_RESULT", global.getErrorAdd());
        attributes.addFlashAttribute("goodsSearch", goodsSearch);
        return "redirect:/goods/list";
    }

    @GetMapping(value = "/page/update")
    public String pageUpdate(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding,
                             @RequestParam(value = "goodsId", required = true) String id,
                             ModelMap modelMap) {
        List<Setmeal> setmeals = setmealService.selectParentSetmeal();
        Goods goods = goodsService.selectByPrimaryKey(Integer.valueOf(id));
        List<SetmealDetail> setmealDetail=setmealDetailService.selectByReferenceId(Integer.valueOf(id),null);
        setmeals.forEach(setmeal -> {
            for (SetmealDetail detail : setmealDetail) {
                if (detail.getSetmealId() != setmeal.getId())
                    continue;
                setmeal.setIsSelected(true);
                break;
            }
        });
        modelMap.addAttribute("setmeals", setmeals);
        modelMap.addAttribute("obj", goods);
        return "goods/goods-update";
    }

    /**
     * 更新
     *
     * @param goods
     * @param attributes
     * @return
     */
    @PostMapping(value = "/update")
    public String update(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding, HttpServletRequest request,
                         @Valid Goods goods, RedirectAttributes attributes) {
        String[] pictures = request.getParameterValues("pictures");
        goods.setPictures(CommonUtil.arrayToString(pictures));
        String mealId[] = request.getParameterValues("mealId");
        int result = goodsService.updateGoods(goods, mealId);
        attributes.addFlashAttribute("PROMPT_RESULT", global.getSuccessUpdate());
        if (result == 0)
            attributes.addFlashAttribute("PROMPT_RESULT", global.getErrorUpdate());
        attributes.addFlashAttribute("goodsSearch", goodsSearch);
        return "redirect:/goods/list";
    }

    /**
     * 下架商品
     *
     * @return
     */
    @GetMapping(value = "/down")
    public String down(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding,
                       @RequestParam(value = "goodsId", required = false) String goodsId,
                       HttpServletRequest request, RedirectAttributes attributes) {
        attributes.addFlashAttribute("PROMPT_RESULT", "菜品下架成功！");
        int result = goodsService.downGoodsById(goodsId);
        if (result == 0)
            attributes.addFlashAttribute("PROMPT_RESULT", "菜品下架失败！");
        attributes.addFlashAttribute("goodsSearch", goodsSearch);
        return "redirect:/goods/list";
    }

    // 上架商品
    @GetMapping(value = "/push")
    public String push(@ModelAttribute GoodsSearch goodsSearch, BindingResult binding,
                       @RequestParam(value = "goodsId", required = false) String goodsId,
                       HttpServletRequest request, RedirectAttributes attributes) {
        attributes.addFlashAttribute("PROMPT_RESULT", "菜品上架成功！");
        int result = goodsService.pushGoodsById(goodsId);
        if (result == 0)
            attributes.addFlashAttribute("PROMPT_RESULT", "菜品上架失败！");
        attributes.addFlashAttribute("goodsSearch", goodsSearch);
        return "redirect:/goods/list";
    }
}
