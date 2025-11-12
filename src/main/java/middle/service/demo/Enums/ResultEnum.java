package middle.service.demo.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {
    SUCCESS(200, ""),
    ERROR_401(401, "Request denied. Unauthorized"),
    ERROR_500(500, "Internal server error"),
    ERROR_501(501, "Error in loading data to JSON File");

    private final int code;
    private final String message;
}
