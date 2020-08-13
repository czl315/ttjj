package work.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author one3c-wangkui3
 * @Description: 我的助力人vo
 * @Date 2019/3/15
 * @Time 下午4:10
 */
public class SupporterVO {

    private String nickName;{}
    private String imgUrl;
    private String supportData;

    public SupporterVO(String nickName, String imgUrl) {
        this.nickName = nickName;
        this.imgUrl = imgUrl;
    }
}


