package br.com.danielfreitassc.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "character")
public class CharacterEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    
    @OneToOne
    @JoinColumn(name = "id")
    private WeaponEntity weapon;
    private int birthplace;
    private String personality;
    // private int best_friend;
    // private int enemy;

    @ManyToMany
    @JoinColumn(name = "id")
    private GroupEntity group;

    @OneToMany
    @JoinColumn(name = "id")
    private PlaceEntity favorite_place;
}
