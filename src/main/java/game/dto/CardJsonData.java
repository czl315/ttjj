package game.dto;

import java.util.List;

/**
 * 卡牌json父类
 * @author chenzhilong
 * @date 2020/8/1
 */
public class CardJsonData {
    /**
     *
     */
    private List<CardJsonDarkMoon> list;

    public List<CardJsonDarkMoon> getList() {
        return list;
    }

    public void setList(List<CardJsonDarkMoon> list) {
        this.list = list;
    }
}
