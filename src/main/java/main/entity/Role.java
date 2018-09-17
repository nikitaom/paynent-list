package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userId;
    @Column(name = "role")
    public String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }
}
