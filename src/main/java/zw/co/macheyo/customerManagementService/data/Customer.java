package zw.co.macheyo.customerManagementService.data;

import lombok.Data;
import zw.co.macheyo.customerManagementService.data.enums.Stage;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Kudzai Justice Macheyo
 */

@Entity
@Data
public class Customer extends BaseEntity{
    private String customerId;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Stage stage;

}
