package ooo.klae.sample.motocatalog.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import ooo.klae.sample.motocatalog.beans.Motorcycle;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MotosServiceTest {
    
    @Autowired
    MotosService service;

    @Test
    void バイク情報を全件検索できる() {
        List<Motorcycle> motorcycles = service.getMotos();
        // 検索結果の件数確認
        assertThat(motorcycles.size()).isEqualTo(4);

        // 検索結果の各項目の確認
        Motorcycle moto = motorcycles.get(0);
        assertThat(moto).isNotNull();
        assertThat(moto.getMotoNo()).isEqualTo(1);
        assertThat(moto.getMotoName()).isEqualTo("YZF-R1");
        assertThat(moto.getPrice()).isEqualTo(1500000);
        assertThat(moto.getBrandId().getBrandName()).isEqualTo("Yamaha");
    }
}
