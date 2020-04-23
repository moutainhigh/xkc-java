package com.tahoecn.xkc.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum ItemEnum {

    // 姓名
    name("姓名", "149F778D-4244-46F6-908F-D33A363A5B58"),
    // 主手机号
    mobile_main("主手机号", "21685728-54C5-4268-8371-62413CE42841"),
    // 辅手机号
    mobile_auxiliary("辅手机号", "21685728-54C5-4268-8371-62413CE42832"),
    // 性别
    sex("性别", "E72C340D-4092-467A-9B8F-5138DBDCA43B"),
    // 证件类型
    document_type("证件类型", "848EBE45-2C03-40C5-AE91-EC72533539BD"),
    // 证件号码
    identification_Number("证件号码", "56CAE4FF-83E7-4ADF-BAC8-5A32F0AD1D37"),
    // 通讯地址
    address("通讯地址", "9036F3E4-2EB7-4E7A-849D-FFF39EEB093D"),
    // 客户成员信息
    customer_member("客户成员信息", "C7B06191-E032-45F4-A990-DB7345012345"),
    // 是否收小筹
    ifexst("是否收小筹", "A977C068-98A9-4184-AD39-5E645778CB5D"),
    // 年龄段
    age("年龄段", "475D020E-DEC6-413C-898E-BC1A52511067"),
    // 顾客标签
    customer_flag("顾客标签", "47A4BAF0-F946-4A8A-81F1-D675BEED6DE2"),
    // 意向项目
    intention_project("意向项目", "7B44EDBA-FCB7-4040-A5FF-DDED0F01C76D"),
    // 意向项目分期
    intention_stages("意向项目分期", "BFD616A1-118F-4C9F-986F-BAA32A1A7EA3"),
    // 归属任务
    attribution_task("归属任务", "9A3ED7C5-F58C-4B3B-8EAC-83C6746B67AC"),
    // 渠道来源
    channel_sources("渠道来源", "F1725D6B-D1F7-4BC3-8C35-20FAB53A1602"),
    // 认知媒体
    cognitive_media("认知媒体", "BDDBD5B0-C1D2-4D76-96B4-C88C51C46AC0"),
    // 媒体子类
    media_subclass("媒体子类", "0BFD0AEF-2C16-4A91-94D3-9CBF114DC78A"),
    // 泰禾员工或家属
    family_members("泰禾员工或家属", "7FFDA0F6-6DEA-4CA5-86B6-C76D400702E3"),
    // 最认可因素
    accreditation_factor("最认可因素", "D9199E8F-D1E0-49C1-A9C6-EE11CA7D5ECD"),
    // 本次置业目的
    home_purchase_purpose("本次置业目的", "8741C05C-86DA-4952-8BC3-5C9CB669CCF9"),
    // 需求业态
    demand_formats("需求业态", "96F8FC2D-9AF2-413B-8628-85BC2040DC72"),
    // 备注
    remark("备注", "4D35ABCF-E61C-4650-9F55-0D2D66548CF0"),
    // 户籍所在地
    domicile_location("户籍所在地", "C7B06191-E032-45F4-A990-DB73450588DE"),
    // 婚姻状况
    marital_status("婚姻状况", "62AC02D0-C4AB-4EB7-A85F-6E1D509C166B"),
    // 家庭结构
    family_structure("家庭结构", "6438B845-441C-4A53-AB17-96ECF291955F"),
    // 所属行业
    industry("所属行业", "4124D302-0E37-47C7-A9CA-A7D5774A5361"),
    // 置业次数
    times_purchase("置业次数", "1A006C4D-B010-44CD-B09A-355E8A4328E3"),
    // 生日
    birthday("生日", "5B3EA885-C8D6-4890-A75E-88904575D46C"),
    // 跟进方式
    follow_mode("跟进方式", "480B60B2-1EE1-4A31-A810-072184A1E9D7"),
    // 意向级别
    intention_level("意向级别", "08289FD5-999A-4A9F-94D5-B85507575404"),
    // 跟进内容
    follow_content("跟进内容", "600DEB36-F5E0-4BA3-B7FA-1A244F0773AB"),
    // 下次跟进
    next_follow("下次跟进", "7E6CAE73-F032-4E3A-9551-C6F7DA2AEC10");

    ItemEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回结果描述
     */
    private String message;

    public static ItemEnum getEnumByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            // code为空返回草稿
            return null;
        }
        for (ItemEnum dynatownItemEnum : ItemEnum.values()) {
            if (dynatownItemEnum.getCode().equals(code)) {
                return dynatownItemEnum;
            }
        }
        return null;
    }

    public static ItemEnum getEnumByMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            // code为空返回草稿
            return null;
        }
        for (ItemEnum dynatownItemEnum : ItemEnum.values()) {
            if (dynatownItemEnum.getMessage().equals(message)) {
                return dynatownItemEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
