package com.yikang.health.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yikang.health.R;
import com.yikang.health.model.ReplyInfo;
import com.yikang.health.model.WorkCircleInfo;
import com.yikang.health.ui.main.MotherFragment;
import com.yikang.health.utils.Utils;
/**
 * 我的PK adapter
 * @author zwb
 *
 */
public class CircleAdapter extends BaseAdapter implements OnClickListener{
	
	public final class ViewHolder {
		 /**
        * 头像，评论按钮,链接图片缩略图
        */
       public ImageView headImageView, replyImageView;
       
       /**
        * 用户名，时间,文本,链接文本,分享了一次链接文本提醒
        */
       public TextView userNameTextView, timeTextView, textTextView,tv_praise_list,button_select;
       
       /**
        * 图片区域
        */
       public GridView photoGridView;
       
       
       /**
        * 评论列表区域
        */
       public ListView replyListView;
       public View ll_praise_list;
       /**
        * 头像地址，链接图片地址，用户名字符串，时间字符串，文本字符串，链接文本字符串，内容类型，图片缩略图
        */
       public String headUrl, linkHeadUrl, timeStr, textStr, linkTextStr, sendtype;
       
       // 小图，大图
       public ArrayList<String> thumbImageUrls;
       
       public ArrayList<String> imageUrls;
	}
//	private  MessagelistModel model;
    /**
     * 列表集合
     */
    private ArrayList<ReplyInfo> replyInfoList;
    /**
     * 实体类集合
     */
    private ArrayList<WorkCircleInfo> list = new ArrayList<WorkCircleInfo>();
    private Context cxt;
    
    public CircleAdapter(Context cxt) {
		// TODO Auto-generated constructor stub
    	this.cxt = cxt;
	}
    public void setData(ArrayList<WorkCircleInfo> list) {
		// TODO Auto-generated method stub
    	this.list = list;
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		WorkCircleInfo model = list.get(position);
		final ViewHolder viewHolder;
		if (null == convertView){
			viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(cxt).inflate(R.layout.circle_item_layout, null);
            viewHolder.headImageView = (ImageView)convertView.findViewById(R.id.headImageView);
            viewHolder.tv_praise_list = (TextView)convertView.findViewById(R.id.tv_praise_list);
            viewHolder.replyImageView = (ImageView)convertView.findViewById(R.id.replyImageView);
            viewHolder.userNameTextView = (TextView)convertView.findViewById(R.id.userNameTextView);
            viewHolder.timeTextView = (TextView)convertView.findViewById(R.id.tv_circle_time);
            viewHolder.textTextView = (TextView)convertView.findViewById(R.id.textTextView);
            viewHolder.ll_praise_list = convertView.findViewById(R.id.ll_praise_list);
            viewHolder.photoGridView = (GridView)convertView.findViewById(R.id.photoGridView);
            viewHolder.replyListView = (ListView)convertView.findViewById(R.id.replyListView);
            viewHolder.button_select = (TextView) convertView.findViewById(R.id.button_select);
            convertView.setTag(viewHolder);
        }else{
        	viewHolder = (ViewHolder)convertView.getTag();
        }
		viewHolder.replyImageView.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0){
                initPopupWindow(viewHolder.replyImageView, position);
            }
        });
		return convertView;
	}
	
	/**
     * 箭头对话框的PopupWindow
     */
    private PopupWindow arrowPopupWindow;
    /**
     * 弹出框中的点赞，评论，同情
     */
    private View rl_praise_icon, rl_reply_icon,rl_share_icon, rl_save_icon;
    /**
     * 弹出箭头对话框的布局View
     */
    private View arrowView;
	/**
     * 评论回复按钮事件
     * 弹出对话框
     */
