package br.com.danielfreitassc.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RelationsType {
    FRIEND("friend"),
    ENEMY("enemy");

    private String relations_type;
}
