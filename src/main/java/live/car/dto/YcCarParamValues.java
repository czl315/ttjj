/**
 * Copyright 2022 json.cn
 */
package live.car.dto;
import java.util.List;

/**
 * Auto-generated: 2022-09-03 10:25:15
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class YcCarParamValues {

    private long id;
    private String value;
    private String baseInfo;
    private String baseInfoObj;
    private List<YcCarSubList> subList;
    private String status;
    private int videoFlag;
    private int imageFlag;
    private int evaluationId;
    private int evaluationItemId;
    private String encyclopediaId;
    private String encyclopediaSubId;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setBaseInfo(String baseInfo) {
        this.baseInfo = baseInfo;
    }
    public String getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfoObj(String baseInfoObj) {
        this.baseInfoObj = baseInfoObj;
    }
    public String getBaseInfoObj() {
        return baseInfoObj;
    }

    public void setSubList(List<YcCarSubList> subList) {
        this.subList = subList;
    }
    public List<YcCarSubList> getSubList() {
        return subList;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setVideoFlag(int videoFlag) {
        this.videoFlag = videoFlag;
    }
    public int getVideoFlag() {
        return videoFlag;
    }

    public void setImageFlag(int imageFlag) {
        this.imageFlag = imageFlag;
    }
    public int getImageFlag() {
        return imageFlag;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationItemId(int evaluationItemId) {
        this.evaluationItemId = evaluationItemId;
    }
    public int getEvaluationItemId() {
        return evaluationItemId;
    }

    public void setEncyclopediaId(String encyclopediaId) {
        this.encyclopediaId = encyclopediaId;
    }
    public String getEncyclopediaId() {
        return encyclopediaId;
    }

    public void setEncyclopediaSubId(String encyclopediaSubId) {
        this.encyclopediaSubId = encyclopediaSubId;
    }
    public String getEncyclopediaSubId() {
        return encyclopediaSubId;
    }

}