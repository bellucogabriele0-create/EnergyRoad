package gabrielebelluco.EnergyRoad.entities;

import gabrielebelluco.EnergyRoad.enums.RoleType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }


    public Role(UUID roleId, RoleType roleType, Set<User> users) {
        this.roleId = roleId;
        this.roleType = roleType;
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public UUID getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", type='" + roleType + '\'' +
                '}';
    }
}
