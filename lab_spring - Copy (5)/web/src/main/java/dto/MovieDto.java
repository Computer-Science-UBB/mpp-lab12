package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class MovieDto extends BaseDto{
    private String title;
    private String description;
    private int rating;
    private int price;
}
