package ooo.klae.sample.motocatalog.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
// import ooo.klae.sample.motocatalog.beans.Brand;

/**
 * バイクの登録・更新用のフォームクラス
 */
@Data
public class MotoForm {
    private int motoNo;

    @NotBlank
    @Size(min = 1, max = 255)
    private String motoName;

    @Min(0)
    @Max(1000)
    private Integer seatHeight;

    @Max(10)
    private Integer cylinder;

    @Size(max = 20)
    private String cooling;

    @Min(0)
    private Integer price;

    @Size(max = 255)
    private String comment;

    @NotNull
    private Integer brandId;

    private Integer version;
}
