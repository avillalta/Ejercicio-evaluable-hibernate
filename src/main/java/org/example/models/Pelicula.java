package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @OneToMany(mappedBy = "pelicula",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Opinion> opiniones = new ArrayList<>();

    public void addOpinion(Opinion opinion) {
        opinion.setPelicula(this);
        this.opiniones.add(opinion);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}