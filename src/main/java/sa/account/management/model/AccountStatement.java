package sa.account.management.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Statement")
public class AccountStatement {

    @Id
    @Column(name = "ID")
    private Integer id;
    
    @NotNull
    @Column(name = "datefield")
    private String dateField;
    
    @NotNull
    @Column(name = "amount")
    private String amount;

    @ManyToOne
    @NotNull
    @JoinColumn(name="account_id")
    private Account account;

    public AccountStatement(Integer id, String dateField, String amount) {
        this.id = id;
        this.dateField = dateField;
        this.amount = amount;
    }

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
        AccountStatement other = (AccountStatement) obj;
        return Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
                "id=" + id +
                ", dateField='" + dateField + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
