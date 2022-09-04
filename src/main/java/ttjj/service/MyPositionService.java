package ttjj.service;

import ttjj.dao.FupanPositionDao;
import ttjj.db.AssetPositionDb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的仓位
 *
 * @author Administrator
 * @date 2022-09-05 00:02
 */
public class MyPositionService {
    /**
     * 我的仓位
     * @param date 日期
     * @return 我的持仓
     */
    public static Map<String, String> listMyPositionByDate(String date) {
        List<AssetPositionDb> rs = FupanPositionDao.listMyPositionByDate(date);//我的持仓
        Map<String, String> mapMyPostion = new HashMap<>();
        for (AssetPositionDb assetPositionDb : rs) {
            mapMyPostion.put(assetPositionDb.getZqdm(), assetPositionDb.getZqmc());
        }
        return mapMyPostion;
    }
}
