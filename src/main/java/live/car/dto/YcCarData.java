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
public class YcCarData {

    private String name;
    private List<YcCarItems> items;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setItems(List<YcCarItems> items) {
        this.items = items;
    }
    public List<YcCarItems> getItems() {
        return items;
    }

}