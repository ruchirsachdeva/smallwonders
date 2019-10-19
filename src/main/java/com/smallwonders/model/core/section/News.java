package com.smallwonders.model.core.section;

import com.smallwonders.model.core.content.Content;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

//@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@DiscriminatorValue(SectionType.Values.NEWS)
public class News extends Section {

    public News(String title, String description, Collection<Content> contents, Category... categories) {
        super(title, description, SectionType.NEWS, contents, categories);
    }
}
