package ttjj.dto;

import java.util.List;

/**
 * http调用返回结果：核心题材父类：包含核心题材、所属板块
 * @author chenzhilong
 * @date 2022/1/8
 */
public class HttpRsHxtc {
    private List<HttpRsHxtcSsbk> ssbk;
    private List<HttpRsHxtcHxtc> hxtc;

    public List<HttpRsHxtcSsbk> getSsbk() {
        return ssbk;
    }

    public void setSsbk(List<HttpRsHxtcSsbk> ssbk) {
        this.ssbk = ssbk;
    }

    public List<HttpRsHxtcHxtc> getHxtc() {
        return hxtc;
    }

    public void setHxtc(List<HttpRsHxtcHxtc> hxtc) {
        this.hxtc = hxtc;
    }
}
