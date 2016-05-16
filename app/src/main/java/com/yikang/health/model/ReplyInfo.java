package com.yikang.health.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReplyInfo implements Parcelable {
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1158083296603742699L;
    
    /**
     * 发送者
     */
    private String sender;
    
    /**
     * 回复内容
     */
    private String content;
    
    /**
     * 发送人的手机号
     */
    private String senderPhoneNumber;
    
    /**
     * 接收人的手机号
     */
    private String receiverPhoneNumber;
    
    /**
     * 回复的id
     */
    private String pk_reply;
    
    /**
     * 接收者
     */
    private String receiver;
    
    /**
     * 对应的圈子条目id
     */
    private String pk_message;
    
    /**
     * 回复时间
     */
    private String sendTime;
    
    /**
     * 回复的圈子内容类型
     */
    private String sendType;
    
    public String getSender()
    {
        return sender;
    }
    
    public void setSender(String sender)
    {
        this.sender = sender;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public String getPk_reply()
    {
        return pk_reply;
    }
    
    public void setPk_reply(String pk_reply)
    {
        this.pk_reply = pk_reply;
    }
    
    public String getReceiver()
    {
        return receiver;
    }
    
    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }
    
    public String getPk_message()
    {
        return pk_message;
    }
    
    public void setPk_message(String pk_message)
    {
        this.pk_message = pk_message;
    }
    
    public String getSendTime()
    {
        return sendTime;
    }
    
    public void setSendTime(String sendTime)
    {
        this.sendTime = sendTime;
    }
    
    public String getSendType()
    {
        return sendType;
    }
    
    public void setSendType(String sendType)
    {
        this.sendType = sendType;
    }
    
    public String getSenderPhoneNumber()
    {
        return senderPhoneNumber;
    }
    
    public void setSenderPhoneNumber(String senderPhoneNumber)
    {
        this.senderPhoneNumber = senderPhoneNumber;
    }
    
    public String getReceiverPhoneNumber()
    {
        return receiverPhoneNumber;
    }
    
    public void setReceiverPhoneNumber(String receiverPhoneNumber)
    {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sender);
        dest.writeString(this.content);
        dest.writeString(this.senderPhoneNumber);
        dest.writeString(this.receiverPhoneNumber);
        dest.writeString(this.pk_reply);
        dest.writeString(this.receiver);
        dest.writeString(this.pk_message);
        dest.writeString(this.sendTime);
        dest.writeString(this.sendType);
    }

    public ReplyInfo() {
    }

    protected ReplyInfo(Parcel in) {
        this.sender = in.readString();
        this.content = in.readString();
        this.senderPhoneNumber = in.readString();
        this.receiverPhoneNumber = in.readString();
        this.pk_reply = in.readString();
        this.receiver = in.readString();
        this.pk_message = in.readString();
        this.sendTime = in.readString();
        this.sendType = in.readString();
    }

    public static final Parcelable.Creator<ReplyInfo> CREATOR = new Parcelable.Creator<ReplyInfo>() {
        @Override
        public ReplyInfo createFromParcel(Parcel source) {
            return new ReplyInfo(source);
        }

        @Override
        public ReplyInfo[] newArray(int size) {
            return new ReplyInfo[size];
        }
    };
}
