package sa.account.management.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sa.account.management.model.User;
import sa.account.management.model.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class UserPrincipal implements UserDetails, Serializable {

    private static final long serialVersionUID = 1096596654707987665L;

    private final Integer id;
    private final String username;
    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName().name())
        ).collect(Collectors.toList());

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

    public boolean isAdmin() {
        return authorities.stream().anyMatch(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.name())::equals);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
        UserPrincipal other = (UserPrincipal) obj;
        return Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
