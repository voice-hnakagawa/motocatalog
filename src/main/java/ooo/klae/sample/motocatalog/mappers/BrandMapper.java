package ooo.klae.sample.motocatalog.mappers;

import ooo.klae.sample.motocatalog.beans.Brand;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandMapper {
    public List<Brand> selectAll();
}
