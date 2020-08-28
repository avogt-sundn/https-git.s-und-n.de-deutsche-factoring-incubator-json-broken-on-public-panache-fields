package de.sn_invent.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class MyEntity extends PanacheEntityBase {
    @Id
    UUID id;

    String name;
}
