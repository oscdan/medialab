package com.medialab.whisper.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Root implements Parcelable {
    public ArrayList<Whisper> popular;
    public ArrayList<Whisper> replies;

    public Root() {
    }

    public Root(ArrayList<Whisper> popular, ArrayList<Whisper> replies) {
        this.popular = popular;
        this.replies = replies;
    }

    public ArrayList<Whisper> getPopular() {
        return popular;
    }

    public void setPopular(ArrayList<Whisper> popular) {
        this.popular = popular;
    }

    public ArrayList<Whisper> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Whisper> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Response{" +
                "popular=" + popular +
                ", replies=" + replies +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.popular);
        dest.writeTypedList(this.replies);
    }

    protected Root(Parcel in) {
        this.popular = in.createTypedArrayList(Whisper.CREATOR);
        this.replies = in.createTypedArrayList(Whisper.CREATOR);
    }

    public static final Parcelable.Creator<Root> CREATOR = new Parcelable.Creator<Root>() {
        @Override
        public Root createFromParcel(Parcel source) {
            return new Root(source);
        }

        @Override
        public Root[] newArray(int size) {
            return new Root[size];
        }
    };
}
