import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

public class MagneticData  {

    private final int MAX_LENGTH = 400;

    public double[] MFSX; //magnetic field strength of x [0] is mean value and [1] is variance
    public double[] MFSY; //magnetic field strength of y
    public double[] MFSZ; //magnetic field strength of z
    public double[] MFST; // total magnetic field strength

    public ArrayList<Double> MFSXData;
    public ArrayList<Double> MFSYData;
    public ArrayList<Double> MFSZData;
    public ArrayList<Double> MFSTData;

    //用于匹配定位成员
    public int x = 0;//数据点坐标
    public int y = 0;
    public float matchProbality = 0;//匹配概率

    //用于匹配定位构造
    public MagneticData(int x, int y, float matchProbality) {
        this.x = x;
        this.y = y;
        this.matchProbality = matchProbality;
    }

    public MagneticData(){
        MFSXData = new ArrayList<Double>();
        MFSYData = new ArrayList<Double>();
        MFSZData = new ArrayList<Double>();
        MFSTData = new ArrayList<Double>();
        MFSX = new double[2];
        MFSY = new double[2];
        MFSZ = new double[2];
        MFST = new double[2];
    }

    @JsonIgnore
    public void setData(double MFSX, double MFSY, double MFSZ, double MFST, boolean calcuate){
        if(MFSXData.size() > MAX_LENGTH){
            MFSXData.remove(0);
            MFSYData.remove(0);
            MFSZData.remove(0);
            MFSTData.remove(0);
        }
        MFSXData.add(MFSX);
        MFSYData.add(MFSY);
        MFSZData.add(MFSZ);
        MFSTData.add(MFST);
        if(calcuate){
            this.MFSX[0] = calculateMean(MFSXData);
            this.MFSY[0] = calculateMean(MFSYData);
            this.MFSZ[0] = calculateMean(MFSZData);
            this.MFST[0] = calculateMean(MFSTData);
            this.MFSX[1] = calculateVariance(MFSXData,this.MFSX[0]);
            this.MFSY[1] = calculateVariance(MFSYData,this.MFSY[0]);
            this.MFSZ[1] = calculateVariance(MFSZData,this.MFSZ[0]);
            this.MFST[1] = calculateVariance(MFSTData,this.MFST[0]);
        }
    }

    public void calculateMV(){
        this.MFSX[0] = calculateMean(MFSXData);
        this.MFSY[0] = calculateMean(MFSYData);
        this.MFSZ[0] = calculateMean(MFSZData);
        this.MFST[0] = calculateMean(MFSTData);
        this.MFSX[1] = calculateVariance(MFSXData,this.MFSX[0]);
        this.MFSY[1] = calculateVariance(MFSYData,this.MFSY[0]);
        this.MFSZ[1] = calculateVariance(MFSZData,this.MFSZ[0]);
        this.MFST[1] = calculateVariance(MFSTData,this.MFST[0]);
    }

    private double calculateMean(ArrayList<Double> data){
        double sum = 0;
        for (Double value:data){
            sum += value;
        }
        return sum/data.size();
    }

    private double calculateVariance(ArrayList<Double> data, double mean){
        double sum = 0;
        for (Double value:data){
            sum += Math.pow((value - mean),2);
        }
        return sum/data.size();
    }

    public double[] getMFSX() {
        return MFSX;
    }

    public void setMFSX(double[] MFSX) {
        this.MFSX = MFSX;
    }

    public double[] getMFSY() {
        return MFSY;
    }

    public void setMFSY(double[] MFSY) {
        this.MFSY = MFSY;
    }

    public double[] getMFSZ() {
        return MFSZ;
    }

    public void setMFSZ(double[] MFSZ) {
        this.MFSZ = MFSZ;
    }

    public double[] getMFST() {
        return MFST;
    }

    public void setMFST(double[] MFST) {
        this.MFST = MFST;
    }

    public ArrayList<Double> getMFSXData() {
        return MFSXData;
    }

    public void setMFSXData(ArrayList<Double> MFSXData) {
        this.MFSXData = MFSXData;
    }

    public ArrayList<Double> getMFSYData() {
        return MFSYData;
    }

    public void setMFSYData(ArrayList<Double> MFSYData) {
        this.MFSYData = MFSYData;
    }

    public ArrayList<Double> getMFSZData() {
        return MFSZData;
    }

    public void setMFSZData(ArrayList<Double> MFSZData) {
        this.MFSZData = MFSZData;
    }

    public ArrayList<Double> getMFSTData() {
        return MFSTData;
    }

    public void setMFSTData(ArrayList<Double> MFSTData) {
        this.MFSTData = MFSTData;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getMatchProbality() {
        return matchProbality;
    }

    public void setMatchProbality(float matchProbality) {
        this.matchProbality = matchProbality;
    }
}
