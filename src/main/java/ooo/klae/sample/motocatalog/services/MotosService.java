package ooo.klae.sample.motocatalog.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ooo.klae.sample.motocatalog.beans.Motorcycle;
import ooo.klae.sample.motocatalog.forms.SearchForm;
import ooo.klae.sample.motocatalog.mappers.MotorcycleMapper;

import ooo.klae.sample.motocatalog.beans.Brand;
import ooo.klae.sample.motocatalog.mappers.BrandMapper;

import java.util.Locale;

import org.springframework.dao.OptimisticLockingFailureException;

@Service
public class MotosService {

    @Autowired
    MessageSource messageSource;

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

    public int save(Motorcycle motorcycle) {
        if (motorcycle.getMotoNo() == 0) {
            return this.add(motorcycle);
        } else {
            return this.update(motorcycle);
        }
    }

    /**
     * バイク情報を更新する
     * 
     * @param motorcycle 更新するバイク情報
     * @return 更新件数
     */
    @Transactional
    private int update(Motorcycle motorcycle) {
        int cnt = motorcycleMapper.update(motorcycle);
        if (cnt == 0) {
            throw new OptimisticLockingFailureException(
                    messageSource.getMessage("error.OptimisticLockingFailure", null, Locale.getDefault()));
        }

        // 2件以上更新された場合は、データの整合性に問題があるため例外をスローする
        if (cnt > 1) {
            throw new RuntimeException(
                    messageSource.getMessage("error.Runtime", new String[] { "2件以上更新されました" }, Locale.getDefault()));
        }

        return cnt;
    }

    /**
     * バイク情報を登録する
     * 
     * @param motorcycle 登録するバイク情報
     * @return 登録件数
     */
    @Transactional
    private int add(Motorcycle motorcycle) {
        int motoNo = motorcycleMapper.selectNewMotoNo();
        motorcycle.setMotoNo(motoNo);
        int cnt = motorcycleMapper.insert(motorcycle);
        if (cnt == 0) {
            throw new RuntimeException(
                    messageSource.getMessage("error.Runtime", new String[] { "登録に失敗しました" }, Locale.getDefault()));
        }

        return cnt;
    }

    /**
     * バイク情報を削除する
     * 
     * @param motorcycle 削除するバイク情報
     * @return 削除件数
     */
    @Transactional
    public int delete(Motorcycle motorcycle) {
        int cnt = motorcycleMapper.delete(motorcycle);
        if (cnt == 0) {
            throw new OptimisticLockingFailureException(
                    messageSource.getMessage("error.OptimisticLockingFailure", null, Locale.getDefault()));
        }

        // 2件以上削除された場合は、データの整合性に問題があるため例外をスローする
        if (cnt > 1) {
            throw new RuntimeException(
                    messageSource.getMessage("error.Runtime", new String[] { "2件以上削除されました" }, Locale.getDefault()));
        }

        return cnt;
    }
}
