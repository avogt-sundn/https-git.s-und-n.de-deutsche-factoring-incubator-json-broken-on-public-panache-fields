package de.sn_invent.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
public class MyLombokEntity extends PanacheEntityBase {
    @Id
    @EqualsAndHashCode.Include
    UUID id;

    String name;

    public MyLombokEntity(){
        this.id = UUID.randomUUID();
    }
    public MyLombokEntity(String s) {
        this();
        this.name = s;
    }
}