//    TextView tv_save;
    
    public void initPopupWindow(ImageView replyImageView, int position){
//        replyIndex = position;     
        if (null == arrowPopupWindow){
            /* 映射出对话框的View*   ********************* */
            arrowView = LayoutInflater.from(cxt).inflate(R.layout.circle_reply_pop, null);
            rl_praise_icon = arrowView.findViewById(R.id.rl_praise_icon);
            rl_reply_icon = arrowView.findViewById(R.id.rl_reply_icon);
            rl_share_icon =arrowView.findViewById(R.id.rl_share_icon);
            rl_save_icon =arrowView.findViewById(R.id.rl_save_icon);
//            tv_save = (TextView) arrowView.findViewById(R.id.tv_save);
            rl_praise_icon.setOnClickListener(this);
            rl_reply_icon.setOnClickListener(this);
            rl_share_icon.setOnClickListener(this);
            rl_save_icon.setOnClickListener(this);
            int width = (Utils.getWidthPixels((Activity)cxt))*7 / 10;
            /* 声明一个弹出框 */
            arrowPopupWindow = new PopupWindow(replyImageView,width, LayoutParams.WRAP_CONTENT);
            
            /* 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景 */
            arrowPopupWindow.setBackgroundDrawable(cxt.getResources().getDrawable(android.R.color.transparent));
            
            /* 设置允许在PopupWindow外面点击消失 */
            arrowPopupWindow.setOutsideTouchable(true);
            
            // 设置动画
            arrowPopupWindow.setAnimationStyle(R.style.AnimationPreview);
            
            /* 设置有焦点(如果没有焦点，当你点击按钮时，对话框不会消失) */
            arrowPopupWindow.setFocusable(true);
            /* 为弹出框设定自定义的布局 */
            arrowPopupWindow.setContentView(arrowView);
        }
//        if(cloneList.get(replyIndex).isHasCollect()){
////     	   tv_save.setText("取消收藏");
//        	arrowView.findViewById(R.id.sc_icon).setVisibility(View.GONE);
//        	arrowView.findViewById(R.id.no_sc_icon).setVisibility(View.VISIBLE);
//        } else{
////        	tv_save.setText("收藏");
//        	arrowView.findViewById(R.id.sc_icon).setVisibility(View.VISIBLE);
//        	arrowView.findViewById(R.id.no_sc_icon).setVisibility(View.GONE);
//        }
        if (!arrowPopupWindow.isShowing()){
            // 显示的位置为:输入框左侧的图片的8分之1并且正数，在坐标0点的左侧 
            arrowPopupWindow.showAsDropDown(replyImageView);
        }
    }
    
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_praise_icon:
                /**
                 * 赞
                 */
            	 arrowPopupWindow.dismiss();
//            	 new submitAsync(0).execute();
                break;
            
            case R.id.rl_reply_icon:
                /**
                 * 评论
                 */
                if (null != arrowPopupWindow){
                    arrowPopupWindow.dismiss();
                    MotherFragment.instance.setReplyLayoutVisibility(View.VISIBLE);
                }
                break;
            
            case R.id.rl_share_icon:
                /**
                 * 分享
                 */
            	arrowPopupWindow.dismiss();
//            	showCircleDialog();
//            	isShare = true;
                break;
            case R.id.rl_save_icon:
                /**
                 * 收藏
                 */
            	arrowPopupWindow.dismiss();
//            	 new submitAsync(4).execute();
                break;
            case R.id.msg_express:
//                /**
//                 * 表情按钮
//                 */
//                if ((Integer)msg_express.getTag() == 1){
//                    msg_express.setTag(2);
//                    // 更换为键盘按钮,显示表情区域
//                    msg_express.setBackgroundResource(R.drawable.workcircle_keyboard_xml);
//                    
//                    showFace();
//                }else{
//                    msg_express.setTag(1);
//                    
//                    // 更换为表情按钮,隐藏表情区域
//                    msg_express.setBackgroundResource(R.drawable.workcircle_face_xml);
//                    
//                    hideFace();
//                }
                break;
            case R.id.msg_submit:
//                /**
//                 * 提交按钮
//                 */
//                if (!TextUtils.isEmpty(msg_content.getText().toString().trim())){
//                    new submitAsync(1).execute();
//                }
//                else{
//                    showToast(WorkCircleActivity.this, "请您输入内容");
//                }
                break;
        }
    }
}