package work.dto;

import java.util.List;

/**
 * 盲盒任务
 *
 */
public class TaskVo {
    private int type;
    private int state;
    private String url;
    private int totalNum;
    private int finishNum;
    private int coinNum;
    private boolean hasFollow;
    private String name;
    private String shopId;
    private String skuId;
    private String venderId;
    private boolean hasMember;
    private String activeId;
    private List<SupporterVO> supporters;//助力人信息

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }

    public int getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(int coinNum) {
        this.coinNum = coinNum;
    }

    public boolean isHasFollow() {
        return hasFollow;
    }

    public void setHasFollow(boolean hasFollow) {
        this.hasFollow = hasFollow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
    }

    public boolean isHasMember() {
        return hasMember;
    }

    public void setHasMember(boolean hasMember) {
        this.hasMember = hasMember;
    }

    public String getActiveId() {
        return activeId;
    }

    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }

    public List<SupporterVO> getSupporters() {
        return supporters;
    }

    public void setSupporters(List<SupporterVO> supporters) {
        this.supporters = supporters;
    }
}
