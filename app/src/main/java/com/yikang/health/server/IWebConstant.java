package com.yikang.health.server;

public interface IWebConstant {
	
	
	// 每页显示的条数
	int PAGESIZE = 20;
	
	/**
	 * 测试标志
	 */
	boolean IS_DEBUG = true;
	
	/**
	 * 接口地址
	 */
//	String WEBSERVERURL_API ="http://interface-m.17dalie.com";//正式环境
//	String WEBSERVERUR_TEST = "http://interface-m.dalie8.com:8000";//测试环境
//
//	String WEBSERVERURL = IS_DEBUG?WEBSERVERUR_TEST:WEBSERVERURL_API;
	
	/**品牌更多*/
	int W_40006 = 40006;
	/**商品详情*/
	int W_30001 = 30001;
	/**优惠券领取*/
	int W_10003 = 10003;
	
	
	/**单个商品广告*/
	int A_30001 = 30001;
	/**商家广告*/
	int A_30002 = 30002;
	/**促销广告*/
	int A_30003 = 30003;
	/**套餐活动*/
	int A_30004 = 30004;
	/**即将开始的活动*/
	int A_30005 = 30005;
	/**专题活动*/
	int A_30006 = 30006;
	
	/**热卖推荐*/
	int C_40001 = 40001;
	/**皮革女装*/
	int C_40002 = 40002;
	/**皮革男装*/
	int C_40003 = 40003;
	/**皮草服饰*/
	int C_40004 = 40004;
	/**鞋包/配饰*/
	int C_40005 = 40005;
	/**品牌*/
	int C_40006 = 40006;
	
	
	/**
	 * 错误信息Key
	 */
	String ERROR_INFO = "error_info";
	
	/**
	 * 参数Key
	 */
	String DATA_KEY = "data";
	
	/**
	 * 版本升级
	 */
	String getUpdate = "/user/checkAndroidVersion";
	
	/** 首页列表信息 旧街口 */
	@Deprecated
	String indexData = "/page/index";
	/** 首页列表信息  新街口*/
	String indexActivityList = "/page/indexActivityList";
	
	/** 首页广告信息列表  旧街口*/
	String indexADV = "/ad/adList";
	
	/** 首页广告信息列表   新街口  暂时不用*/
	@Deprecated
	String indexTopActivity = "/page/indexTopActivity";
	
	/** 2016首页改版接口*/
	String indexField = "/page/indexField";
	/** 2016首页改版接口-商品推荐加载更多*/
	String IndexRecommendCommodityList = "/page/IndexRecommendCommodityList";
	/** 2016首页改版接口-广告栏位*/
	String indexActivityField = "/page/indexActivityField";
	
	
	/** 分类列表信息*/
	String category = "/category/categoryList";
	/** 全部分类列表信息*/
	String allCategoryList = "/category/allCategoryList";
	
	
	/** 商品列表信息*/
	String commodityList = "/commodity/commodityList";
	/** 商品评论列表 */
	String goodsComment = "/comment/newcommentCommodityList";
	/** 商品详情信息*/
	String goodsDetials = "/commodity/commodityDetail";
	/** 商品图文详情*/
	String goodsImageText = "/commodity/commodityContentImage";
	/** 商品交易记录*/
	String goodsRecord = "/commodity/commodityTradeList";
	/** 添加商品评论*/
	String addCommentCommodity = "/comment/addCommentCommodity";
	/** 获取评论FTP配置信息*/
	String commentImageFtp = "/service/commentImageFtp";
	/** 获取FTP配置信息*/
	String imageFtp = "/service/imageFtp";
	/**筛选页面详细数据*/
	String searchdatadetail = "/commodity/searchdatadetail";
	
	/** 购物车列表*/
	String cartList = "/user/cartList";
	/** 添加或修改购物车商品 */
	String cartEdit = "/user/cartEdit";
	/** 删除购物车商品 */
	String cartDelete = "/user/cartDelete";
	/** 用户登录*/
	String userLogin = "/user/login";
	
	
	
	/**吐槽反馈*/
	String addfeedback = "/service/addfeedback";
	
	/** 用户注册*/
	@Deprecated
	String userRegist = "/user/register";
	/** 用户注册*/
	String buyerRegister = "/user/buyerRegister";
	/** 获取验证码*/
	String userSendSms = "/user/userSendSms";
	
	/**验证手机验证码*/
	String userVerifySms = "/service/checkverificationcode";
	
	/** 重置密码*/
	String userPasswordMobile = "/user/userPasswordMobile";
	/** 修改用户资料*/
	String updateUserExtension = "/user/updateUserExtension";
	/** 修改推送消息的设备ID*/
	String updatedevicetoken = "/user/updatedevicetoken";
	
	
	/** 第三方登录 **/
	String wxLogin = "/user/thirdpass";
	/** 第三方注册 **/
	String wxRegister = "/user/thirdregister";
	/** 第三方绑定 **/
	String wxBind = "/user/thirdbind";
	
	/** 发票列表*/
	String userInvoiceList ="/user/listInvoice";
	/** 发票添加/修改*/
	String invoiceEdit = "/user/InvoiceEdit";
	/** 发票删除*/
	String invoiceDelete = "/user/invoiceDelete";
	/** 发票设置为默认*/
	String invoiceDefault = "/user/invoiceDefault";
	
	/** 收货地址列表*/
	String deliveryList ="/user/deliveryList";
	/** 收货地址添加/修改*/
	String deliveryEdit = "/user/deliveryEdit";
	/** 收货地址删除*/
	String deliveryDelete = "/user/deliveryDelete";
	/** 收货地址设置为默认*/
	String deliveryDefaultSetting = "/user/deliveryDefaultSetting";
	/** 默认收货地址信息*/
	String deliveryDefault = "/user/deliveryDefault";
	
	/** 添加删除/收藏*/
	String operatecollection = "/order/operatecollection";
	/** 我的收藏 */
	String collenctionList = "/order/mycollection";
	/** 我的评论 */
	String userCommodityCommentList = "/comment/userCommodityCommentList";
	/** 我的评论删除*/
	String deleteCommodityComment = "/comment/deleteCommodityComment";
	
	/** 订单检测 */
	String checkOrder = "/order/checkOrder";
	/** 订单详情 */
	String buyerOrderDetail = "/order/buyerOrderDetail";
	/** 订单列表 */
	String buyerOrderList = "/order/buyerOrderList";
	/** 提交订单 */
	String addOrder = "/order/addOrder";
	/** 取消订单 */
	String buyerCancelOrder = "/order/buyerCancelOrder";
	/** 提醒卖家发货 */
	String remindOrder = "/order/remindOrder";
	/** 获取订单支付信息 */
	String buyerPaymentOrder = "/order/buyerPaymentOrder";
	/** 订单物流信息 */
	String orderWaybill = "/order/orderWaybill";
	/** 确认订单 */
	String buyerCheckDeliveryOrder = "/order/buyerCheckDeliveryOrder";
	/** 申请售后 */
	String buyerAddReturnGoods = "/order/buyerAddReturnGoods";
	
	/** 获取商品规格列表 */
	String commodityStandard =  "/commodity/commodityStandard";
	
	/** 用户优惠券列表*/
	String userCouponList = "/coupon/userCouponList";
	/** 获取优惠券 */
	String getCoupon = "/coupon/getCoupon";
	/** 用户优惠券列表_新接口*/
	String newUserCouponList = "/coupon/newUserCouponList";
	
	/** 用户消息列表 */
	String userSysMessage = "/user/usermessagelist";
	
	
}
