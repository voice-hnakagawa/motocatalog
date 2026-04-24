package ooo.klae.sample.motocatalog.mappers;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import ooo.klae.sample.motocatalog.beans.Motorcycle;
import ooo.klae.sample.motocatalog.beans.SearchForm;

@Mapper
public interface MotorcycleMapper {

    

    /**
     * 
     * モーターサイクルの全件取得
     * @return モーターサイクルのリスト
     */
    public List<Motorcycle> selectByCondition(SearchForm condition);
    public Motorcycle selectByPK(int motoNo);

    /**
     * モーターサイクルの更新
     * @param motorcycle 更新するモーターサイクルの情報
     * @return 更新件数
     */
    @Update("UPDATE m_motorcycle SET moto_name = #{motoName}, seat_height = #{seatHeight}, cylinder = #{cylinder}, cooling = #{cooling}, price = #{price}, comment = #{comment}, brand_id = #{brandId.brandId}, version = version + 1, ins_dt = #{insDt}, upd_dt = CURRENT_TIMESTAMP WHERE moto_no = #{motoNo} AND version = #{version}")
    public int update(Motorcycle motorcycle);

    /**
     * 新しいバイク番号を採番する
     * @return 新しいバイク番号
     */
    public Integer selectNewMotoNo();

    /**
     * バイク情報を登録する
     * @param motorcycle 登録するバイク情報
     * @return 登録件数
     */
    public int insert(Motorcycle motorcycle);
    
    /**
     * モーターサイクルの削除
     * @param motorcycle 削除するモーターサイクルの情報
     * @return 削除件数
     */
    @Delete("DELETE FROM m_motorcycle WHERE moto_no = #{motoNo} AND version = #{version}")
    public int delete(Motorcycle motorcycle);
}
