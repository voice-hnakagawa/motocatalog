package ooo.klae.sample.motocatalog.mappers;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ooo.klae.sample.motocatalog.beans.Motorcycle;

@Mapper
public interface MotorcycleMapper {

    

    /**
     * 
     * モーターサイクルの全件取得
     * @return モーターサイクルのリスト
     */
    public List<Motorcycle> selectAll();
    
}
