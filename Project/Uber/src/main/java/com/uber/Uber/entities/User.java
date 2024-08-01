package com.uber.Uber.entities;


import com.uber.Uber.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;


    //New Table Creation: Using @ElementCollection
    // with @Enumerated(EnumType.STRING) in JPA creates a new table in the
    // database to store a collection of enum values (e.g., roles). Each enum value is stored as a string in this table.
    //
    //Relationship: The new table is linked to the main entity's table through a foreign key relationship,
    // typically referencing the primary key of the entity.
    //
    //Automatic Management: JPA automatically manages the new table, handling creation, updates, and
    // deletion of entries based on the collection defined in the entity class.
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
