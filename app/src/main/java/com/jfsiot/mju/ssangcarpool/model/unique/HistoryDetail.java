package com.jfsiot.mju.ssangcarpool.model.unique;

import com.jfsiot.mju.ssangcarpool.model.data.Carpooler;
import com.jfsiot.mju.ssangcarpool.model.data.Path;

/**
 * Created by SSS on 2016-05-24.
 */
public class HistoryDetail {
    private static HistoryDetail instance;
    public static synchronized HistoryDetail getInstance(){
        return HistoryDetail.instance == null ? instance = new HistoryDetail() : instance;
    }

    private Path path;
    private Carpooler carpooler;

    public Path getPath() {return path;}
    public void setPath(Path path) {this.path = path;}
    public Carpooler getCarpooler() {return carpooler;}
    public void setCarpooler(Carpooler carpooler) {this.carpooler = carpooler;}
}
