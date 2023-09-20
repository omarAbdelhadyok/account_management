package sa.account.management.model;

import sa.account.management.model.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    RoleName roleName;

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + roleName +
                '}';
    }

}
