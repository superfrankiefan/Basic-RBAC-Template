package com.sff.rbacdemo.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.entity.Dict;
import com.sff.rbacdemo.system.entity.DictDetail;
import com.sff.rbacdemo.system.mapper.DictDetailMapper;
import com.sff.rbacdemo.system.mapper.DictMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Frankie Fan
 * @date 2022-04-14 11:20
 * 数据字典接口
 */

@Slf4j
@RestController
@RequestMapping("/system/dict")
public class DictMgmt extends BaseController {

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @PostMapping("addOrUpdateDict")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateDict(@RequestBody Dict dict){
        if(dict != null) {
            if(dict.getDictId() != null) {
                Dict dictIns = this.dictMapper.selectById(dict.getDictId());
                if (dictIns != null) {
                    this.dictMapper.updateById(dict);
                    return APIResponse.OK("Update Dict", null);
                }
            }
            this.dictMapper.insert(dict);
            return APIResponse.OK("Add Dict", null);
        }
        return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"Dict Data Invalid",dict);
    }

    @PostMapping("addOrUpdateDictDetail")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateDictDetail(@RequestBody DictDetail dictDetail){
        if(dictDetail != null) {
            if(dictDetail.getDetailId() != null) {
                DictDetail dict = this.dictDetailMapper.selectById(dictDetail.getDetailId());
                if (dict != null) {
                    this.dictDetailMapper.updateById(dictDetail);
                    return APIResponse.OK("Update Dict Detail", null);
                }
            }
            this.dictDetailMapper.insert(dictDetail);
            return APIResponse.OK("Add Dict Detail", null);
        }
        return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"Dict Detail Data Invalid",dictDetail);
    }

    @GetMapping("getDictList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getDictList(@RequestParam(required = false, value = "dictCode") String dictCode,
                                   @RequestParam(required = false, value = "description") String description) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("DICT_NAME");
        if (dictCode != null) {
            queryWrapper.like("DICT_CODE", dictCode);
        }
        if (description != null) {
            queryWrapper.like("DESCRIPTION", description);
        }
        return APIResponse.OK("dicts", this.dictMapper.selectList(queryWrapper));
    }

    @GetMapping("getDictDetailList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getDictDetailList(@RequestParam(required = false, value = "dictCode") String dictCode) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("DETAIL_VALUE");
        if (dictCode != null) {
            queryWrapper.like("DICT_CODE", dictCode);
        }
        return APIResponse.OK("dict details", this.dictDetailMapper.selectList(queryWrapper));
    }

    @DeleteMapping("deleteDicts")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteDicts(@RequestBody Map<String, String> dictIds) {
        this.dictMapper.deleteById(dictIds.get("dictIds"));
        return APIResponse.OK("Delete Dicts", null);
    }

    @DeleteMapping("deleteDictDetails")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteDictDetails(@RequestBody Map<String, String> dictDetailIds) {
        this.dictDetailMapper.deleteById(dictDetailIds.get("dictDetailIds"));
        return APIResponse.OK("Delete Dict Details", null);
    }

}
