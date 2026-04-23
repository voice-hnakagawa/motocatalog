package ooo.klae.sample.motocatalog.forms;

import lombok.Data;
import ooo.klae.sample.motocatalog.beans.Brand;

/**
 * バイクの登録・更新用のフォームクラス
 */
@Data
public class MotoForm {
    private int motoNo;
    private String motoName;
    private int seatHeight;
    private int cylinder;
    private String cooling;
    private int price;
    private String comment;
    private int brandId;
    private int version;
}
