package game.dto;

import java.util.List;

/**
 * 卡牌json父类
 * @author chenzhilong
 * @date 2020/8/1
 */
public class CardJsonPtDarkMoon {
    /**
     *
     */
    private String msg;
    /**
     *
     */
    private String status;
    /**
     *
     */
    private CardJsonData data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CardJsonData getData() {
        return data;
    }

    public void setData(CardJsonData data) {
        this.data = data;
    }
}
