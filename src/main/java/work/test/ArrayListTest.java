package work.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayListTest {
    public static void main(String[] args) {
        List<StarExchangeCodeVo> rs = test01();
        System.out.println(JSON.toJSONString(rs));
    }

    static List<StarExchangeCodeVo> test01(){
        List<StarExchangeCodeVo> rs = new ArrayList<StarExchangeCodeVo>();
        Set<String> rsSet = new HashSet<>();
        rsSet.add("aaa");
        rsSet.add("bbb");
        StarExchangeCodeVo starExchangeCodeVo = new StarExchangeCodeVo();
        for (String code : rsSet) {
//            starExchangeCodeVo = new StarExchangeCodeVo();
            starExchangeCodeVo.setExchangeCode(code);
            rs.add(starExchangeCodeVo);//数组中持有对象的引用，如果不创建，都是同一个对象
        }
        return rs;
    }

    static class StarExchangeCodeVo {

        private String pin;

        private String exchangeCode;

        private String createTime;

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getExchangeCode() {
            return exchangeCode;
        }

        public void setExchangeCode(String exchangeCode) {
            this.exchangeCode = exchangeCode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public StarExchangeCodeVo(){

        }

        public StarExchangeCodeVo(String pin, String exchangeCode, String createTime) {
            this.pin = pin;
            this.exchangeCode = exchangeCode;
            this.createTime = createTime;
        }
    }
}
