package uz.jl.models.personal;

import lombok.*;
import uz.jl.enums.extras.Gender;
import uz.jl.models.Auditable;

import java.util.Date;

/**
 * @author Elmurodov Javohir, Mon 12:06 PM. 12/6/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passport extends Auditable {
    private String serial;
    private String number;
    private Gender gender;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String ownerId;

    @Builder(builderMethodName = "childBuilder")
    public Passport(String id, Date createdAt, String createdBy, Date updatedAt, String updatedBy, int deleted, String serial, String number, Gender gender, String firstName, String lastName, String fatherName, String ownerId) {
        super(createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.serial = serial;
        this.number = number;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.ownerId = ownerId;
    }
}
