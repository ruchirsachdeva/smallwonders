package com.smallwonders.model.core.section;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.page.PageType;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

//@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@DiscriminatorValue(PageType.Values.NEWS)
public class News extends Section {

    public News(String title, String description, Collection<Content> contents, Category... categories) {
        super(title, description, PageType.NEWS, contents, categories);
    }
}
