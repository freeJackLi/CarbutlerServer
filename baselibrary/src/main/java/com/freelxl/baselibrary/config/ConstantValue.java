package com.freelxl.baselibrary.config;

public interface ConstantValue {

	// String HOST = "http://172.27.13.136/";
	String HOST = "http://www.ziroom.com/";

	String RANDOM_STRING = "7srzT88FcNiRQA3n";

	// 访问图片服务器的URL前缀
	// String IMAGE_HOST =
	// "http://pic.ziroom.com.cn/house_images/";
	String UP_LOAD_IMAGE_URL = "http://www.ziroom.com/?_p=api&_a=uploadImg"; // 图片上传地址
	// ===========================URL
	// List========================================

	// 登录的URL
	String URL_LOGIN = "index.php?_p=api_mobile&_a=login_normal";

	// 获取评估详情——根据房屋编号获取该用户对该房间的评估数据
	String URL_GET_ASSESS_DETAIL = "index.php?_p=api_mobile&_a=getAssessDetail";

	// 获取评估记录——获取该用户的全部评估记录
	String URL_GET_ASSESS_LIST = "index.php?_p=api_mobile&_a=getAssessList";

	// 获取房源详情
	String URL_GET_ROOM_INFO = "index.php?_p=api_mobile&_a=getRoomInfo";

	// 库存管理---获取房源列表
	String URL_GET_ROOM_LIST = "index.php?_p=api_mobile&_a=getRoomlist";

	// 获取房屋信息
	String URL_GET_HOUSE_INFO = "index.php?_p=api_mobile&_a=getHouseInfo";

	// 提交房屋信息获取基准价格——由第一步进入第二步
	String URL_GET_ASSESS_RENT_PRICE = "index.php?_p=api_mobile&_a=getAssessRentPrice";

	// 获取常用楼盘
	String URL_GET_COMMON_RESBLOCK = "index.php?_p=api_mobile&_a=getCommonlyResblock";

	// 添加常用楼盘
	String URL_ADD_COMMONLY_RESBLOCK = "index.php?_p=api_mobile&_a=addCommonlyResblock";

	// 评估收房价格——第二步点击评估
	String URL_GET_ASSESS_HIRE_PRICE = "index.php?_p=api_mobile&_a=getAssessHirePrice";

	// 提交评估——第三步点击评估按钮
	String URL_AUDIT_ASSESS_INFO = "index.php?_p=api_mobile&_a=auditAssessInfo";

	// 是否聚焦楼盘
	String URL_GET_IS_FOCUS_RESBLOCK = "index.php?_p=api_mobile&_a=getIsFocusResblock";

	// 删除常用楼盘
	String URL_DEL_COMMON_RESBLOCK = "index.php?_p=api_mobile&_a=delCommonlyResblock";

	// 目标看板接口
	String URL_TARGET_REACH = "index.php?_p=api_mobile&_a=targetReach";

	// 管家信息接口
	String URL_STEWARD_INFORMATION = "index.php?_p=api_mobile&_a=stewardInformation";

	String APP_TOKEN = "7srzT88FcNiRQA3n";

	// 客户通讯录
	String CUSTOMER_LIST = "index.php?_p=api_mobile&_a=myuserlist";
	// 客户信息
	String CUSTOMER_INFO = "index.php?_p=api_mobile&_a=userInformation";
	// 最近带看信息
	String LAST_LOOK_TIME = "index.php?_p=api_mobile&_a=latest_looktime";
	// 意向区域
	String INTENT_REGION = "index.php?_p=api_mobile&_a=getIntentionRegion";
	// 更新意向区域
	String CHANGE_INTENT_REGION = "index.php?_p=api_mobile&_a=updateIntentionRegion";
	// 设置重要客户
	String SET_VIP = "index.php?_p=api_mobile&_a=important_user";
	// 请求城区数据
	String GET_DISTRICT_LIST = "?_p=api_mobile&_a=get_realDistrictList";
	// 请求商圈数据
	String GET_BIZ_CIRCLE_LIST = "?_p=api_mobile&_a=get_realBizcircleList";
	// 库存接口
	String GET_ROOM_LIST = "index.php?_p=api_mobile&_a=getDeaiRoomlist";
	// 库存详情
	String GET_ROOM_ITEM = "index.php?_p=api_mobile&_a=getDeaiRoomInfo";

	// 拉去预约看房的接口
	static String URL_GET_LOOK_INFO = "index.php?_p=api_mobile&_a=get_lookinfo";

	// 更新预约看房信息
	String URL_UPDATE_LOOK_HOUSE = "index.php?_p=api_mobile&_a=update_lookhouse";

	// 登录接口
	String URL_LOGIN_NORMAL = "index.php?_p=api_mobile&_a=login_normal";

	// 整租的房源详情接口
	String URL_DETAIL_SHOW_ZZ = "index.php?_p=api_mobile&_a=detailShowZZ";

	// 分租的房源详情
	String URL_DETAIL_SHOW = "index.php?_p=api_mobile&_a=detailShow";

	// 目标看板的接口
	String URL_HOUSE_TARGET_KAN_BAN = "index.php/?_p=api_mobile&_a=cHouseTargetKanban";

	// 管家信息
	String URL_STEWARD_INFO = "index.php?_p=api_mobile&_a=stewardInformation";

	// 检查版本接口
	String URL_CHECK_VERSION = "?_p=api_mobile&_a=checkVersion";

	// 预约列表的接口
	String URL_RESERVATION_LIST = "index.php?_p=api_mobile&_a=get_reservation_list";

	String URL_ANNOUNCEMETNLIST = "?_p=api_mobile&_a=getAnnouncementList";

	String HAND_OVER_FIRST_STEP = "/index.php?_p=api_newsign&_a= property_goods";

	String HAND_OVER_FIRST_STEP_ADD = "/index.php?_p=api_newsign&_a= property_goods";

	// 交易详情的接口
	String URL_DEAL_DETAIL = "index.php?_p=api_newsign&_a=transaction_detail";

	String HAND_OVER_FIRST_STEP_SUBMIT = "/index.php?_p=api_newsign&_a= property_goods";

	String HAND_OVER_SECOND_STEP = "/index.php?_p=api_newsign&_a=property_key_cards";

	String HAND_OVER_SECOND_STEP_SUBMIT = "/index.php?_p=api_newsign&_a=property_key_cards";

	String HAND_OVER_THRID_STEP = "/index.php?_p=api_newsign&_a=property_fees";

	// /===========================Debug
	// Tag===========================================
	public static class APP_TAG {
		public final static String HTTP_REQUEST_TAG = "HTTP_REQUEST_TAG";

		public final static String ACTIVITY_TAG = "ACTIVITY_TAG";

		public final static String JSON_TAG = "JSON_TAG";

		public final static String JSON_TAG_SUCCESS = "JSON_TAG_SUCCESS";

		public final static String JSON_TAG_ERROR = "JSON_TAG_ERROR";

		public final static String CLIENT_REQUEST_INFO = "CLIENT_REQUEST_INFO";

		public final static String SEERVER_RESPONSE_INFO = "SEERVER_RESPONSE_INFO";

		public final static String JSON_ERROR = "JSON_ERROR";

		public final static String NET = "NET";

	}

}
