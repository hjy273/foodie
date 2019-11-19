package com.geekerstar.controller;

import com.geekerstar.enums.YesOrNo;
import com.geekerstar.pojo.Carousel;
import com.geekerstar.pojo.Category;
import com.geekerstar.service.CarouselService;
import com.geekerstar.service.CategoryService;
import com.geekerstar.util.JSONResult;
import com.geekerstar.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author geekerstar
 * date: 2019/11/18 21:30
 * description:
 */
@Api(value = "首页",tags = "首页展示的相关接口")
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表",notes = "获取首页轮播图列表",httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel(){
        List<Carousel> result = carouselService.queryAll(YesOrNo.YES.type);
        return JSONResult.ok(result);
    }

    /**
     * 首页分类展示需求
     * 1、第一次刷新主页查询大分类，渲染展示到首页
     * 2、如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "获取商品分类（一级分类）",notes = "获取商品分类（一级分类）",httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats(){
        List<Category> result = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(result);
    }


    @ApiOperation(value = "获取商品子分类",notes = "获取商品子分类",httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONResult sunCat(
            @ApiParam(name = "rootCatId",value = "一级分类ID",required = true)
            @PathVariable Integer rootCatId){
        if (rootCatId == null) {
            return JSONResult.errorMsg("分类不存在");
        }
        List<CategoryVO> result = categoryService.getSubCatList(rootCatId);
        return JSONResult.ok(result);
    }
}
