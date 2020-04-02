package com.tahoecn.xkc.controller;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;

@Component
public class TahoeBaseController {

	@Value("${tahoe.application.physicalPath}")
    private String physicalPath;
	
    
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    public String uploadFiles(MultipartFile[] files) {
        File dir;
        StringBuffer paths = new StringBuffer();
        for (MultipartFile file : files) {
            String extensionName = "";
            String originalFilename =  file.getOriginalFilename();
            if(originalFilename!=null&&!"".equals(originalFilename)){
                int index = originalFilename.lastIndexOf(".");
                if(index > 0){
                    extensionName = originalFilename.substring(index, originalFilename.length());
                }
            }

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;

            String ypath = physicalPath + "/" + year;
            String mpath = physicalPath + "/" + year+ "/" + month;

            //检查目录是否存在，存在就直接使用，不存在就创建目录
            dir = new File(ypath);
            if(!dir.exists()){
                dir = new File(mpath);
                dir.mkdirs();
            }else{
                dir = new File(mpath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
            }

            //获取一个UUID来作为存入服务器中的文件的名字
            String filename = SecureUtil.simpleUUID();
            filename = filename+extensionName;
            try {
                //将文件转存到指定位置
                file.transferTo(new File(dir,filename));
            } catch (Exception e) {
                e.printStackTrace();
            }

            //将文件的服务器地址存到数据库
            String path = "/" + year+ "/" + month + "/" + filename + ",";
            paths = paths.append(path);

        }

        return paths.deleteCharAt(paths.length()-1).toString();
    }

    /**
     * spring ModelAttribute
     * 放置在方法上面：表示请求该类的每个Action前都会首先执行它，也可以将一些准备数据的操作放置在该方法里面
     */
    @ModelAttribute
    public void setBaseController(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
        this.session=request.getSession();
    }
    
    /**
     * 去除前台“null”字符串
     * @param obj
     * @return
     */
    public Object checkNull(Object obj) {
        Class<? extends Object> clazz = obj.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 可访问私有变量
            field.setAccessible(true);
            // 获取属性类型
            String type = field.getGenericType().toString();
            // 如果type是类类型，则前面包含"class "，后面跟类名
            if ("class java.lang.String".equals(type)) {
                // 将属性的首字母大写
                String methodName = field.getName().replaceFirst(field.getName().substring(0, 1),
                        field.getName().substring(0, 1).toUpperCase());
                System.out.println(methodName);
                try {
                    Method methodGet = clazz.getMethod("get" + methodName);
                    // 调用getter方法获取属性值
                    String str = (String) methodGet.invoke(obj);
                    if (str!=null&&(str.length()==0||str.equals("undefined")||str.equals("null"))) {
                        // Method methodSet = clazz.getMethod("set" +
                        // methodName, new Class[] { String.class });
                        // methodSet.invoke(o, new Object[] { "" });
                        System.out.println(field.getType()); // class java.lang.String
                        // 如果为""的String类型的属性则重新复制为null
                        field.set(obj, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
    
    public String getCurrentOpenId() {
    	String openId = this.request.getHeader(GlobalConstants.OPEN_ID);
    	return openId;
    }


    /**
     * 标记成功Json执行结果
     * @return
     */
    protected JSONResult markSuccess() {
        return new JSONResult(GlobalConstants.S_CODE,"SUCCESS");
    }

    /**
     * 标记成功Json执行结果并携带数据
     * @param data
     * @return
     */
    protected JSONResult markSuccess(Object data) {
        JSONResult response = new JSONResult(GlobalConstants.S_CODE,"SUCCESS");
        response.setData(data);
        return response;
    }

    /**
     * 标记错误Json执行结果
     * @param message 错误信息
     * @return
     */
    protected JSONResult markError(String message) {
        JSONResult response = new JSONResult(GlobalConstants.E_CODE);
        response.setMsg(message);
        return response;
    }

    /**
     * 标记错误Json执行结果并携带数据
     * @param message 错误信息
     * @param data 数据
     * @return
     */
    protected JSONResult markError(String message, Object data) {
        JSONResult response = new JSONResult(GlobalConstants.E_CODE);
        response.setMsg(message);
        response.setData(data);
        return response;
    }

    /**
     * 标记错误Json执行结果并携带code
     * @param message 错误信息
     * @param code 执行码
     * @return
     */
    protected JSONResult markError(String message, Integer code) {
        JSONResult response = new JSONResult();
        response.setMsg(message);
        response.setCode(code);
        return response;
    }

    /**
     * 标记错误Json执行结果并携带code和数据
     *
     * @param message 错误信息
     * @param code 执行码
     * @param data 数据
     * @return
     */
    protected JSONResult markError(String message, Integer code, Object data) {
        JSONResult response = new JSONResult();
        response.setMsg(message);
        response.setCode(code);
        response.setData(data);
        return response;
    }
}
