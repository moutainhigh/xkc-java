/**
 * 
 */
package com.tahoecn.xkc.common.ucapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tahoecn.http.HttpUtil;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.security.SecureUtil;

/**
 * UC api调用 接口文档地址 http://git.tahoecn.com/pmo/project/wikis/Home/泰禾公共服务接入/UC对接文档
 * 
 * @ClassName UcApiUtils
 * @author wang_sd
 * @date 2019年4月3日
 */
@Component("ucApiUtils")
public class UcApiUtils {
	private static final Log log = LogFactory.get();

	private static String baseUrl;

	private static String sysId;

	private static String privKey;

	private static String bindHost;

	@Value("${uc_api_url}")
	public void setBaseUrl(String baseUrl) {
		UcApiUtils.baseUrl = baseUrl;
	}

	@Value("${uc_sysId}")
	public void setSysId(String sysId) {
		UcApiUtils.sysId = sysId;
	}

	@Value("${uc_priv_key}")
	public void setPrivKey(String privKey) {
		UcApiUtils.privKey = privKey;
	}

	/**
	 * 获取用户权限接口
	 */
	public Integer getSyspriv() {
		String url = String.format(setUrl("/v1/userStandardRole/list") + "&userName=%s", ThreadLocalUtils.getUserName());
		log.info("request url = {}", url);
		String result = HttpUtil.httpGet(url);
		log.info("userinfo result = {}", result);
		//在线开盘管理员(授权)
		if (result.indexOf("在线开盘管理员(授权)") > 0){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * 获取初始化请求路径
	 * 
	 * @param api
	 * @return
	 */
	public static String setUrl(String api) {
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String token = SecureUtil.md5(timestamp + privKey);
		return String.format(baseUrl + api + "?sysId=%s&timestamp=%s&token=%s", sysId, timestamp, token);
	}
	public static void main(String[] args) {
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String token = SecureUtil.md5(timestamp + "luanqibazaod_zhend_buzhid");
		System.out.println(String.format(String.format("http://ucapi.tahoecndemo.com" + "/v1/syspriv/list" + "?sysId=%s&timestamp=%s&token=%s", "ZXKAIPAN", timestamp, token) + "&userName=%s", "wangjianhua1")); 
		//System.out.println(String.format(String.format("http://ucapi.tahoecndemo.com" + "/v1/syspriv/list" + "?sysId=%s&timestamp=%s&token=%s", "ZXKAIPAN", timestamp, token)) + "&userName=%s", "wangjianhua1"); 
	}
}
