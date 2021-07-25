package zw.co.macheyo.customerManagementService.data;

import lombok.Data;
import zw.co.macheyo.customerManagementService.data.enums.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class FAQ extends BaseEntity{
    private String topic;
    private String answer;
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;
}
