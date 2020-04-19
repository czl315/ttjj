package dto;

import java.util.List;

/**
 * @author chenzhilong
 * @date
 */
public class LsjzData {
    private String FundType;
    private String SYType;
    private String isNewType;
    private String Feature;
    private List<LsjzDataLsjz> LSJZList;

    public List<LsjzDataLsjz> getLSJZList() {
        return LSJZList;
    }

    public void setLSJZList(List<LsjzDataLsjz> LSJZList) {
        this.LSJZList = LSJZList;
    }

    public String getFundType() {
        return FundType;
    }

    public void setFundType(String fundType) {
        FundType = fundType;
    }

    public String getSYType() {
        return SYType;
    }

    public void setSYType(String SYType) {
        this.SYType = SYType;
    }

    public String getIsNewType() {
        return isNewType;
    }

    public void setIsNewType(String isNewType) {
        this.isNewType = isNewType;
    }

    public String getFeature() {
        return Feature;
    }

    public void setFeature(String feature) {
        Feature = feature;
    }
}
