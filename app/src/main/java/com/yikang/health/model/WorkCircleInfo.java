package com.yikang.health.model;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkCircleInfo implements Serializable
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2407441983190940422L;
    
    /**
     * 发送者用户名
     */
    private String sender;
    
    /**
     * 发送者的头像
     */
    private String avatar;
    
    /**
     * 内容类型
     * 1----→纯文本
     * 2----→纯图片
     * 3----→链接
     * 4----→文本+图片
     */
    private String sendtype;
    
    /**
     * 发送者手机号
     */
    private String senderPhoneNumber;
    
    /**
     * 文本内容
     */
    private String content;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 圈子条目id
     */
    private String pk_message;
    
    /**
     * 图片内容，如果多个用逗号隔开
     */
    private String image;
    
    /**
     * 小图
     */
    private ArrayList<String> thumbImageUrls = new ArrayList<String>();
    
    /**
     * 大图
     */
    private ArrayList<String> imageUrls = new ArrayList<String>();
    
    /**
     * 评论列表集合
     */
    private ArrayList<ReplyInfo> replyInfoList = new ArrayList<ReplyInfo>();
    
    /**
     * 链接地址
     */
    private String urlLink;
    
    /**
     * 发送时间
     */
    private String sendtime;
    
    public String getSender()
    {
        return sender;
    }
    
    public void setSender(String sender)
    {
        this.sender = sender;
    }
    
    public String getAvatar()
    {
        return avatar;
    }
    
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    
    public String getSendtype()
    {
        return sendtype;
    }
    
    public void setSendtype(String sendtype)
    {
        this.sendtype = sendtype;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getPk_message()
    {
        return pk_message;
    }
    
    public void setPk_message(String pk_message)
    {
        this.pk_message = pk_message;
    }
    
    public String getImage()
    {
        return image;
    }
    
    public void setImage(String image)
    {
        this.image = image;
    }
    
    public String getUrlLink()
    {
        return urlLink;
    }
    
    public void setUrlLink(String urlLink)
    {
        this.urlLink = urlLink;
    }
    
    public String getSendtime()
    {
        return sendtime;
    }
    
    public void setSendtime(String sendtime)
    {
        this.sendtime = sendtime;
    }
    
    public ArrayList<String> getThumbImageUrls()
    {
        return thumbImageUrls;
    }
    
    public void setThumbImageUrls(ArrayList<String> thumbImageUrls)
    {
        this.thumbImageUrls = thumbImageUrls;
    }
    
    public ArrayList<String> getImageUrls()
    {
        return imageUrls;
    }
    
    public void setImageUrls(ArrayList<String> imageUrls)
    {
        this.imageUrls = imageUrls;
    }
    
    public ArrayList<ReplyInfo> getReplyInfoList()
    {
        return replyInfoList;
    }
    
    public void setReplyInfoList(ArrayList<ReplyInfo> replyInfoList)
    {
        this.replyInfoList = replyInfoList;
    }

    public String getSenderPhoneNumber()
    {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber)
    {
        this.senderPhoneNumber = senderPhoneNumber;
    }
}