package ttjj.dto;

import java.util.List;

/**
 * 资产持仓-父类
 * @author chenzhilong
 * @date 2021/3/18
 */
public class Asset {
    private String Djzj;
    private String Dryk;
    private String Kqzj;
    private String Kyzj;
    private String Ljyk;
    private String Money_type;
    private String RMBZzc;
    private String Zjye;
    private String Zxsz;
    private String Zzc;
    private List<AssetPosition> positions;

    public String getDjzj() {
        return Djzj;
    }

    public void setDjzj(String djzj) {
        Djzj = djzj;
    }

    public String getDryk() {
        return Dryk;
    }

    public void setDryk(String dryk) {
        Dryk = dryk;
    }

    public String getKqzj() {
        return Kqzj;
    }

    public void setKqzj(String kqzj) {
        Kqzj = kqzj;
    }

    public String getKyzj() {
        return Kyzj;
    }

    public void setKyzj(String kyzj) {
        Kyzj = kyzj;
    }

    public String getLjyk() {
        return Ljyk;
    }

    public void setLjyk(String ljyk) {
        Ljyk = ljyk;
    }

    public String getMoney_type() {
        return Money_type;
    }

    public void setMoney_type(String money_type) {
        Money_type = money_type;
    }

    public String getRMBZzc() {
        return RMBZzc;
    }

    public void setRMBZzc(String RMBZzc) {
        this.RMBZzc = RMBZzc;
    }

    public String getZjye() {
        return Zjye;
    }

    public void setZjye(String zjye) {
        Zjye = zjye;
    }

    public String getZxsz() {
        return Zxsz;
    }

    public void setZxsz(String zxsz) {
        Zxsz = zxsz;
    }

    public String getZzc() {
        return Zzc;
    }

    public void setZzc(String zzc) {
        Zzc = zzc;
    }

    public List<AssetPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<AssetPosition> positions) {
        this.positions = positions;
    }
}
