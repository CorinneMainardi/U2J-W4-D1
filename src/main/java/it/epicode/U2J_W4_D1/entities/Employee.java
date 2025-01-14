package it.epicode.U2J_W4_D1.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email") //Voglio che email e username siano univoci
})
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String name;
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    private String img; //per l'img è stata creata un dto a parte, in quanto facoltativa


}


