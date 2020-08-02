package game.dto;

import java.util.List;

/**
 * 卡牌json父类
 * @author chenzhilong
 * @date 2020/8/1
 */
public class CardJsonPt {
    /**
     * 总数
     */
    private String total;
    /**
     * 职业
     */
    private String curCardClass;

    /**
     * 卡牌json列表
     */
    private List<CardJson> cards;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurCardClass() {
        return curCardClass;
    }

    public void setCurCardClass(String curCardClass) {
        this.curCardClass = curCardClass;
    }

    public List<CardJson> getCards() {
        return cards;
    }

    public void setCards(List<CardJson> cards) {
        this.cards = cards;
    }
}
