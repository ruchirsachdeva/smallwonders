package com.smallwonders.model.core.section.form;

import com.smallwonders.model.core.Coordinates;
import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.section.Organization;
import com.smallwonders.model.core.section.Section;
import com.smallwonders.model.core.section.SectionType;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@MappedSuperclass
public abstract class FormSection extends Section {

    private String name;
    private String phoneNumber;
    private Coordinates location;
    @OneToOne
    private Organization organization;
    private String email;
    private String message;
    private Status status;

    public FormSection(String title, String description, SectionType sectionType, Collection<Content> contents, String name, String phoneNumber, Coordinates location, Organization organization, String email, String message, Status status, Category... categories) {
        super(title, description, sectionType, contents, categories);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.organization = organization;
        this.email = email;
        this.message = message;
        this.status = status;
    }

    public enum Status {
        REQUESTED, IN_PROGRESS, COMPLETED, DELETED, REJECTED
    }


}
