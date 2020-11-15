package game.dto;

/**
 * 卡牌json
 *
 * @author chenzhilong
 * @date 2020/8/1
 */
public class CardJsonDarkMoon {
    /**
     * id
     */
    private Long id;
    /**
     * code
     */
    private String code;
    /**
     *
     */
    private String slug;


    /**
     * 名称
     */
    private String name;
    /**
     * 卡牌描述
     */
    private String description;
    /**
     * 卡牌类型
     */
    private String cardType;
    /**
     * 稀有度
     */
    private String cardRarity;
    /**
     * 职业
     */
    private String cardClass;


    /**
     * 随从类型
     */
    private String minionType;
    /**
     * 法力消耗
     */
    private String manaCost;
    /**
     * 攻击力
     */
    private String attack;
    /**
     * 生命值
     */
    private String health;

    /**
     * 关键字
     */
    private String legacyKeywords;

    /**
     *
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLegacyKeywords() {
        return legacyKeywords;
    }

    public void setLegacyKeywords(String legacyKeywords) {
        this.legacyKeywords = legacyKeywords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardRarity() {
        return cardRarity;
    }

    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    public String getCardClass() {
        return cardClass;
    }

    public void setCardClass(String cardClass) {
        this.cardClass = cardClass;
    }

    public String getMinionType() {
        return minionType;
    }

    public void setMinionType(String minionType) {
        this.minionType = minionType;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
