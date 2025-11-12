package middle.service.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"recordCount"})
public class Meta {
    private int code;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int recordCount;
}
