package uz.jl.models.branch;

import lombok.*;
import uz.jl.enums.atm.Status;
import uz.jl.models.Auditable;
import uz.jl.models.atm.ATMEntity;
import uz.jl.models.auth.AuthUser;
import uz.jl.ui.AdminUI;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends Auditable {
    private String name;
    private Status status;
}



