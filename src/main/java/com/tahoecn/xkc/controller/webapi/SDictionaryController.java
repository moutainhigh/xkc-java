package com.tahoecn.xkc.controller.webapi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@RestController
@RequestMapping("/webapi/sDictionary")
public class SDictionaryController extends TahoeBaseController {
    @Autowired
    private ISDictionaryService dictionaryService;

    @ApiOperation(value = "获取所有参数", notes = "获取所有参数")
    @RequestMapping(value = "/SystemAllParams_Select", method = {RequestMethod.POST})
    public Result SystemAllParams_Select(String PID,String AuthCompanyID,String ProductID,String ProjectID,String Media,int Pageindex,int Pagesize){
        //参数获取
        IPage page=new Page(Pageindex,Pagesize);
        if (Media==null){
        QueryWrapper<SDictionary> wrapper=new QueryWrapper<>();
        wrapper.eq("IsDel",0).eq("AuthCompanyID",AuthCompanyID).eq("ProductID",ProductID);
        wrapper.eq("PID",PID);
        wrapper.lt("Levels",3);
        wrapper.orderByAsc("Levels","ListIndex");
        IPage page1 = dictionaryService.page(page, wrapper);
        return Result.ok(page1);
        }
        //媒体小类获取
        else {
            IPage<Map<String,Object>>   page1=dictionaryService.getMediaList(page,PID,ProjectID);
            List<Map<String, Object>> records = page1.getRecords();
            for (Map<String, Object> record : records) {
                record.put("Media","Media");
            }
            return Result.ok(page1);
        }
    }

    @ApiOperation(value = "获取所有参数--树形", notes = "获取所有参数--树形")
    @RequestMapping(value = "/SystemAllParams_Select_Tree", method = {RequestMethod.POST})
    public Result SystemAllParams_Select_Tree(String PID,String ProjectID,String Media){
        List<Map<String,Object>> list=dictionaryService.SystemAllParams_Select_Tree(PID,ProjectID,Media);

        return Result.ok(list);
    }

    @ApiOperation(value = "启用/禁用参数", notes = "启用/禁用参数")
    @RequestMapping(value = "/SystemParamStatus_Update", method = {RequestMethod.POST})
    public Result SystemParamStatus_Update(String ID,int Status,String Media){
        if (Media==null){
            SDictionary dictionary=new SDictionary();
            dictionary.setId(ID);
            dictionary.setStatus(Status);
            boolean b = dictionaryService.updateById(dictionary);
            if (b){
                return Result.okm("成功");
            }
            return Result.errormsg(99,"修改失败");
        }else {
            dictionaryService.updateMediaStatus(ID,Status);
        }
        return null;
    }

    @ApiOperation(value = "新增参数", notes = "新增参数")
    @RequestMapping(value = "/SystemParam_Insert", method = {RequestMethod.POST})
    public Result SystemParam_Insert(@RequestBody SDictionary dictionary){
        //取消媒体大类在参数管理中  注释掉
//        if (Media==null) {
//            SDictionary dictionary=new SDictionary();
//            dictionary.setId(UUID.randomUUID().toString());
//            dictionary.setPid(pid);
//            dictionary.setAuthCompanyID(authCompanyID);
//            dictionary.setDictCode(dictCode);
//            dictionary.setDictName(dictName);
//            dictionary.setLevels(levels);
//            dictionary.setListIndex(listIndex);
//            dictionary.setProductID(productID);
//            dictionary.setRemark(remark);
            String PID = dictionary.getPid();
            SDictionary byId = dictionaryService.getById(PID);
            String fullPath = byId.getFullPath();
            String newFullPath;
            if (StringUtils.isBlank(fullPath)) {
                newFullPath = dictionary.getDictName();
            } else {
                newFullPath = fullPath + "/" + dictionary.getDictName();
            }
            dictionary.setFullPath(newFullPath);
            dictionary.setCreator(ThreadLocalUtils.getUserName());
            dictionary.setCreateTime(new Date());
            dictionary.setIsDel(0);
            dictionary.setStatus(1);
            boolean save = dictionaryService.save(dictionary);
            if (save) {
                return Result.okm("成功");
            }
            return Result.errormsg(99, "新增失败");
//        }else {
//            String id = UUID.randomUUID().toString();
//            Result result=dictionaryService.saveMedia(pid,id,dictName,listIndex,ProjectID);
//            return result;
//        }
    }

    @ApiOperation(value = "编辑修改参数", notes = "编辑修改参数")
    @RequestMapping(value = "/SystemParam_Update", method = {RequestMethod.POST})
    public Result SystemParam_Update(@RequestBody SDictionary dictionary){
        boolean b=dictionaryService.SystemParam_Update(dictionary);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改失败");
    }


    @ApiOperation(value = "删除参数", notes = "删除参数")
    @RequestMapping(value = "/SystemParam_Delete", method = {RequestMethod.POST})
    public Result SystemParam_Delete(String ID){
      //先修改为删除状态 在通过路径判断把带有他路径的子项都改为删除状态
        boolean b=dictionaryService.SystemParam_Delete(ID);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(99,"删除失败");
    }


//    @ApiOperation(value = "PC数据字典", notes = "PC数据字典")
//    @RequestMapping(value = "/SystemDictionaryDetail_Select", method = {RequestMethod.GET})
//    public Result SystemDictionaryDetail_Select(String Code,String UserID,String Model,String JobID,String JobCode,String OrgID,String ProjectID) {
//        HashMap<String,Object> param=new HashMap<>();
//        param.put("Code",Code);
//        param.put("UserID",UserID);
//        param.put("Model",Model);
//        param.put("JobID",JobID);
//        param.put("JobCode",JobCode);
//        param.put("OrgID",OrgID);
//        param.put("ProjectID",ProjectID);
//        Result result=dictionaryService.PCSystemDictionaryDetail(param);
//        return result;
//    }

//    @ApiOperation(value = "媒体类别管理--树形", notes = "媒体类别管理--树形")
//    @RequestMapping(value = "/getMediaLargeList_Tree", method = {RequestMethod.GET})
//    public Result getMediaLargeList_Tree(String PID,String ProjectID){
//        List<Map<String,Object>> list=dictionaryService.getMediaLargeList_Tree(PID,ProjectID);
//
//        return Result.ok(list);
//    }
}
