package longlh.dev.base.excel.base.excel.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String telephone;
}
