package io.github.hobbstech.commons.usermanager.group.model;

import io.github.hobbstech.commons.utilities.jpa.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "user_groups")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        if (!super.equals(o)) return false;
        Group group = (Group) o;
        return getId() == group.getId() &&
                getName().equals(group.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName());
    }

    @Override
    public String toString(){
        return this.name;
    }
}
