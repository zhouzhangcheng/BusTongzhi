package com.zzc.bustongzhi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/8/7.
 */
public class BusStation implements Parcelable {
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a1);
        dest.writeString(this.a2);
        dest.writeString(this.a3);
        dest.writeString(this.a4);
        dest.writeString(this.a5);
    }

    public BusStation() {
    }

    protected BusStation(Parcel in) {
        this.a1 = in.readString();
        this.a2 = in.readString();
        this.a3 = in.readString();
        this.a4 = in.readString();
        this.a5 = in.readString();
    }

    public static final Parcelable.Creator<BusStation> CREATOR = new Parcelable.Creator<BusStation>() {
        public BusStation createFromParcel(Parcel source) {
            return new BusStation(source);
        }

        public BusStation[] newArray(int size) {
            return new BusStation[size];
        }
    };
}
