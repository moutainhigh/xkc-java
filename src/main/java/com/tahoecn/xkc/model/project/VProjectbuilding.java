package com.tahoecn.xkc.model.project;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
@TableName("V_ProjectBuilding")
@ApiModel(value="VProjectbuilding对象", description="")
public class VProjectbuilding implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("ProjectPID")
    private String ProjectPID;

    @TableField("ProjectName")
    private String ProjectName;

    @TableField("BuildingID")
    private String BuildingID;

    @TableField("BuildingName")
    private String BuildingName;

    @TableField("BuildingUnSaleCount")
    private Integer BuildingUnSaleCount;

    @TableField("BuildingSaleCount")
    private Integer BuildingSaleCount;

    @TableField("BuildingUnSaleRate")
    private String BuildingUnSaleRate;

    @TableField("BuildingSaleRate")
    private String BuildingSaleRate;

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getProjectPID() {
        return ProjectPID;
    }

    public void setProjectPID(String ProjectPID) {
        this.ProjectPID = ProjectPID;
    }
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }
    public String getBuildingID() {
        return BuildingID;
    }

    public void setBuildingID(String BuildingID) {
        this.BuildingID = BuildingID;
    }
    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String BuildingName) {
        this.BuildingName = BuildingName;
    }
    public Integer getBuildingUnSaleCount() {
        return BuildingUnSaleCount;
    }

    public void setBuildingUnSaleCount(Integer BuildingUnSaleCount) {
        this.BuildingUnSaleCount = BuildingUnSaleCount;
    }
    public Integer getBuildingSaleCount() {
        return BuildingSaleCount;
    }

    public void setBuildingSaleCount(Integer BuildingSaleCount) {
        this.BuildingSaleCount = BuildingSaleCount;
    }
    public String getBuildingUnSaleRate() {
        return BuildingUnSaleRate;
    }

    public void setBuildingUnSaleRate(String BuildingUnSaleRate) {
        this.BuildingUnSaleRate = BuildingUnSaleRate;
    }
    public String getBuildingSaleRate() {
        return BuildingSaleRate;
    }

    public void setBuildingSaleRate(String BuildingSaleRate) {
        this.BuildingSaleRate = BuildingSaleRate;
    }

    @Override
    public String toString() {
        return "VProjectbuilding{" +
        "ProjectID=" + ProjectID +
        ", ProjectPID=" + ProjectPID +
        ", ProjectName=" + ProjectName +
        ", BuildingID=" + BuildingID +
        ", BuildingName=" + BuildingName +
        ", BuildingUnSaleCount=" + BuildingUnSaleCount +
        ", BuildingSaleCount=" + BuildingSaleCount +
        ", BuildingUnSaleRate=" + BuildingUnSaleRate +
        ", BuildingSaleRate=" + BuildingSaleRate +
        "}";
    }
}
