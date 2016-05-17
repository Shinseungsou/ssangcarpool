package com.jfsiot.mju.ssangcarpool.model.map;

import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;

/**
 * Created by SSS on 2016-05-17.
 */
public class MapRoute {
    private MapPOIData originationPOIData, destinationPOIData;
    private TMapPoint pointOrigination, pointDestination;
    private TMapPolyLine route;

    private static MapRoute instance = null;
    public static synchronized MapRoute getInstance() {
        if( MapRoute.instance == null ) MapRoute.instance = new MapRoute();
        return MapRoute.instance;
    }

    public MapRoute(){

    }
    public void update(MapPOIData origination, MapPOIData destination){
        update(origination, destination, null);
    }

    public void update(MapPOIData origination, MapPOIData destination, TMapPolyLine polyLine){
        this.originationPOIData = origination;
        this.destinationPOIData = destination;
        this.route = polyLine;
        this.pointOrigination = new TMapPoint(Double.parseDouble(origination.getCenterLat()), Double.parseDouble(origination.getCenterLon()));
        this.pointDestination = new TMapPoint(Double.parseDouble(destination.getCenterLat()), Double.parseDouble(destination.getCenterLon()));
    }

    public TMapPolyLine getRoute() { return route; }
    public void setRoute(TMapPolyLine route) {this.route = route;}
    public MapPOIData getOriginationPOIData() {return originationPOIData;}
    public void setOriginationPOIData(MapPOIData originationPOIData) {this.originationPOIData = originationPOIData;}
    public MapPOIData getDestinationPOIData() {return destinationPOIData;}
    public void setDestinationPOIData(MapPOIData destinationPOIData) {this.destinationPOIData = destinationPOIData;}
    public TMapPoint getPointOrigination() {return pointOrigination;}
    public void setPointOrigination(TMapPoint pointOrigination) {this.pointOrigination = pointOrigination;}
    public TMapPoint getPointDestination() {return pointDestination;}
    public void setPointDestination(TMapPoint pointDestination) {this.pointDestination = pointDestination;}
}
