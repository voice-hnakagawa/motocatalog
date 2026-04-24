package ooo.klae.sample.motocatalog.beans;

import java.time.LocalDateTime;

// import org.springframework.cglib.core.Local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  
public class Motorcycle {
    private Integer motoNo;
    private String motoName;
    private Integer seatHeight;
    private Integer cylinder;
    private String cooling;
    private Integer price;
    private String comment;
    private Brand brandId;
    private Integer version;
    private LocalDateTime insDt;
    private LocalDateTime updDt;

    public void setMotoNo(Integer motoNo) {
        this.motoNo = motoNo;
    }

    public Integer getMotoNo() {
        return motoNo;
    }


}
