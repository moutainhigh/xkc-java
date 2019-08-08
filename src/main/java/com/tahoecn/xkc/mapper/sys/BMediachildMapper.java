package com.tahoecn.xkc.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.BMediachild;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-08-06
 */
public interface BMediachildMapper extends BaseMapper<BMediachild> {

    void MediaChildSave(BMediachild bMediachild);

    void MediaChildUpdate(BMediachild bMediachild);
}
