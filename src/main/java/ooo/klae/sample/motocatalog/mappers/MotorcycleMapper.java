package ooo.klae.sample.motocatalog.mappers;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import ooo.klae.sample.motocatalog.beans.Motorcycle;
import ooo.klae.sample.motocatalog.beans.SearchCondition;

@Mapper
public interface MotorcycleMapper {

    

    /**
     * 
     * モーターサイクルの全件取得
     * @return モーターサイクルのリスト
     */
    public List<Motorcycle> selectByCondition(SearchCondition condition);
    public Motorcycle selectByPK(int motoNo);

    @Update("UPDATE m_motorcycle SET moto_name = #{motoName}, seat_height = #{seatHeight}, cylinder = #{cylinder}, cooling = #{cooling}, price = #{price}, comment = #{comment}, brand_id = #{brandId.brandId}, version = version + 1, ins_dt = #{insDt}, upd_dt = CURRENT_TIMESTAMP WHERE moto_no = #{motoNo} AND version = #{version}")
    public int update(Motorcycle motorcycle);
    
}
