package com.smallwonders.model.core.section;

import com.smallwonders.model.auth.User;
import com.smallwonders.model.core.content.Content;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Feedback extends Section {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Feedback(String title, String description, Collection<Content> contents, User user, Category... categories) {
        super(title, description, SectionType.FEEDBACK, contents, categories);
        this.user = user;
    }

    public Feedback(String title, String description, Collection<Content> contents, Rendering rendering, User user, Category... categories) {
        super(title, description, SectionType.FEEDBACK, contents, rendering, categories);
        this.user = user;
    }
}
