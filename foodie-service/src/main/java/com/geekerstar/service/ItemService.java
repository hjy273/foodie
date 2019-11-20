package com.geekerstar.service;

import com.geekerstar.pojo.Items;
import com.geekerstar.pojo.ItemsImg;
import com.geekerstar.pojo.ItemsParam;
import com.geekerstar.pojo.ItemsSpec;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geekerstar
 * date: 2019/11/20 19:20
 * description:
 */
@Service
public interface ItemService {

    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

}