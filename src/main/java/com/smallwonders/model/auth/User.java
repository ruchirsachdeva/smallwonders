package com.smallwonders.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smallwonders.model.core.GradeType;
import com.smallwonders.model.core.section.Feedback;
import com.smallwonders.model.core.section.Organization;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(value = {"users", "organizations"}, ignoreUnknown = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_organization",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id")
    )
//    @JsonIgnore
    private Set<Organization> organizations = new HashSet<>();
//    @OneToOne
//    @JoinColumn(name = "working_hours_id")
//    private WorkingHours workingHours;


    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private Set<User> directs = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Collection<User> children = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Feedback> feedbacks = new HashSet<>();

    //  for role student, parent, staff
    // TODO Where will teacher-student-grade go HINT Break into inherited classes
    @ElementCollection
    @CollectionTable(name = "user_grade", joinColumns = @JoinColumn(name = "grade_id"))
    @Column(name = "grades")
    @Enumerated(EnumType.STRING)
    private Set<GradeType> grades = new HashSet<>(Arrays.asList(GradeType.ALL));

    private boolean isVerified;

    private String password;
    private String passwordConfirm;
    @Lob
    @Basic
    private byte[] image;


    public void addOrganization(Organization organization) {
        organizations.add(organization);
        organization.getUsers().add(this);
    }

    public void removeOrganization(Organization organization) {
        organizations.remove(organization);
        organization.getUsers().remove(this);
    }

    @JsonProperty
    public String getBase64() {
        return Base64.encodeBase64String(this.image);
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> getRole() != null ? addRolePrefixWithUnderscore() : "ROLE_USER");
    }

    private String addRolePrefixWithUnderscore() {
        return "ROLE_" + getRole().getName().replaceAll(" ", "_").toUpperCase();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


    public String toString() {
        return "User(userId=" + this.getUserId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", role=" + this.getRole() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", password=" + this.getPassword() + ", passwordConfirm=" + this.getPasswordConfirm() + ")";
    }

}
