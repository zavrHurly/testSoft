package com.example.testSoft.domain;

import com.example.testSoft.utils.HasId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
public class User extends BaseEntity implements HasId, Serializable {

    @NotBlank
    private String surname;

    @NotBlank
    private String name;

    private String fatherName;

    private LocalDate dateOfBirth;

    @NotBlank
    private String email;

    @NotBlank
    private long callNumber;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 256)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long id() {
        return id;
    }

    public void updateUser(User updateUser) {
        this.surname = updateUser.getSurname();
        this.name = updateUser.getName();
        this.fatherName = updateUser.getFatherName();
        this.dateOfBirth = updateUser.getDateOfBirth();
        this.callNumber = updateUser.getCallNumber();
    }
}
