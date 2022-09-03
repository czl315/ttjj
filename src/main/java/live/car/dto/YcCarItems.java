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
public class YcCarItems {

    private int id;
    private String name;
    private List<YcCarParamValues> paramValues;
    private String paramId;
    private int paramType;
    private String desc;
    private String type;
    private String sameType;
    private int customSameType;
    private String imageUrl;
    private String videoPlaceId;
    private String imagePlaceId;
    private String encyclopediaId;
    private int keyItem;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setParamValues(List<YcCarParamValues> paramValues) {
        this.paramValues = paramValues;
    }
    public List<YcCarParamValues> getParamValues() {
        return paramValues;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }
    public String getParamId() {
        return paramId;
    }

    public void setParamType(int paramType) {
        this.paramType = paramType;
    }
    public int getParamType() {
        return paramType;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setSameType(String sameType) {
        this.sameType = sameType;
    }
    public String getSameType() {
        return sameType;
    }

    public void setCustomSameType(int customSameType) {
        this.customSameType = customSameType;
    }
    public int getCustomSameType() {
        return customSameType;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setVideoPlaceId(String videoPlaceId) {
        this.videoPlaceId = videoPlaceId;
    }
    public String getVideoPlaceId() {
        return videoPlaceId;
    }

    public void setImagePlaceId(String imagePlaceId) {
        this.imagePlaceId = imagePlaceId;
    }
    public String getImagePlaceId() {
        return imagePlaceId;
    }

    public void setEncyclopediaId(String encyclopediaId) {
        this.encyclopediaId = encyclopediaId;
    }
    public String getEncyclopediaId() {
        return encyclopediaId;
    }

    public void setKeyItem(int keyItem) {
        this.keyItem = keyItem;
    }
    public int getKeyItem() {
        return keyItem;
    }

}