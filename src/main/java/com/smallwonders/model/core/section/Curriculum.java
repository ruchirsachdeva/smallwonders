package com.smallwonders.model.core.section;



import com.smallwonders.model.core.content.Content;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Curriculum extends Section {

    public Curriculum(String title, String description, Collection<Content> contents, Category... categories) {
        super(title, description, SectionType.CURRICULUM, contents, categories);

    }
}
