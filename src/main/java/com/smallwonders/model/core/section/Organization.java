package com.smallwonders.model.core.section;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smallwonders.model.auth.User;
import com.smallwonders.model.core.Coordinates;
import com.smallwonders.model.core.content.Content;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, value = {"users", "organizations"})
@NoArgsConstructor
public class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;

    private Coordinates location;

    @OneToOne
    private Content content;


    @ManyToMany(mappedBy = "organizations", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<User> users;

    public Organization(Content content, Coordinates location) {
        this.location = location;
        this.content = content;
    }


}
