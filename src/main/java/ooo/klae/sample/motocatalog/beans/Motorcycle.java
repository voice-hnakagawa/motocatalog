package ooo.klae.sample.motocatalog.beans;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  
public class Motorcycle {
    private int motoNo;
    private String motoName;
    private int seatHeight;
    private int cylinder;
    private String cooling;
    private int price;
    private String comment;
    private Brand brandId;
    private int version;
    private LocalDateTime insDt;
    private LocalDateTime updDt;

    public void setMotoNo(int motoNo) {
        this.motoNo = motoNo;
    }

    public int getMotoNo() {
        return motoNo;
    }


}
