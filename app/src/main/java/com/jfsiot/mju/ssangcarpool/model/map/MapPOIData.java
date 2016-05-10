package com.jfsiot.mju.ssangcarpool.model.map;

import android.graphics.Bitmap;

import com.skp.Tmap.TMapPOIItem;

/**
 * Created by SSS on 2016-04-17.
 */
public class MapPOIData {
    private String id;
    private String name;
    private String telNo;
    private String frontLat;
    private String frontLon;
    private String centerLat; //noorLat
    private String centerLon; //nootLon
    private String upperAddrName;
    private String middleAddrName;
    private String detailAddrName;
    private String firstNo;
    private String secondNo;
    private String upperBizName;
    private String middleBizName;
    private String detailBizName;
    private String desc;
    private String distance;
    private String roadName;
    private String buildingNo1;
    private String buildingNo2;
    private String radius;
    private Bitmap icon;

    public MapPOIData(TMapPOIItem poiItem){
        this.update(poiItem);
    }

    public MapPOIData update(TMapPOIItem poiItem){
        this.id = poiItem.id;
        this.name = poiItem.name;
        this.telNo = poiItem.telNo;
        this.frontLat = poiItem.frontLat;
        this.frontLon = poiItem.frontLon;
        this.centerLat = poiItem.noorLat;
        this.centerLon = poiItem.noorLon;
        this.upperAddrName = poiItem.upperAddrName;
        this.middleAddrName = poiItem.middleAddrName;
        this.detailAddrName = poiItem.detailAddrName;
        this.firstNo = poiItem.firstNo;
        this.secondNo = poiItem.secondNo;
        this.upperBizName = poiItem.upperBizName;
        this.middleBizName = poiItem.middleBizName;
        this.detailBizName = poiItem.detailBizName;
        this.desc = poiItem.desc;
        this.radius = poiItem.radius;
        this.distance = poiItem.distance;
        this.roadName = poiItem.roadName;
        this.buildingNo1 = poiItem.buildingNo1;
        this.buildingNo2 = poiItem.buildingNo2;
        this.icon = poiItem.getIcon();

        return this;
    }

    @Override
    public String toString() {
        return String.format("id : %s, name : %s, front lat/lon : <%s><%s>, center lat/lon : <%s><%s>, radius : %s ,AddrName : <%s><%s><%s>, BizName : <%s><%s><%s>, road : [%s], buildingNo1 : %s, buildingNo2 : %s,  desc : [%s]  ",
            this.id, this.name, this.frontLat, this.frontLon, this.centerLat, this.centerLon, this.radius,this.upperAddrName, this.middleAddrName, this.detailAddrName, this.upperBizName, this.middleBizName, this.detailBizName, this.roadName, this.buildingNo1, this.buildingNo2, this.desc);
    }
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getTelNo() {return telNo;}
    public void setTelNo(String telNo) {this.telNo = telNo;}
    public String getFrontLat() {return frontLat;}
    public void setFrontLat(String frontLat) {this.frontLat = frontLat;}
    public String getFrontLon() {return frontLon;}
    public void setFrontLon(String frontLon) {this.frontLon = frontLon;}
    public String getCenterLat() {return centerLat;}
    public void setCenterLat(String centerLat) {this.centerLat = centerLat;}
    public String getCenterLon() {return centerLon;}
    public void setCenterLon(String centerLon) {this.centerLon = centerLon;}
    public String getUpperAddrName() {return upperAddrName;}
    public void setUpperAddrName(String upperAddrName) {this.upperAddrName = upperAddrName;}
    public String getMiddleAddrName() {return middleAddrName;}
    public void setMiddleAddrName(String middleAddrName) {this.middleAddrName = middleAddrName;}
    public String getDetailAddrName() {return detailAddrName;}
    public void setDetailAddrName(String detailAddrName) {this.detailAddrName = detailAddrName;}
    public String getFirstNo() {return firstNo;}
    public void setFirstNo(String firstNo) {this.firstNo = firstNo;}
    public String getSecondNo() {return secondNo;}
    public void setSecondNo(String secondNo) {this.secondNo = secondNo;}
    public String getUpperBizName() {return upperBizName;}
    public void setUpperBizName(String upperBizName) {this.upperBizName = upperBizName;}
    public String getMiddleBizName() {return middleBizName;}
    public void setMiddleBizName(String middleBizName) {this.middleBizName = middleBizName;}
    public String getDetailBizName() {return detailBizName;}
    public void setDetailBizName(String detailBizName) {this.detailBizName = detailBizName;}
    public String getDesc() {return desc;}
    public void setDesc(String desc) {this.desc = desc;}
    public String getDistance() {return distance;}
    public void setDistance(String distance) {this.distance = distance;}
    public String getRoadName() {return roadName;}
    public void setRoadName(String roadName) {this.roadName = roadName;}
    public String getBuildingNo1() {return buildingNo1;}
    public void setBuildingNo1(String buildingNo1) {this.buildingNo1 = buildingNo1;}
    public String getBuildingNo2() {return buildingNo2;}
    public void setBuildingNo2(String buildingNo2) {this.buildingNo2 = buildingNo2;}
    public String getRadius() {return radius;}
    public void setRadius(String radius) {this.radius = radius;}
    public Bitmap getIcon() {return icon;}
    public void setIcon(Bitmap icon) {this.icon = icon;}

}
