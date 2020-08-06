package com.smallwonders.model.core.section;



import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.page.PageType;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import java.util.Collection;

//@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@DiscriminatorValue(PageType.Values.CURRICULUM)
public class Curriculum extends Section {

    public Curriculum(String title, String description, Collection<Content> contents, Category... categories) {
        super(title, description, PageType.CURRICULUM, contents, categories);

    }
}
