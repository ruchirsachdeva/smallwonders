package com.smallwonders.model.core.section.form;


import com.smallwonders.model.core.Coordinates;
import com.smallwonders.model.auth.User;
import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.section.Organization;
import com.smallwonders.model.core.section.SectionType;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Admission extends FormSection {

    private User child;

    private Date dateOfBirth;


    public Admission(String title, String description, Collection<Content> contents, String name, String phoneNumber, Coordinates location, Organization organization, String email, String message, Status status, User child, Date dateOfBirth, Category... categories) {
        super(title, description, SectionType.ADMISSION, contents, name, phoneNumber, location, organization, email, message, status, categories);
        this.child = child;
        this.dateOfBirth = dateOfBirth;
    }
}
