/**
 * Copyright 2022 json.cn
 */
package live.car.dto;
import java.util.List;

/**
 * Auto-generated: 2022-09-03 10:25:15
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class YcCar {

    private String status;
    private String message;
    private List<YcCarData> data;
    private String ercd;
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(List<YcCarData> data) {
        this.data = data;
    }
    public List<YcCarData> getData() {
        return data;
    }

    public void setErcd(String ercd) {
        this.ercd = ercd;
    }
    public String getErcd() {
        return ercd;
    }

}