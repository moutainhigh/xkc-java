package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class CustomerModelVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ClueID;
	
	private String OpportunityID;

	private String CustomerID;

	private String OrgID;

	private int IsNew;

	private List<PanelItem> Panel;
    
    
    public String getClueID() {
		return ClueID;
	}

	public void setClueID(String clueID) {
		ClueID = clueID;
	}

	public String getOpportunityID() {
		return OpportunityID;
	}

	public void setOpportunityID(String opportunityID) {
		OpportunityID = opportunityID;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getOrgID() {
		return OrgID;
	}

	public void setOrgID(String orgID) {
		OrgID = orgID;
	}

	public int getIsNew() {
		return IsNew;
	}

	public void setIsNew(int isNew) {
		IsNew = isNew;
	}

	public List<PanelItem> getPanel() {
		return Panel;
	}

	public void setPanel(List<PanelItem> panel) {
		Panel = panel;
	}

	public static class PanelItem{
        //默认项
		private String Name;

		private String Type;

		private int ListIndex;

		private List<ChildItem> Child;

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getType() {
			return Type;
		}

		public void setType(String type) {
			Type = type;
		}

		public int getListIndex() {
			return ListIndex;
		}

		public void setListIndex(int listIndex) {
			ListIndex = listIndex;
		}

		public List<ChildItem> getChild() {
			return Child;
		}

		public void setChild(List<ChildItem> child) {
			Child = child;
		}

    }
    
    public static class ChildItem{
    	private String ID;
        // 姓(必填)
    	private String Name;

        // 请输入姓氏
    	private String Placeholder;

    	private String Type;

    	private String Value;

    	private String ValueID;

    	private int ListIndex;

    	private int IsMust;

    	private int IsHide;

    	private int IsMustShow;
        
    	private int IsEdit;

    	private int IsMulti;

    	private int IsFullLine;

    	private int MaxLength;

    	private int MaxSelectedNum;
        
    	private String MinDate;
        
    	private String MaxDate;

    	private String NormalColor;

    	private String SelectedColor;

    	private String Partner;

    	private String SubPartner;
        
    	private List<VerifyItem> Verify;

        private List<OptionItem> Option;
        
        public ChildItem(){
            IsMust = 0;
            IsHide = 0;
            IsMustShow = 0;
            IsEdit = 0;
            IsMulti = 0;
        }

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getPlaceholder() {
			return Placeholder;
		}

		public void setPlaceholder(String placeholder) {
			Placeholder = placeholder;
		}

		public String getType() {
			return Type;
		}

		public void setType(String type) {
			Type = type;
		}

		public String getValue() {
			return Value;
		}

		public void setValue(String value) {
			Value = value;
		}

		public String getValueID() {
			return ValueID;
		}

		public void setValueID(String valueID) {
			ValueID = valueID;
		}

		public int getListIndex() {
			return ListIndex;
		}

		public void setListIndex(int listIndex) {
			ListIndex = listIndex;
		}

		public int getIsMust() {
			return IsMust;
		}

		public void setIsMust(int isMust) {
			IsMust = isMust;
		}

		public int getIsHide() {
			return IsHide;
		}

		public void setIsHide(int isHide) {
			IsHide = isHide;
		}

		public int getIsMustShow() {
			return IsMustShow;
		}

		public void setIsMustShow(int isMustShow) {
			IsMustShow = isMustShow;
		}

		public int getIsEdit() {
			return IsEdit;
		}

		public void setIsEdit(int isEdit) {
			IsEdit = isEdit;
		}

		public int getIsMulti() {
			return IsMulti;
		}

		public void setIsMulti(int isMulti) {
			IsMulti = isMulti;
		}

		public int getIsFullLine() {
			return IsFullLine;
		}

		public void setIsFullLine(int isFullLine) {
			IsFullLine = isFullLine;
		}

		public int getMaxLength() {
			return MaxLength;
		}

		public void setMaxLength(int maxLength) {
			MaxLength = maxLength;
		}

		public int getMaxSelectedNum() {
			return MaxSelectedNum;
		}

		public void setMaxSelectedNum(int maxSelectedNum) {
			MaxSelectedNum = maxSelectedNum;
		}

		public String getMinDate() {
			return MinDate;
		}

		public void setMinDate(String minDate) {
			MinDate = minDate;
		}

		public String getMaxDate() {
			return MaxDate;
		}

		public void setMaxDate(String maxDate) {
			MaxDate = maxDate;
		}

		public String getNormalColor() {
			return NormalColor;
		}

		public void setNormalColor(String normalColor) {
			NormalColor = normalColor;
		}

		public String getSelectedColor() {
			return SelectedColor;
		}

		public void setSelectedColor(String selectedColor) {
			SelectedColor = selectedColor;
		}

		public String getPartner() {
			return Partner;
		}

		public void setPartner(String partner) {
			Partner = partner;
		}

		public String getSubPartner() {
			return SubPartner;
		}

		public void setSubPartner(String subPartner) {
			SubPartner = subPartner;
		}

		public List<VerifyItem> getVerify() {
			return Verify;
		}

		public void setVerify(List<VerifyItem> verify) {
			Verify = verify;
		}

		public List<OptionItem> getOption() {
			return Option;
		}

		public void setOption(List<OptionItem> option) {
			Option = option;
		}
        
    }
    
    public static class VerifyItem{
    	private String ID;

        //身份证
    	private String Name;

    	private String Type;

    	private int MaxLength;

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getType() {
			return Type;
		}

		public void setType(String type) {
			Type = type;
		}

		public int getMaxLength() {
			return MaxLength;
		}

		public void setMaxLength(int maxLength) {
			MaxLength = maxLength;
		}

    }
    
    public static class OptionItem{
        //主键
    	private String ID;

        //名称
    	private String Name;

        //是否可以选择
    	private int IsChoose;

        //是否子集必填
    	private int IsSubMust;
        
        //可以选择颜色
    	private String SelectedColor;

        //不可以选择颜色
    	private String UnSelectedColor;

        //子集
    	private List<OptionItem> Child;

        public OptionItem(){
            IsSubMust = 0;
            IsChoose = 1;
            SelectedColor = "#000000";
            UnSelectedColor = "#cccccc";
        }

		public List<OptionItem> getChild() {
			return Child;
		}

		public void setChild(List<OptionItem> child) {
			Child = child;
		}



		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public int getIsChoose() {
			return IsChoose;
		}

		public void setIsChoose(int isChoose) {
			IsChoose = isChoose;
		}

		public int getIsSubMust() {
			return IsSubMust;
		}

		public void setIsSubMust(int isSubMust) {
			IsSubMust = isSubMust;
		}

		public String getSelectedColor() {
			return SelectedColor;
		}

		public void setSelectedColor(String selectedColor) {
			SelectedColor = selectedColor;
		}

		public String getUnSelectedColor() {
			return UnSelectedColor;
		}

		public void setUnSelectedColor(String unSelectedColor) {
			UnSelectedColor = unSelectedColor;
		}
        
    }
}
