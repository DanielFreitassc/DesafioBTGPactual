package br.com.danielfreitassc.backend.exceptions.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String status;
    private String message;
}
