package uz.jl.enums.atm;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Elmurodov Javohir, Mon 12:07 PM. 11/29/2021
 */

@Getter
@AllArgsConstructor
public enum CassetteStatus {
    BLOCKED(-1),
    ACTIVE(0);
    private final int code;
}
