package com.shengruitn.controller;

import com.shengruitn.dataobject.ProductCategory;
import com.shengruitn.exception.SellException;
import com.shengruitn.form.CategoryForm;
import com.shengruitn.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author tangah
 * @Title: CategoryController
 * @ProjectName Sell
 * @Description: 类目
 * @date 2018/7/11  14:46
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
    * @Description: 
    * @param         * @param map 
    * @return       org.springframework.web.servlet.ModelAndView 
    * @author       tangah
    * @date         2018/7/11 15:33 
    */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map){

        List<ProductCategory> categoryList;
        try {
            categoryList = categoryService.findAll();
            map.put("categoryList", categoryList);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
        }

        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map){
        try {
            ProductCategory category;
            if ( null == categoryId ){
                category = new ProductCategory();
            }else {
                category = categoryService.findOne(categoryId);
            }
            map.put("category", category);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("retUrl", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        map.put("retUrl", "/sell/seller/category/list");
        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map ){

        map.put("retUrl", "/sell/seller/category/list");

        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            log.error("{}", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }

        try{
            //TODO 表单传输日期格式有问题
            //ProductCategory category = new ProductCategory();
            ProductCategory category = categoryService.findOne(categoryForm.getCategoryId());
            categoryForm.setCreateTime(category.getCreateTime());
            categoryForm.setUpdateTime(category.getUpdateTime());
            BeanUtils.copyProperties(categoryForm, category);

            categoryService.save(category);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }

        return new ModelAndView("common/success", map);
    }
}
