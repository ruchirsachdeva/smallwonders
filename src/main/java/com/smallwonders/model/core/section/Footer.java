package com.smallwonders.model.core.section;


import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.page.PageType;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

//@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@DiscriminatorValue(PageType.Values.FOOTER)
public class Footer extends Section {

    public Footer(String title, String description, Collection<Content> contents, Rendering rendering, Category... categories) {
        super(title, description, PageType.FOOTER, contents, categories);
    }


    public static Footer dummySection() {
        return new Footer("Footer dummy title", "Dummy footer description....", Collections.emptyList(), Rendering.CAROUSAL, Category.ALL);
    }

}
