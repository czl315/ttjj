package game;

import com.alibaba.fastjson.JSON;
import game.dto.Card;
import game.dto.CardJson;
import game.dto.CardJsonPt;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.FundTrade;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LscsHtmlUtil {

    public static void main(String[] args) {
        String str = "";
        {
            str = "";
        }

        String page = "";
        String cardSet = "scholomance-academy";//通灵学院
//        String cardClass = "demonhunter";
        String cardClass = "";
        for (int i = 1; i < 20; i++) {
            String rsJson = findJsonCard("", cardClass, i + page, cardSet);
//            System.out.println(rsJson);//返回结果
            CardJsonPt cardJsonPt = JSON.parseObject(rsJson, CardJsonPt.class);
            List<CardJson> cardJsonList = cardJsonPt.getCards();
            if (cardJsonList == null || cardJsonList.size() <= 0) {
                break;
            }
            for (CardJson cardJson : cardJsonList) {
//            System.out.println(JSON.toJSONString(cardJson));
                handlerCardRs(cardJson);
                System.out.println(cardJson.getName() + "\t"
                        + cardJson.getCode() + "\t"
                        + cardJson.getDescription() + "\t"
                        + cardJson.getCardType() + "\t"
                        + cardJson.getMinionType() + "\t"
                        + cardJson.getAttack() + "\t"
                        + cardJson.getHealth() + "\t"
                        + cardJson.getCardClass() + "\t"
                        + cardJson.getManaCost() + "\t"
                        + cardJson.getCardRarity() + "\t"
                        + "1通灵学院");
            }
        }


    }

    /**
     * 处理卡牌结果数据
     *
     * @param cardJson
     */
    private static void handlerCardRs(CardJson cardJson) {
        if (cardJson == null) {
            return;
        }
        if ("minion".equals(cardJson.getCardType())) {
            cardJson.setCardType("随从");
        }
        if ("spell".equals(cardJson.getCardType())) {
            cardJson.setCardType("法术");
        }
        if (StringUtils.isBlank(cardJson.getCardType())) {
            cardJson.setCardType("");
        }
        //卡牌类型
        if ("demonhunter".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("恶魔猎手");
        }
        if ("druid".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("德鲁伊");
        }
        if ("hunter".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("猎人");
        }
        if ("mage".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("法师");
        }
        if ("neutral".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("中立");
        }
        if ("paladin".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("圣骑士");
        }
        if ("priest".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("牧师");
        }
        if ("rogue".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("潜行者");
        }
        if ("shaman".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("萨满");
        }
        if ("warlock".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("术士");
        }
        if ("warrior".equals(cardJson.getCardClass())) {
            cardJson.setCardClass("战士");
        }

        //关键字
        if(StringUtils.isBlank(cardJson.getCardClass())){
            if (cardJson.getLegacyKeywords().contains("恶魔猎手")) {
                cardJson.setCardClass(cardJson.getCardClass()+"恶魔猎手,");
            }
            if (cardJson.getLegacyKeywords().contains("德鲁伊")) {
                cardJson.setCardClass(cardJson.getCardClass()+"德鲁伊,");
            }
            if (cardJson.getLegacyKeywords().contains("猎人")) {
                cardJson.setCardClass(cardJson.getCardClass()+"猎人,");
            }
            if (cardJson.getLegacyKeywords().contains("法师")) {
                cardJson.setCardClass(cardJson.getCardClass()+"法师,");
            }
            if (cardJson.getLegacyKeywords().contains("牧师")) {
                cardJson.setCardClass(cardJson.getCardClass()+"牧师,");
            }
            if (cardJson.getLegacyKeywords().contains("圣骑士")) {
                cardJson.setCardClass(cardJson.getCardClass()+"圣骑士,");
            }
            if (cardJson.getLegacyKeywords().contains("潜行者")) {
                cardJson.setCardClass(cardJson.getCardClass()+"潜行者,");
            }
            if (cardJson.getLegacyKeywords().contains("萨满")) {
                cardJson.setCardClass(cardJson.getCardClass()+"萨满,");
            }
            if (cardJson.getLegacyKeywords().contains("术士")) {
                cardJson.setCardClass(cardJson.getCardClass()+"术士,");
            }
            if (cardJson.getLegacyKeywords().contains("战士")) {
                cardJson.setCardClass(cardJson.getCardClass()+"战士,");
            }
        }



        //随从类型
        if (StringUtils.isBlank(cardJson.getMinionType())) {
            cardJson.setMinionType("");
        }
        if ("minion".equals(cardJson.getMinionType())) {
            cardJson.setMinionType("随从");
        }

        //稀有度
        if ("rare".equals(cardJson.getCardRarity())) {
            cardJson.setCardRarity("稀有");
        }
        if ("legendary".equals(cardJson.getCardRarity())) {
            cardJson.setCardRarity("传说");
        }
        if ("epic".equals(cardJson.getCardRarity())) {
            cardJson.setCardRarity("史诗");
        }
        if ("common".equals(cardJson.getCardRarity())) {
            cardJson.setCardRarity("普通");
        }
        //攻击
        if (StringUtils.isBlank(cardJson.getAttack())) {
            cardJson.setAttack("");
        }
        //生命
        if (StringUtils.isBlank(cardJson.getHealth())) {
            cardJson.setHealth("");
        }


    }

    /**
     * 查询
     *
     * @param cookie
     * @param page
     * @param cardSet
     */
    private static String findJsonCard(String cookie, String cardClass, String page, String cardSet) {
        String url = "https://hs.blizzard.cn/action/cards/query";
        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("cardClass="+"druid");
        urlParam.append("&cardClass=" + cardClass +
                "&p=" + page +
                "&standard=1" +
                "&keywords=" +
                "&t=1596363737564" +
                "&cardSet=" + cardSet);

        //        System.out.println(rs);
//        System.out.println("请求url:"+url+JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
//        System.out.println("myTradeRs:"+rs);
//        System.out.println("fundTradeList:" + JSON.toJSONString(fundTradeList));
        return rs;
    }
}
