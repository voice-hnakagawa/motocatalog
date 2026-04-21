package ooo.klae.sample.motocatalog.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ooo.klae.sample.motocatalog.beans.Motorcycle;
import ooo.klae.sample.motocatalog.mappers.MotorcycleMapper;

import ooo.klae.sample.motocatalog.beans.Brand;
import ooo.klae.sample.motocatalog.mappers.BrandMapper;

@Service
public class MotosService {
    

    @Autowired
    MotorcycleMapper motorcycleMapper;

    @Autowired
    BrandMapper brandMapper;

    public List<Motorcycle> getMotos() {
        return motorcycleMapper.selectAll();
    }

    public List<Brand> getBrands() {
        return brandMapper.selectAll();
    }
}
