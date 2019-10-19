package com.smallwonders.model.core.section.form;


import com.smallwonders.model.core.Coordinates;
import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.section.Organization;
import com.smallwonders.model.core.section.SectionType;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

//@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@DiscriminatorValue(SectionType.Values.FRANCHISEE)
public class Franchisee extends FormSection {

    public Franchisee(String title, String description, Collection<Content> contents, String name, String phoneNumber, Coordinates location, Organization organization, String email, String message, Status status, Category... categories) {
        super(title, description, SectionType.FRANCHISEE, contents, name, phoneNumber, location, organization, email, message, status, categories);
    }
}
