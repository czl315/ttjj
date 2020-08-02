package game;

import com.alibaba.fastjson.JSON;
import game.dto.Card;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LscsHtmlUtil {

    public static void main(String[] args) {
        String str = "";
        str="<div class=\"cards_box_wraper clearFix\">\n" +
                "<div class=\"cards_place\">\n" +
                "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59727\" data-params=\",59727,7,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/1c7010bfb9f461c7265ce0ff759286a2c204e49dc435a513612363de46a491ea.png\">\n" +
                "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                "<img class=\"card_img\" alt=\"灵魂学家玛丽希亚\" src=\"https://hearthstone.nosdn.127.net/hearthstone/1c7010bfb9f461c7265ce0ff759286a2c204e49dc435a513612363de46a491ea.png\" style=\"display: inline;\"></a>\n" +
                "<div class=\"cards_detail_wraper\">\n" +
                "<div class=\"cards_detail\">\n" +
                "<h4 class=\"cardName\">灵魂学家玛丽希亚</h4>\n" +
                "<p class=\"cardFlavorText\">珍爱生命，别说她的研究领域是“软科学”。</p>\n" +
                "<p class=\"cardText\">" +
                "<b>战吼：</b>你的牌库中每有一张灵魂残片，召唤一个3/3并具有<b>突袭</b>的灵魂。 <i>（ ）</i>" +
                "</p>\n" +
                "<ul class=\"attributesList\">\n" +
                "<li>类型：<span>随从</span>\n" +
                "</li>\n" +
                "<li>稀有度：<span>传说</span>\n" +
                "</li>\n" +
                "<li>系列：<span>《通灵学园》</span>\n" +
                "</li>\n" +
                "<li>职业：<span>术士 , 恶魔猎手</span>\n" +
                "</li>\n" +
                "<li>制作费用：<span>1600 / 3200 (金卡)</span>\n" +
                "</li>\n" +
                "<li>分解的价值：<span>400 / 1600 (金卡)</span>\n" +
                "</li>\n" +
                "<li>画家：<span class=\"Artist\">Steven Prescott</span>\n" +
                "</li>\n" +
                "<li>可收集</li>\n" +
                "</ul>\n" +
                "<div class=\"keywordsView\">\n" +
                "<p>了解更多：</p>\n" +
                "<div class=\"keywordsList\">\n" +
                "<a href=\"javascript:;\" data-text=\"当你从你的手牌中使用它的时候，它会产生一些效果。\">战吼</a>\n" +
                "<a href=\"javascript:;\" data-text=\"可以立即攻击随从。\">突袭</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"relatedCardsView\">\n" +
                "<p>衍生卡牌：</p>\n" +
                "<div class=\"relatedCardsList\">\n" +
                "<a href=\"javascript:;\" data-image=\"https://hearthstone.nosdn.127.net/hearthstone/66276c02e69a396cb8dc0df050bc9c62f6e11614c9d4e467074f8afd4549f986.png\">被释放的灵魂</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/1c7010bfb9f461c7265ce0ff759286a2c204e49dc435a513612363de46a491ea.png\" href=\"javascript:void(0);\"></a>\n" +
                "</div>\n" +
                "<div class=\"cards_place\">\n" +
                "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"60164\" data-params=\"demonhunter,60164,7,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/fb890103d50679e254e06e6b4e2374429d45970ceb5c1d89ebf856a6bef5e9bf.png\">\n" +
                "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                "<img class=\"card_img\" alt=\"邪能护卫\" src=\"https://hearthstone.nosdn.127.net/hearthstone/fb890103d50679e254e06e6b4e2374429d45970ceb5c1d89ebf856a6bef5e9bf.png\" style=\"display: inline;\"></a>\n" +
                "<div class=\"cards_detail_wraper\">\n" +
                "<div class=\"cards_detail\">\n" +
                "<h4 class=\"cardName\">邪能护卫</h4>\n" +
                "<p class=\"cardFlavorText\">什么东西能保护恶魔的蹄子？邪能。</p>\n" +
                "<p class=\"cardText\">召唤三个1/2并具有<b>嘲讽</b>的恶魔。每当一个友方随从死亡，该牌的法力值消耗便减少（1）点。</p>\n" +
                "<ul class=\"attributesList\">\n" +
                "<li>类型：<span>法术</span>\n" +
                "</li>\n" +
                "<li>稀有度：<span>普通</span>\n" +
                "</li>\n" +
                "<li>系列：<span>《通灵学园》</span>\n" +
                "</li>\n" +
                "<li>职业：<span>恶魔猎手</span>\n" +
                "</li>\n" +
                "<li>制作费用：<span>40 / 400 (金卡)</span>\n" +
                "</li>\n" +
                "<li>分解的价值：<span>5 / 50 (金卡)</span>\n" +
                "</li>\n" +
                "<li>画家：<span class=\"Artist\">Anton Zemskov</span>\n" +
                "</li>\n" +
                "<li>可收集</li>\n" +
                "</ul>\n" +
                "<div class=\"keywordsView\">\n" +
                "<p>了解更多：</p>\n" +
                "<div class=\"keywordsList\">\n" +
                "<a href=\"javascript:;\" data-text=\"敌人必须攻击该随从。\">嘲讽</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"relatedCardsView\">\n" +
                "<p>衍生卡牌：</p>\n" +
                "<div class=\"relatedCardsList\">\n" +
                "<a href=\"javascript:;\" data-image=\"https://hearthstone.nosdn.127.net/hearthstone/c34502d677f2b96f65adef3b622333f1c9069be181db2c9a8acd04501c9437d9.png\">食魂地狱犬</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/fb890103d50679e254e06e6b4e2374429d45970ceb5c1d89ebf856a6bef5e9bf.png\" href=\"javascript:void(0);\" style=\"bottom:0;\"></a>\n" +
                "</div>\n" +
                "<div class=\"cards_place\">\n" +
                "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"60143\" data-params=\"demonhunter,60143,9,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/1736e9a6df1af7944be92691129846d2635c5a0b5640ebcdb2e2ea642c79d828.png\">\n" +
                "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                "<img class=\"card_img\" alt=\"上古虚空恶犬\" src=\"https://hearthstone.nosdn.127.net/hearthstone/1736e9a6df1af7944be92691129846d2635c5a0b5640ebcdb2e2ea642c79d828.png\" style=\"display: inline;\"></a>\n" +
                "<div class=\"cards_detail_wraper\">\n" +
                "<div class=\"cards_detail\">\n" +
                "<h4 class=\"cardName\">上古虚空恶犬</h4>\n" +
                "<p class=\"cardFlavorText\">我的1/1的虚空狗狗怎么这样了？还有，刚才那伙人呢？！</p>\n" +
                "<p class=\"cardText\">在你的回合结束时，从所有敌方随从处偷取1点攻击力和生命值。</p>\n" +
                "<ul class=\"attributesList\">\n" +
                "<li>类型：<span>随从</span>\n" +
                "</li>\n" +
                "<li>稀有度：<span>史诗</span>\n" +
                "</li>\n" +
                "<li>系列：<span>《通灵学园》</span>\n" +
                "</li>\n" +
                "<li>职业：<span>恶魔猎手</span>\n" +
                "</li>\n" +
                "<li>制作费用：<span>400 / 1600 (金卡)</span>\n" +
                "</li>\n" +
                "<li>分解的价值：<span>100 / 400 (金卡)</span>\n" +
                "</li>\n" +
                "<li>画家：<span class=\"Artist\">Jaemin Kim</span>\n" +
                "</li>\n" +
                "<li>可收集</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "</div>\n" +
                "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/1736e9a6df1af7944be92691129846d2635c5a0b5640ebcdb2e2ea642c79d828.png\" href=\"javascript:void(0);\"></a>\n" +
                "</div>\n" +
                "</div>\n";
        

        String[] cards = str.split("<div class=\"cards_place\">");
        List<Card> cardList = new ArrayList<Card>();
        for (String cardStr : cards) {
//            System.out.println(cardStr);//输出每行数据
            Card card = new Card();
            String[] cardTrs = cardStr.split("\n");
            for (String cardTr : cardTrs) {
                String tempStart = "<h4 class=\"cardName\">";
                String tempEnd = "</h4>";
                int tempStartLength = 21;
                if (cardTr.startsWith(tempStart)) {
//                    System.out.println(cardTr);
                    card.setCardName(cardTr.substring(cardTr.indexOf(tempStart)+tempStartLength, cardTr.indexOf(tempEnd)));
                }
                tempStart = "<p class=\"cardText\">";
                tempEnd = "</p>";
                tempStartLength = 20;
                if (cardTr.startsWith(tempStart)) {
//                    System.out.println(cardTr);
                    card.setCardText(cardTr.substring(cardTr.indexOf(tempStart)+tempStartLength, cardTr.indexOf(tempEnd)));
                }
                tempStart = "<li>类型：<span>";
                tempEnd = "</span>";
                tempStartLength = 13;
                if (cardTr.startsWith(tempStart)) {
//                                        System.out.println(cardTr);
                    card.setType(cardTr.substring(cardTr.indexOf(tempStart)+tempStartLength, cardTr.indexOf(tempEnd)));
                }
                tempStart = "<li>稀有度：<span>";
                tempEnd = "</span>";
                tempStartLength = 14;
                if (cardTr.startsWith(tempStart)) {
                    card.setRarity(cardTr.substring(cardTr.indexOf(tempStart)+tempStartLength, cardTr.indexOf(tempEnd)));
                }
                tempStart = "<li>职业：<span>";
                tempEnd = "</span>";
                tempStartLength = 13;
                if (cardTr.startsWith(tempStart)) {
                    card.setProfession(cardTr.substring(cardTr.indexOf(tempStart)+tempStartLength, cardTr.indexOf(tempEnd)));
                }
            }
            cardList.add(card);
        }
        for (Card cardDto : cardList) {
//            System.out.println(JSON.toJSON(cardDto));
            if(null== cardDto.getCardName()){
                continue;
            }
            System.out.println(cardDto.getCardName()+"\t"+"\t"+cardDto.getCardText()+"\t"+"\t"+cardDto.getType()+"\t"+"\t"+"\t"+cardDto.getProfession()+"\t"
                    +"\t"+cardDto.getRarity()+"\t"+"通灵学院");
        }

    }
}
