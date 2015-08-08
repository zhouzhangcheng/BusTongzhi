package com.zzc.bustongzhi.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/8/3.
 */
public class BusInfoBean {
    public String a1;
    public String a2;
    public String a3;
    public String a4;
    public String a5;
    public String a6;
    public String a7;
    public String a8;
    public String a9;
    public String a10;
    public String a11;
    public String a12;
    public String a13;
    public String a14;
    public String a15;
    public List<Info> list;
    public List<TicketInfo> list1;

    public class Info {
        public String a1;
        public String a2;
        public String a3;
        public String a4;
        public String a5;
    }

    public class TicketInfo {
        public String freeSeat;
        public String isbook;
        public String lineBaseId;
        public String lineClassid;
        public String orderDate;
        public String orderSeats;
        public String orderStartTime;
        public String price;
    }
}

