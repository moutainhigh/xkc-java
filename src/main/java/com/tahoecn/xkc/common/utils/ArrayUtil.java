package com.tahoecn.xkc.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组合并
 * @author WH
 */
public class ArrayUtil {
	
    public static String[] ArrayUnion(String[] befor,String[] now) {
        List<String> list = new ArrayList<String>(Arrays.asList(befor));
        list.addAll(Arrays.asList(now));
        Object[] c = list.toArray();
        System.out.println(Arrays.toString(c));
        return (String[]) c;
    }
}
