package com.tahoecn.xkc.converter;

public class CareerConsCustConverter {
	
	public static String GetCustomerActionByFollowUpWay(String FollowUpWay){
		String res = "";
        switch (FollowUpWay){
            case "A79A1057-D4DC-497C-8C81-8F93E422C819"://来电
                res = "来电";
                break;
            case "EEB32C04-5B7C-4676-A5DC-5F95E56370EB"://外展接待
                res = "外展接待";
                break;
            case "E30825AA-B894-4A5F-AF55-24CAC34C8F1F"://售场接待
                res = "售场接待";
                break;
            case "44775694-7C97-455C-B48E-154C6BFE2D94"://访谈
                res = "访谈";
                break;
            case "F0942939-A90E-4915-81D7-7752919B0F72"://去电
                res = "去电";
                break;
        }
        return res;
    }
}
