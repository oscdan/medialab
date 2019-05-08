package com.medialab.whisper.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Whisper implements Parcelable {
    public String wid;
    public String url;
    public String text;
    public int me2;
    public int replies;

    public Whisper() {
    }

    public Whisper(String wid, String url, String text, int me2, int replies) {
        this.wid = wid;
        this.url = url;
        this.text = text;
        this.me2 = me2;
        this.replies = replies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.wid);
        dest.writeString(this.url);
        dest.writeString(this.text);
        dest.writeInt(this.me2);
        dest.writeInt(this.replies);
    }

    protected Whisper(Parcel in) {
        this.wid = in.readString();
        this.url = in.readString();
        this.text = in.readString();
        this.me2 = in.readInt();
        this.replies = in.readInt();
    }

    public static final Parcelable.Creator<Whisper> CREATOR = new Parcelable.Creator<Whisper>() {
        @Override
        public Whisper createFromParcel(Parcel source) {
            return new Whisper(source);
        }

        @Override
        public Whisper[] newArray(int size) {
            return new Whisper[size];
        }
    };

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMe2() {
        return me2;
    }

    public void setMe2(int me2) {
        this.me2 = me2;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Whisper{" +
                "wid='" + wid + '\'' +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", me2=" + me2 +
                ", replies=" + replies +
                '}';
    }
}
