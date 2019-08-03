/**
 * 
 */
package com.tahoecn.xkc.common.enums;

/**
 * TODO 定时控制枚举类
 * 
 * type: 
 * fixedDelay 控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，那么直到上一次执行完，并间隔给定的时间后，执行下一次。 
 * fixedRate 是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行。
 * cron 表达式可以定制化执行任务，但是执行的方式是与fixedDelay相近的，也是会按照上一次方法结束时间开始算起。
 * 
 * @ClassName:  TaskInfoType
 * @author: wq_qycc
 * @date:   2019年8月2日   
 */
public enum TaskInfoType {
	ServiceAutoOC2Sale("cron","0 0/5 * * * ?"),
	ServiceAutoVoucher("cron","0 0/5 * * * ?"),
	ServiceAutoSyncCustomer("cron","0 0/1 * * * ?"),
	ServiceAutoProject("cron","0 0 1 * * ?"),
	ServiceAutoOpportunity("cron","0 0/5 * * * ?"),
	ServiceAutoRemindNew("cron","0 0 2 * * ?"),
	ServiceAutoUCUser("",""),
	ServiceAutoSaleModiApply("cron","0 0/5 * * * ?"),
	ServiceAutoRoom("cron","0 0/5 * * * ?"),
	ServiceAutoBuilding("cron","0 0/5 * * * ?"),
	ServiceAutoContract("cron","0 0/5 * * * ?"),
	ServiceAutoMonthYs("cron","0 0/5 * * * ?"),
	ServiceAutoFee("cron","0 0/5 * * * ?"),
	ServiceAutoGetin("cron","0 0/5 * * * ?"),
	ServiceAutoRemind_QD("cron","0 0 3 * * ?"),
	ServiceAutoRreclaim("cron","0 0 1 * * ?"),
	ServiceAutoTrade("cron","0 0/5 * * * ?"),
	ServiceAutoCommission("cron","0 0/5 * * * ?"),
	ServiceAutoOrder("cron","0 0/5 * * * ?"),
	ServiceAutoOwnerChannelProtectedJob("cron","0 0/5 * * * ?"),
	ServiceAutoBizParamOption("cron","0 0/5 * * * ?"),
	ServiceAutoUCUserChange("","");
	
	private String type;
	private String value;

	private TaskInfoType(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static TaskInfoType getByCode(String code) {
		if (code != null && !"".equals(code)) {
			for (TaskInfoType task : TaskInfoType.values()) {
				if(code.equals(task.name())) {
					return task;
				}
			}
		}
		return null;
	}
	
}
