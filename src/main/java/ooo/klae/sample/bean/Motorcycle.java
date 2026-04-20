package ooo.klae.sample.bean;

import java.time.LocalDateTime;

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
    private int cooling;
    private int price;
    private String comment;
    private String brandId;
    private int version;
    private LocalDateTime insDt;
    // private String insDt;

    public void setMotoNo(int motoNo) {
        this.motoNo = motoNo;
    }

    public int getMotoNo() {
        return motoNo;
    }


}
