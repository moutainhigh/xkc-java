package com.tahoecn.xkc.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.BMediachild;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-08-06
 */
public interface IBMediachildService extends IService<BMediachild> {

    void MediaChildSave(BMediachild bMediachild);

    void MediaChildUpdate(BMediachild bMediachild);

}
