package com.shengruitn.controller;

import com.shengruitn.Enum.ResultEnum;
import com.shengruitn.dataobject.ProductCategory;
import com.shengruitn.dataobject.ProductInfo;
import com.shengruitn.exception.SellException;
import com.shengruitn.form.ProductForm;
import com.shengruitn.service.CategoryService;
import com.shengruitn.service.ProductInfoService;
import com.shengruitn.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
 * @ Author     ：tangah.
 * @ Date       ：Created in 19:33 2018/7/7
 * @ Description：卖家端商品管理
 * @ Modified By：
 * @Version: 1.0$
 */


@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String,Object> map) {
        String returnUrl = "/sell/seller/product/list?page=" + page + "&size=" + size;

        Page<ProductInfo> productInfoPage;
        try {
            PageRequest request = new PageRequest(page-1, size);
            productInfoPage = productInfoService.findAll(request);
        } catch (Exception e) {
            log.error( "【查询商品列表】 失败：【{}】", e.getMessage());
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        map.put( "productInfoPage", productInfoPage );
        map.put( "currentPage", page);
        map.put( "size", size );
        map.put( "retUrl", returnUrl );

        return new ModelAndView("/product/list",map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               @RequestParam("retUrl")String retUrl,
                               Map<String, Object> map){
        try{
            productInfoService.onSale(productId);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/list");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        map.put("retUrl", retUrl);
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               @RequestParam("retUrl")String retUrl,
                               Map<String, Object> map){
        try{
            productInfoService.offSale(productId);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("retUrl", retUrl);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        map.put("retUrl", retUrl);
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      @RequestParam(value = "retUrl", required = false) String retUrl,
                      Map<String, Object> map) {
        // 查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        ProductInfo productInfo;

        if (!StringUtils.isEmpty(productId)){
            productInfo = productInfoService.findOne(productId);
        }else {
            productInfo = new ProductInfo();
            productInfo.setProductId(KeyUtil.genUniqueKey());
        }

        map.put("categoryList", categoryList);
        map.put("productInfo", productInfo);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView sale(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        map.put("retUrl", "/sell/seller/product/list");

        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = productInfoService.findOne(productForm.getProductId());
        if (productInfo == null)
            productInfo = new ProductInfo();

        try{
            BeanUtils.copyProperties(productForm, productInfo);
            productInfoService.save(productInfo);
        }catch (SellException e){
            log.error("err:[{}]", e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error",map);
        }


        return new ModelAndView("common/success", map );
    }
}
