package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.Fupan;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public interface FupanMapper {
    @Select("select * from fupan")
    List<Fupan> getAllRupan();

    @Select("select * from fupan where id= #{id}")
    public Fupan getUserByID(int id);

    @Update({"<script>",
            "update fupan",
            "  <set>",
            "    <if test='rtHs300 != null'>rt_hs300=#{rtHs300},</if>",
            "    <if test='cjeHs300 != null'>cje_hs300=#{cjeHs300},</if>",
            "    <if test='ptHs300 != null'>pt_hs300=#{ptHs300}</if>",
            "  </set>",
            "where CODE=#{code} AND fupan.period=#{period} AND fupan.TYPE=#{type}",
            "</script>"})
    void updateFupan(Fupan fupan);

}
