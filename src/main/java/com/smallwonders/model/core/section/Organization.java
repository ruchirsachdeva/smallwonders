package com.smallwonders.model.core.section;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smallwonders.model.auth.User;
import com.smallwonders.model.core.Coordinates;
import com.smallwonders.model.core.content.Content;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, value = {"users", "organizations"})
@NoArgsConstructor
public class Organization extends Section {

    private Coordinates location;


    @ManyToMany(mappedBy = "organizations", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;

    public Organization(String title, String description, Collection<Content> contents, Coordinates location, Category... categories) {
        super(title, description, SectionType.ORGANIZATION, contents, categories);
        this.location = location;
    }

    public String toString() {
        return "Organization(location=" + this.getLocation() + ")";
    }
}
