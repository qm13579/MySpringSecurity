package cn.people.domain.vo;
import cn.people.utils.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoVO {

    @NotBlank(groups = {BaseEntity.Create.class})
    private String context;
    /**消息时间*/
    @NotBlank(groups = BaseEntity.Create.class)
    private String date;
    /**消息有效期*/
    @NotBlank(groups = BaseEntity.Create.class)
    private String endDate;

    @Override
    public String toString() {
        return "InfoVO{" +
                "context='" + context + '\'' +
                ", date='" + date + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
