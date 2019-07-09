package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.SFormsessionMapper;
import com.tahoecn.xkc.model.sys.SFormsession;
import com.tahoecn.xkc.service.sys.ISFormsessionService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@Service
public class SFormsessionServiceImpl extends ServiceImpl<SFormsessionMapper, SFormsession> implements ISFormsessionService {

    /**
     * //查询是否已经存在无效的FormSessionID,不存在则更新为无效状态
     * @param formSessionID
     * @return
     */
    @Override
    public int checkFormSessionID(String formSessionID) {
        QueryWrapper<SFormsession> wrapper=new QueryWrapper<>();
        wrapper.eq("ID",formSessionID).eq("Status",0);
        int count = baseMapper.selectCount(wrapper);
        //count为0 则第一次提交,为1重复提交
        if (count==0){
            SFormsession formsession=new SFormsession();
            formsession.setId(formSessionID);
            formsession.setEditTime(new Date());
            formsession.setStatus(0);
            baseMapper.updateById(formsession);
        }
      return count;

    }
}
