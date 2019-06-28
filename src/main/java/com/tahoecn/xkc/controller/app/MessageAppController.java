package com.tahoecn.xkc.controller.app;


import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.ResponseMessage;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
public class MessageAppController extends TahoeBaseController {

    @Autowired
    private ISystemMessageService iSystemMessageService;

    @ResponseBody
    @ApiOperation(value = "未读消息数接口", notes = "未读消息数接口")
    @RequestMapping(value = "/mMessageUnreadCount_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage mMessageUnreadCount_Select(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());
        Map paramMap = (HashMap)jsonParam.get("_param");
        String jobCode = (String)paramMap.get("JobCode");
        String userId = (String)paramMap.get("UserID");
        String typeCode = (String)paramMap.get("TypeCode");
        String projectId = (String)paramMap.get("ProjectID");

        List unreadCountList = iSystemMessageService.UnreadCountListByMessageType_Select(projectId,userId);

        return ResponseMessage.ok(unreadCountList);
    }

}
