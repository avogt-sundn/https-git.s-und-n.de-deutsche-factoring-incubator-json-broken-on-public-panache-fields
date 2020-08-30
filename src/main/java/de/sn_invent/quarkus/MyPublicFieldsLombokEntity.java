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
public class MyPublicFieldsLombokEntity extends PanacheEntityBase {
    @Id
    @EqualsAndHashCode.Include
    public UUID id;

    public String name;

    public MyPublicFieldsLombokEntity(){
        this.id = UUID.randomUUID();
    }
    public MyPublicFieldsLombokEntity(String s) {
        this();
        this.name = s;
    }
}
