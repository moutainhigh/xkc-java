package com.tahoecn.xkc.model.reprot;

import com.tahoecn.xkc.model.customer.CostomerReport;

/**
 * @program: xkc
 * @Date: 2020/3/31 0031 16:33
 * @Author: houzh
 * @Description:
 */
public class KfCostomerReportDetailVO extends CostomerReport {

 //推荐业主区域
  private String tjOrgName;

  //推荐业主城市公司
  private String tjCityName;

  //推荐业主项目
  private String tjProjectName;

  //推荐业主房屋编号
  private String tjRoomCode;

 public String getTjOrgName() {
  return tjOrgName;
 }

 public void setTjOrgName(String tjOrgName) {
  this.tjOrgName = tjOrgName;
 }

 public String getTjCityName() {
  return tjCityName;
 }

 public void setTjCityName(String tjCityName) {
  this.tjCityName = tjCityName;
 }

 public String getTjProjectName() {
  return tjProjectName;
 }

 public void setTjProjectName(String tjProjectName) {
  this.tjProjectName = tjProjectName;
 }

 public String getTjRoomCode() {
  return tjRoomCode;
 }

 public void setTjRoomCode(String tjRoomCode) {
  this.tjRoomCode = tjRoomCode;
 }
}
