package game;

import com.alibaba.fastjson.JSON;
import game.dto.Card;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LscsHtmlUtil {

    public static void main(String[] args) {
        String str = "";
        {
            str = "<div class=\"cards_box_wraper clearFix\">\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59606\" data-params=\"demonhunter,59606,1,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/0234d81cd6376537551079294a9994bd0539d3e4cdc4f92b1ef3dc8cc221fcc4.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"二段跳\" src=\"https://hearthstone.nosdn.127.net/hearthstone/0234d81cd6376537551079294a9994bd0539d3e4cdc4f92b1ef3dc8cc221fcc4.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">二段跳</h4>\n" +
                    "<p class=\"cardFlavorText\">各种速通的关键技能。</p>\n" +
                    "<p class=\"cardText\">从你的牌库中抽一张<b>流放</b>牌。</p>\n" +
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
                    "<li>画家：<span class=\"Artist\">A.J. Nazzaro</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "<div class=\"keywordsView\">\n" +
                    "<p>了解更多：</p>\n" +
                    "<div class=\"keywordsList\">\n" +
                    "<a href=\"javascript:;\" data-text=\"在手牌中的最左或最右侧使用时触发的额外效果。\">流放</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/0234d81cd6376537551079294a9994bd0539d3e4cdc4f92b1ef3dc8cc221fcc4.png\" href=\"javascript:void(0);\" style=\"bottom:0;\"></a>\n" +
                    "</div>\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59233\" data-params=\",59233,1,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/39e202ebaf9facff2dff28c0663e47292a19cbcd484a31687fae776f5d77253d.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"引月长弓\" src=\"https://hearthstone.nosdn.127.net/hearthstone/39e202ebaf9facff2dff28c0663e47292a19cbcd484a31687fae776f5d77253d.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">引月长弓</h4>\n" +
                    "<p class=\"cardFlavorText\">一支穿云箭，千军万马来相见。</p>\n" +
                    "<p class=\"cardText\">在你的英雄攻击一个随从后，你的所有随从也会攻击该随从。</p>\n" +
                    "<ul class=\"attributesList\">\n" +
                    "<li>类型：<span>武器</span>\n" +
                    "</li>\n" +
                    "<li>稀有度：<span>史诗</span>\n" +
                    "</li>\n" +
                    "<li>系列：<span>《通灵学园》</span>\n" +
                    "</li>\n" +
                    "<li>职业：<span>恶魔猎手 , 猎人</span>\n" +
                    "</li>\n" +
                    "<li>制作费用：<span>400 / 1600 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>分解的价值：<span>100 / 400 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>画家：<span class=\"Artist\">Kagi</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/39e202ebaf9facff2dff28c0663e47292a19cbcd484a31687fae776f5d77253d.png\" href=\"javascript:void(0);\"></a>\n" +
                    "</div>\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59394\" data-params=\",59394,1,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/51a84e6977f8a3820b6dc08b6cdca984931f3b5676d7a751ea5d5fc475bdd6bd.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"恶魔伙伴\" src=\"https://hearthstone.nosdn.127.net/hearthstone/51a84e6977f8a3820b6dc08b6cdca984931f3b5676d7a751ea5d5fc475bdd6bd.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">恶魔伙伴</h4>\n" +
                    "<p class=\"cardFlavorText\">养了宠物之后，有时就会感觉自己在和恶魔作伴。</p>\n" +
                    "<p class=\"cardText\">随机召唤一个恶魔伙伴。</p>\n" +
                    "<ul class=\"attributesList\">\n" +
                    "<li>类型：<span>法术</span>\n" +
                    "</li>\n" +
                    "<li>稀有度：<span>稀有</span>\n" +
                    "</li>\n" +
                    "<li>系列：<span>《通灵学园》</span>\n" +
                    "</li>\n" +
                    "<li>职业：<span>恶魔猎手 , 猎人</span>\n" +
                    "</li>\n" +
                    "<li>制作费用：<span>100 / 800 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>分解的价值：<span>20 / 100 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>画家：<span class=\"Artist\">Zoltan Boros</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "<div class=\"relatedCardsView\">\n" +
                    "<p>衍生卡牌：</p>\n" +
                    "<div class=\"relatedCardsList\">\n" +
                    "<a href=\"javascript:;\" data-image=\"https://hearthstone.nosdn.127.net/hearthstone/7bcfbac2e08dc112820d80cc8d5bf86261e3dba62fd9849e19fc051b8edca508.png\">莎米</a>\n" +
                    "<a href=\"javascript:;\" data-image=\"https://hearthstone.nosdn.127.net/hearthstone/b66a3dd0f38e50db846b08d948037c0aa1432ea34bf0436f8ffff1a1cabd34aa.png\">弗霍</a>\n" +
                    "<a href=\"javascript:;\" data-image=\"https://hearthstone.nosdn.127.net/hearthstone/3b88efba8c3c31199bfd22d7099412897379141e949a2f4e93f6e53ea6ec1dc8.png\">克欧雷</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/51a84e6977f8a3820b6dc08b6cdca984931f3b5676d7a751ea5d5fc475bdd6bd.png\" href=\"javascript:void(0);\" style=\"bottom:0;\"></a>\n" +
                    "</div>\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59724\" data-params=\",59724,1,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/a9774bc284abca4dded5efd57533d78928e4d48ba871962752a7ced2ea8c6d8e.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"精魂狱卒\" src=\"https://hearthstone.nosdn.127.net/hearthstone/a9774bc284abca4dded5efd57533d78928e4d48ba871962752a7ced2ea8c6d8e.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">精魂狱卒</h4>\n" +
                    "<p class=\"cardFlavorText\">“当然了，它们确实被关在你的牌库里。但思想，才更像是真正的监牢。”</p>\n" +
                    "<p class=\"cardText\"><b>战吼：</b>将两张灵魂残片洗入你的牌库。</p>\n" +
                    "<ul class=\"attributesList\">\n" +
                    "<li>类型：<span>随从</span>\n" +
                    "</li>\n" +
                    "<li>稀有度：<span>普通</span>\n" +
                    "</li>\n" +
                    "<li>系列：<span>《通灵学园》</span>\n" +
                    "</li>\n" +
                    "<li>职业：<span>术士 , 恶魔猎手</span>\n" +
                    "</li>\n" +
                    "<li>制作费用：<span>40 / 400 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>分解的价值：<span>5 / 50 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>画家：<span class=\"Artist\">Ben Olson</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "<div class=\"keywordsView\">\n" +
                    "<p>了解更多：</p>\n" +
                    "<div class=\"keywordsList\">\n" +
                    "<a href=\"javascript:;\" data-text=\"当你从你的手牌中使用它的时候，它会产生一些效果。\">战吼</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/a9774bc284abca4dded5efd57533d78928e4d48ba871962752a7ced2ea8c6d8e.png\" href=\"javascript:void(0);\"></a>\n" +
                    "</div>\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59726\" data-params=\",59726,1,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/855377233efdda337e19e85725fc5cba931ec0db911aa953b947d658f64e573b.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"邪能学说\" src=\"https://hearthstone.nosdn.127.net/hearthstone/855377233efdda337e19e85725fc5cba931ec0db911aa953b947d658f64e573b.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">邪能学说</h4>\n" +
                    "<p class=\"cardFlavorText\">不要再念邪音了，要扣钱的。</p>\n" +
                    "<p class=\"cardText\">复制你手牌中法力值消耗最低的恶魔牌。<b>流放：</b>使这两张恶魔牌获得+1/+1。</p>\n" +
                    "<ul class=\"attributesList\">\n" +
                    "<li>类型：<span>法术</span>\n" +
                    "</li>\n" +
                    "<li>稀有度：<span>史诗</span>\n" +
                    "</li>\n" +
                    "<li>系列：<span>《通灵学园》</span>\n" +
                    "</li>\n" +
                    "<li>职业：<span>术士 , 恶魔猎手</span>\n" +
                    "</li>\n" +
                    "<li>制作费用：<span>400 / 1600 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>分解的价值：<span>100 / 400 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>画家：<span class=\"Artist\">Adam Byrne</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "<div class=\"keywordsView\">\n" +
                    "<p>了解更多：</p>\n" +
                    "<div class=\"keywordsList\">\n" +
                    "<a href=\"javascript:;\" data-text=\"在手牌中的最左或最右侧使用时触发的额外效果。\">流放</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/855377233efdda337e19e85725fc5cba931ec0db911aa953b947d658f64e573b.png\" href=\"javascript:void(0);\" style=\"bottom:0;\"></a>\n" +
                    "</div>\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59725\" data-params=\",59725,2,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/c7011e886da71a5a6940bd6491aa437716ac147c6260917c1c5fe1e25a94dde6.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"灵魂剥离\" src=\"https://hearthstone.nosdn.127.net/hearthstone/c7011e886da71a5a6940bd6491aa437716ac147c6260917c1c5fe1e25a94dde6.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">灵魂剥离</h4>\n" +
                    "<p class=\"cardFlavorText\">“再往边上靠一点，再靠一点。”</p>\n" +
                    "<p class=\"cardText\">对一个随从造成 3点伤害。将两张灵魂残片洗入你的 牌库。</p>\n" +
                    "<ul class=\"attributesList\">\n" +
                    "<li>类型：<span>法术</span>\n" +
                    "</li>\n" +
                    "<li>稀有度：<span>稀有</span>\n" +
                    "</li>\n" +
                    "<li>系列：<span>《通灵学园》</span>\n" +
                    "</li>\n" +
                    "<li>职业：<span>术士 , 恶魔猎手</span>\n" +
                    "</li>\n" +
                    "<li>制作费用：<span>100 / 800 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>分解的价值：<span>20 / 100 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>画家：<span class=\"Artist\">Dave Allsop</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/c7011e886da71a5a6940bd6491aa437716ac147c6260917c1c5fe1e25a94dde6.png\" href=\"javascript:void(0);\" style=\"bottom:0;\"></a>\n" +
                    "</div>\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"60151\" data-params=\"demonhunter,60151,3,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/9bedfb27ebd1a626836650bcfe7e4df34cf446a0bc5c118267b7ff939f7175a8.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"残片震爆秘术师\" src=\"https://hearthstone.nosdn.127.net/hearthstone/9bedfb27ebd1a626836650bcfe7e4df34cf446a0bc5c118267b7ff939f7175a8.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">残片震爆秘术师</h4>\n" +
                    "<p class=\"cardFlavorText\">感受这震撼灵魂的伤害吧。</p>\n" +
                    "<p class=\"cardText\">战吼：</b>摧毁一张你牌库中的灵魂残片，对所有其他随从造成3点伤害。</p>\n" +
                    "<ul class=\"attributesList\">\n" +
                    "<li>类型：<span>随从</span>\n" +
                    "</li>\n" +
                    "<li>稀有度：<span>稀有</span>\n" +
                    "</li>\n" +
                    "<li>系列：<span>《通灵学园》</span>\n" +
                    "</li>\n" +
                    "<li>职业：<span>恶魔猎手</span>\n" +
                    "</li>\n" +
                    "<li>制作费用：<span>100 / 800 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>分解的价值：<span>20 / 100 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>画家：<span class=\"Artist\">Vladimir Kafanov</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "<div class=\"keywordsView\">\n" +
                    "<p>了解更多：</p>\n" +
                    "<div class=\"keywordsList\">\n" +
                    "<a href=\"javascript:;\" data-text=\"当你从你的手牌中使用它的时候，它会产生一些效果。\">战吼</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/9bedfb27ebd1a626836650bcfe7e4df34cf446a0bc5c118267b7ff939f7175a8.png\" href=\"javascript:void(0);\"></a>\n" +
                    "</div>\n" +
                    "<div class=\"cards_place\">\n" +
                    "<a class=\"cards_link\" data-cardset=\"scholomance-academy\" data-id=\"59230\" data-params=\"demonhunter,59230,3,0\" href=\"javascript:void(0);\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/4c62e9c98d4435b36e125ff15ff2673f65738bd2bb5792b37d7f02fdd577cd7e.png\">\n" +
                    "<img class=\"imgload\" src=\"https://hearthstone.nosdn.127.net/3/cards/cards_loading.gif\" style=\"display: none;\">\n" +
                    "<img class=\"card_img\" alt=\"法师猎手\" src=\"https://hearthstone.nosdn.127.net/hearthstone/4c62e9c98d4435b36e125ff15ff2673f65738bd2bb5792b37d7f02fdd577cd7e.png\" style=\"display: inline;\"></a>\n" +
                    "<div class=\"cards_detail_wraper\">\n" +
                    "<div class=\"cards_detail\">\n" +
                    "<h4 class=\"cardName\">法师猎手</h4>\n" +
                    "<p class=\"cardFlavorText\">奔袭如火。讨厌话多。喜欢紫色。</p>\n" +
                    "<p class=\"cardText\"><b>突袭</b> 每当该随从攻击一个随从时，将其<b>沉默</b>。</p>\n" +
                    "<ul class=\"attributesList\">\n" +
                    "<li>类型：<span>随从</span>\n" +
                    "</li>\n" +
                    "<li>稀有度：<span>稀有</span>\n" +
                    "</li>\n" +
                    "<li>系列：<span>《通灵学园》</span>\n" +
                    "</li>\n" +
                    "<li>职业：<span>恶魔猎手</span>\n" +
                    "</li>\n" +
                    "<li>制作费用：<span>100 / 800 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>分解的价值：<span>20 / 100 (金卡)</span>\n" +
                    "</li>\n" +
                    "<li>画家：<span class=\"Artist\">Dave Allsop</span>\n" +
                    "</li>\n" +
                    "<li>可收集</li>\n" +
                    "</ul>\n" +
                    "<div class=\"keywordsView\">\n" +
                    "<p>了解更多：</p>\n" +
                    "<div class=\"keywordsList\">\n" +
                    "<a href=\"javascript:;\" data-text=\"移除所有卡牌描述和强化。\">沉默</a>\n" +
                    "<a href=\"javascript:;\" data-text=\"可以立即攻击随从。\">突袭</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<a class=\"cards_zoom\" rel=\"https://hearthstone.nosdn.127.net/hearthstone/4c62e9c98d4435b36e125ff15ff2673f65738bd2bb5792b37d7f02fdd577cd7e.png\" href=\"javascript:void(0);\"></a>\n" +
                    "</div>\n" +
                    "</div>\n";

        }

        String[] cards = str.split("<div class=\"cards_place\">");
        List<Card> cardList = new ArrayList<Card>();
        for (String cardStr : cards) {
//            System.out.println(card);//输出每行数据
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
