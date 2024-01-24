package br.com.danielfreitassc.crud.models;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CrudEntity extends RepresentationModel<CrudEntity> implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal price;
}
