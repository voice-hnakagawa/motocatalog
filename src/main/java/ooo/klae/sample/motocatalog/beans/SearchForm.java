package ooo.klae.sample.motocatalog.beans;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SearchForm {

    

    private Integer brandId;
    
    @Size(min = 2, max = 100)
    private String keyword;
}
