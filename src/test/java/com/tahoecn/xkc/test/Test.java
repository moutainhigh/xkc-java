package com.tahoecn.xkc.test;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
      String str="[\"ADC0D952-63B5-E711-80C7-00505686C900,0F8A9F4F-26BD-41EF-994B-4408E9623A50\",\"4346185F-63B5-E711-80C7-00505686C900,DD5C3182-0294-46A1-A234-D1D0BF2BE9D7\",\"87416015-63B5-E711-80C7-00505686C900,97E2E8C4-07B6-487E-B8A1-92C0220564BD\",\"CD69E423-63B5-E711-80C7-00505686C900,\",\"AFA58323-6F94-E711-80C7-00505686C900,\"]";
        Object o = JSON.parseArray(str).get(0);
        System.out.println(JSON.parseArray(str).get(0));
    }
}
