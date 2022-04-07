package uz.jl.enums.atm;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Elmurodov Javohir, Mon 12:12 PM. 11/29/2021
 */
@Getter
@AllArgsConstructor
public enum ATMType {
    UZCARD("des"),
    HUMO("des"),
    VISA("des"),
    VISA_UZCARD("des"),
    VISA_HUMO("des");
    private String description;

    public static void listAtmType() {
        int cnt = 1;
        for (ATMType value : values()) {
            System.out.println(cnt++ + ". " + value);
        }
    }
}
