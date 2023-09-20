package sa.account.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "ID")
    private Integer id;
    
    @NotNull
    @Column(name = "account_type")
    private String accountType;
    
    @NotNull
    @Column(name = "account_number")
    private String accountNumber;

    @OneToMany(mappedBy="account")
    private Set<AccountStatement> statements;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        return Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID='" + id + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
