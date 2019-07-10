package com.tahoecn.xkc.mapper.clue;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.clue.ClueListSelect;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 1 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface ClueListSelectMapper extends BaseMapper<ClueListSelect> {
    int getCustomerCount(@Param("projectId") String projectId, @Param("memberId")String memberId);
}
