package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.common.MessageConst;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiniuUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author ：seanyang
 * @date ：Created in 2019/7/18
 * @description ：套餐控制器
 * @version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {


    @Reference
    private SetmealService setmealService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        try{
            // 原名
            String fileName = imgFile.getOriginalFilename();
            log.debug("#######getOriginalFilename:{}",fileName);
            // UUID+"_"+fileName
            String saveUploadName = UUID.randomUUID().toString().replace("-","")+"_"+fileName;
            // 使用七牛上传
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),saveUploadName);
            // 下发到客户端，http://xxxx/saveUploadName
            String filePath = QiniuUtils.qiniu_img_url_pre+saveUploadName;
            log.debug("#######filePath:{}",filePath);

            // 放入redis

            return new Result(true, MessageConst.PIC_UPLOAD_SUCCESS,filePath);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.PIC_UPLOAD_FAIL);
        }
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkGroupIds){
        System.out.println("checkGroupIds.length::::::"+checkGroupIds.length);
        log.debug(">>>>>>>>> Ids:{}",checkGroupIds);
        try{
            // 把表单提交的图片路径中的域名地址去掉
            // http://puph4ecls.bkt.clouddn.com/xxxxxx.jpg
            log.debug("####img url:{}",setmeal.getImg());
            String saveName = setmeal.getImg().replace(QiniuUtils.qiniu_img_url_pre,"");
            setmeal.setImg(saveName);
            setmealService.add(setmeal,checkGroupIds);
            log.debug(">>>>>>>>img url:{} >>>>>>>>>>>>setmeal:{}" ,setmeal.getImg(),setmeal);
            return new Result(true,MessageConst.ADD_SETMEAL_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }


}
