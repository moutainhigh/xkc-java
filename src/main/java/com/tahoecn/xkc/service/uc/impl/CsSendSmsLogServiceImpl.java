package com.tahoecn.xkc.service.uc.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.uc.CsSendSmsLogMapper;
import com.tahoecn.xkc.mapper.uc.CsSendtoallMapper;
import com.tahoecn.xkc.model.CsSendSmsLog;
import com.tahoecn.xkc.service.uc.CsSendSmsLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 常见问题表 服务实现类
 * </p>
 *
 * @author zghw
 * @since 2018-11-18
 */
@Transactional
@Service
public class CsSendSmsLogServiceImpl extends ServiceImpl<CsSendSmsLogMapper, CsSendSmsLog> implements CsSendSmsLogService {

	
	private static final String CONTENT_PREFIX = "【泰禾集团】";
	private  Log _log = LogFactory.get();
	@Value("${sms_uri}")
	private  String uri;
	@Value("${sms_account}")
	private  String account;
	@Value("${sms_pwd}")
	private  String pwd;
	@Value("${sms_needstatus}")
	private  String needstatus;
	@Value("${sms_product}")
	private  String product;
	@Value("${sms_extno}")
	private  String extno;
	@Value("${sms_switch}")
	private  String smsSwitch;
	@Autowired
	private CsSendtoallMapper csSendToAllMapper;
	/**
	 * @author liuq
	 * @param mobiles 139XXXXXXXX,150XXXXXXXX
	 * @param content 短信信息
	 * @param userName 人员姓名
	 */
	@Override
	public void sendSms(String mobiles, String content , String userName) {

		if (!"on".equals(smsSwitch)) {
        	_log.error("sms is off");
        	return;
        }
		if (!StringUtils.startsWith(content, CONTENT_PREFIX)) {
            content = CONTENT_PREFIX + content;
        }
        if (StringUtils.isBlank(mobiles)) {
            _log.error("mobiles is null");
            return;
        }
        
        final String contentnew = content;
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                //csSendSmsLogService
                try {
                    String rt = "";
                    _log.info(uri+account+pwd+
                            mobiles+ contentnew+ needstatus+ product+
                            extno);
                    if (StringUtils.contains(mobiles, ",")) {
                        rt = batchSend(uri, account, pwd,
                                mobiles, contentnew, needstatus, product,
                                extno,userName);
                    } else {
                        rt = send(uri, account, pwd,
                                mobiles, contentnew, needstatus, product,
                                extno,userName);
                    }
                    System.out.println(rt);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    _log.error("mobiles:"+mobiles+",内容contentnew:"+contentnew);
//                    csSendSmsLog.setSendResult("发送异常！");
//                    csSendSmsLogService.saveCsSendSmsLog(csSendSmsLog);
                }

            }
        });
    }
    public String send(String uri, String account, String pswd, String mobiles, String content,
    		String needstatus, String product, String extno, String userName) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("account", account);
        params.put("pswd", pswd);
        params.put("mobile", mobiles);
        params.put("needstatus", String.valueOf(needstatus));
        params.put("msg", URLEncoder.encode(content, "UTF-8"));
        //params.put("product", product);
        params.put("extno", extno);
        //String result = HttpClient.httpPost(uri+"HttpSendSM", params);
        String result = HttpUtil.post(uri+"HttpSendSM", params);
        return result;
    }
    
    public String batchSend(String uri, String account, String pswd, String mobiles, String content,
    		String needstatus, String product, String extno, String userName) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("account", account);
        params.put("pswd", pswd);
        params.put("mobile", mobiles);
        params.put("needstatus", String.valueOf(needstatus));
        params.put("msg", URLEncoder.encode(content, "UTF-8"));
        //params.put("product", product);
        params.put("extno", extno);
        //String result = HttpClient.httpPost(uri+"HttpSendSM", params);
        String result = HttpUtil.post(uri+"HttpBatchSendSM", params);
        CsSendSmsLog csSendSmsLog = new CsSendSmsLog();
    	csSendSmsLog.setUserName(userName);
    	csSendSmsLog.setCreateDate(new Date());
    	csSendSmsLog.setSendMobiles(mobiles);
    	csSendSmsLog.setContentDesc(content);
    	csSendSmsLog.setSendResult(result);
    	this.baseMapper.insert(csSendSmsLog);
        return result;
    }
	@Override
	public void sendToAll(String msg) {
		List<HashMap<String,Object>> list = csSendToAllMapper.getSendToAll();
		int i =0 ;
		int b=0;
		String mobiles = "";
		for(HashMap<String,Object> map : list){
			
			mobiles = mobiles + map.get("telephone").toString() + ",";
			i++;b++;
			if(i == 1000 || b==list.size()){
				mobiles = mobiles.substring(0,mobiles.length() - 1);
				System.out.println(mobiles);
				sendSms(mobiles,msg,"期初发短信");
				i=0;
				mobiles = "";
			}
			//String mobiles, String content , String userName
			
		}
	}

}
