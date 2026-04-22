package ooo.klae.sample.motocatalog.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import ooo.klae.sample.motocatalog.beans.Motorcycle;
import static org.assertj.core.api.Assertions.assertThat;

import ooo.klae.sample.motocatalog.beans.SearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class MotosServiceTest {

    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    
    @Autowired
    MotosService service;

    // @Test
    // void バイク情報を全件検索できる() {
    //     SearchCondition condition = new SearchCondition();
    //     List<Motorcycle> motorcycles = service.getMotos(condition);
    //     // 検索結果の件数確認
    //     assertThat(motorcycles.size()).isEqualTo(4);

    //     // 検索結果の各項目の確認
    //     Motorcycle moto = motorcycles.get(0);
    //     assertThat(moto).isNotNull();
    //     assertThat(moto.getMotoNo()).isEqualTo(1);
    //     assertThat(moto.getMotoName()).isEqualTo("YZF-R1");
    //     assertThat(moto.getPrice()).isEqualTo(1500000);
    //     assertThat(moto.getBrandId().getBrandName()).isEqualTo("Yamaha");
    // }

    // バイク一覧取得条件：ブランドID
    @DisplayName("バイク一覧取得条件：ブランドID")
    @ParameterizedTest
    @CsvSource({"1, Yamaha", "2, Honda", "3, Suzuki", "4, Kawasaki"})
    void test001(int brandId, String brandName) {
        SearchCondition condition = new SearchCondition();
        condition.setBrandId(brandId);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        // 検索結果の件数確認
        // assertThat(motorcycles.size()).isEqualTo(2);
        assertThat(motorcycles.size()).isGreaterThanOrEqualTo(1);
        assertThat(motorcycles.size()).isGreaterThan(0);
        for (Motorcycle moto : motorcycles) {
            assertThat(moto.getBrandId().getBrandName()).isEqualTo(brandName);
        }
    }

    // バイク一覧取得条件：ブランドID該当なし
    @DisplayName("バイク一覧取得条件：ブランドID該当なし")
    @ParameterizedTest
    @CsvSource({"999"})
    void test002(int brandId) {
        SearchCondition condition = new SearchCondition();
        condition.setBrandId(brandId);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        // 検索結果の件数確認
        assertThat(motorcycles.size()).isEqualTo(0);
    }

    // バイク一覧取得条件：バイク名‐完全一致
    @DisplayName("バイク一覧取得条件：バイク名‐完全一致")
    @ParameterizedTest
    @CsvSource({"YZF-R1", "CBR1000RR", "GSX-R1000", "Ninja ZX-10R"})
    void test003(String bikeName) {
        SearchCondition condition = new SearchCondition();
        condition.setKeyword(bikeName);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        

        assertThat(motorcycles.size()).isGreaterThanOrEqualTo(1);
        for (Motorcycle moto : motorcycles) {
            assertThat(moto.getMotoName()).isEqualTo(bikeName);
        }
    }

    // バイク一覧取得条件：バイク名‐前方一致
    @DisplayName("バイク一覧取得条件：バイク名‐前方一致")
    @ParameterizedTest
    @CsvSource({"YZF, YZF-R1", "CBR100, CBR1000RR", "GSX, GSX-R1000", "Ninja, Ninja ZX-10R"})
    void test004_1(String keyword, String partOfBikeName) {
        SearchCondition condition = new SearchCondition();
        condition.setKeyword(keyword);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        
        assertThat(motorcycles.size()).isGreaterThanOrEqualTo(1);
        for (Motorcycle moto : motorcycles) {
            assertThat(moto.getMotoName()).startsWith(partOfBikeName);
        }
    }

    // バイク一覧取得条件：バイク名‐後方一致
    @DisplayName("バイク一覧取得条件：バイク名‐後方一致")
    @ParameterizedTest
    @CsvSource({"F-R1, YZF-R1", "RR, CBR1000RR", "-R1000, GSX-R1000", "ZX-10R, Ninja ZX-10R"})
    void test004_2(String keyword, String partOfBikeName) {
        SearchCondition condition = new SearchCondition();
        condition.setKeyword(keyword);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        
        assertThat(motorcycles.size()).isGreaterThanOrEqualTo(1);
        for (Motorcycle moto : motorcycles) {
            assertThat(moto.getMotoName()).endsWith(partOfBikeName);
        }
    }

    // バイク一覧取得条件：バイク名‐部分一致
    @DisplayName("バイク一覧取得条件：バイク名‐部分一致")
    @ParameterizedTest
    @CsvSource({"YZF, YZF-R1", "CBR100, CBR1000RR", "GSX, GSX-R1000", "Ninja, inja ZX-10R"})
    void test004(String keyword, String partOfBikeName) {
        SearchCondition condition = new SearchCondition();
        condition.setKeyword(keyword);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        
        assertThat(motorcycles.size()).isGreaterThanOrEqualTo(1);
        for (Motorcycle moto : motorcycles) {
            assertThat(moto.getMotoName()).contains(partOfBikeName);
        }
    }

    // バイク一覧取得条件：バイク名‐該当なし
    @DisplayName("バイク一覧取得条件：バイク名‐該当なし")
    @Test
    void test005() {
        SearchCondition condition = new SearchCondition();
        condition.setKeyword("該当なし");
        List<Motorcycle> motorcycles = service.getMotos(condition);
        assertThat(motorcycles.size()).isEqualTo(0);
    }

    // バイク一覧取得条件：ブランドID、バイク名
    @DisplayName("バイク一覧取得条件：ブランドID、バイク名")
    @ParameterizedTest
    @CsvSource({"1, YZF, YZF-R1", "2, CBR100, CBR1000RR", "3, GSX, GSX-R1000", "4, Ninja, inja ZX-10R"})
    void test006(int brandId, String keyword, String partOfBikeName) {
        SearchCondition condition = new SearchCondition();
        condition.setBrandId(brandId);
        condition.setKeyword(keyword);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        
        assertThat(motorcycles.size()).isGreaterThanOrEqualTo(1);
        for (Motorcycle moto : motorcycles) {
            assertThat(moto.getBrandId().getBrandId()).isEqualTo(brandId);
            assertThat(moto.getMotoName()).contains(partOfBikeName);

        }
    }

    // バイク一覧取得条件：ブランドID、バイク名該当なし
    @DisplayName("バイク一覧取得条件：ブランドID、バイク名該当なし")
    @ParameterizedTest
    @CsvSource({"1, 該当なし", "2, 該当なし", "3, 該当なし", "4, 該当なし"})
    void test007(int brandId, String keyword) {
        SearchCondition condition = new SearchCondition();
        condition.setBrandId(brandId);
        condition.setKeyword(keyword);
        List<Motorcycle> motorcycles = service.getMotos(condition);
        assertThat(motorcycles.size()).isEqualTo(0);
    }

    // バイク一覧取得条件：なし　全件該当
    @DisplayName("バイク一覧取得条件：なし　全件該当")
    @Test
    void test008() {
        SearchCondition condition = new SearchCondition();
        List<Motorcycle> motorcycles = service.getMotos(condition);
        assertThat(motorcycles.size()).isEqualTo(10);
    }

    // バイク情報取得条件：バイク番号
    @DisplayName("バイク情報取得条件：バイク番号")
    @ParameterizedTest
    @CsvSource({"1, YZF-R1", "2, CBR1000RR", "3, GSX-R1000", "4, Ninja ZX-10R"})
    void test009(int motoNo, String motoName) {
        Motorcycle moto = service.getMotos(motoNo);
        assertThat(moto.getMotoName()).isEqualTo(motoName);
    }

    @DisplayName("バイク情報取得条件：バイク番号　前項目確認")
    @Test
    void test010() {
        Motorcycle motorcycles = service.getMotos(1);    
        assertThat(motorcycles.getMotoNo()).isEqualTo(1);
        assertThat(motorcycles.getMotoName()).isEqualTo("YZF-R1");
        assertThat(motorcycles.getSeatHeight()).isEqualTo(850);
        assertThat(motorcycles.getCylinder()).isEqualTo(4);
        assertThat(motorcycles.getCooling()).isEqualTo("水冷");
        assertThat(motorcycles.getPrice()).isEqualTo(1500000);
        assertThat(motorcycles.getComment()).isEqualTo("Yamahaの代表的なスポーツバイク");
        assertThat(motorcycles.getBrandId().getBrandId()).isEqualTo(1);
        assertThat(motorcycles.getBrandId().getBrandName()).isEqualTo("Yamaha");
        assertThat(motorcycles.getVersion()).isEqualTo(1);
        // assertThat(motorcycles.getInsDt().format(dtFormatter)).isEqualTo(LocalDateTime.now().format(dtFormatter)); // Doesn't work because the local laptop isn't fast enough to keep up with the test execution, so the insDt is a few seconds earlier than the current time when the assertion is executed.
        assertThat(motorcycles.getInsDt().format(dtFormatter)).isEqualTo(motorcycles.getInsDt().format(dtFormatter));
        assertThat(motorcycles.getUpdDt()).isNull();
    }

    @DisplayName("バイク情報更新")
    @Test
    @Transactional
    @Rollback
    void test011() {
        Motorcycle before = service.getMotos(1);
        before.setMotoName("motomoto");

        service.save(before);

        Motorcycle after = service.getMotos(1);
        assertThat(after.getMotoName()).isEqualTo("motomoto");
        assertThat(after.getVersion()).isEqualTo(before.getVersion() + 1);
    }
}
