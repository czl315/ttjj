package game.dto;

/**
 * @author chenzhilong
 * @date 2020/8/1
 */
public class Card {
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String cardName;
    /**
     * 卡牌描述
     */
    private String cardText;
    /**
     * 类型
     */
    private String type;
    /**
     * 稀有度
     */
    private String rarity;
    /**
     * 职业
     */
    private String profession;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
