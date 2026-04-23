package ooo.klae.sample.motocatalog.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ooo.klae.sample.motocatalog.beans.Motorcycle;
import ooo.klae.sample.motocatalog.mappers.MotorcycleMapper;

import ooo.klae.sample.motocatalog.beans.Brand;
import ooo.klae.sample.motocatalog.mappers.BrandMapper;
import ooo.klae.sample.motocatalog.beans.SearchForm;

import org.springframework.dao.OptimisticLockingFailureException;

@Service
public class MotosService {
    

    @Autowired
    MotorcycleMapper motorcycleMapper;

    @Autowired
    BrandMapper brandMapper;

    public List<Motorcycle> getMotos(SearchForm condition) {
        return motorcycleMapper.selectByCondition(condition);
    }

    public Motorcycle getMotos(int motoNo) {
        return motorcycleMapper.selectByPK(motoNo);
    }

    public List<Brand> getBrands() {
        return brandMapper.selectAll();
    }

    /**
     *  バイク情報を更新する
     * @param motorcycle 更新するバイク情報
     * @return 更新件数
     */
    public int save(Motorcycle motorcycle) {
        int cnt = motorcycleMapper.update(motorcycle);
        if (cnt == 0) {
            throw new OptimisticLockingFailureException("楽観的なロックに失敗しました。データは他のユーザーによって更新されました。");
        }
        return cnt;
    }
}
