package io.github.hobbstech.commons.usermanager.user.model;

import io.github.hobbstech.commons.utilities.jpa.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Audited
public class Token extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    private boolean expired;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        if (!super.equals(o)) return false;
        Token token = (Token) o;
        return Objects.equals(getUser(), token.getUser()) &&
                Objects.equals(getValue(), token.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser(), getValue());
    }
}
