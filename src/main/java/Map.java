import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

public class Map {

    public int floor;        //地图对应楼层

    private int mapWidth;
    private int mapHeight;

    private String mapname;


    public Map( int width , int height) {
        mapWidth = width;
        mapHeight = height;
    }

    public Map(){}


    public boolean isInMap(int X, int Y){
        if(X < 0 || X > mapWidth)
            return false;
        if(Y < 0 || Y > mapHeight)
            return false;
        return true;
    }


    public boolean isInMapX(int X){
        if(X < 0 || X > mapWidth)
            return false;
        return true;
    }

    public boolean isInMapY(int Y){
        if(Y < 0 || Y > mapHeight)
            return false;
        return true;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    @JsonIgnore
    public String getWifiMapName() {
        return mapname+"-WM";
    }

    @JsonIgnore
    public String getMagnaticMapName() {
        return mapname+"-MM";
    }

    @JsonIgnore
    public String getWifiDataName() {
        return mapname+"-WD";
    }

    @JsonIgnore
    public String getMagnaticDataName() {
        return mapname+"-MD";
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

}

