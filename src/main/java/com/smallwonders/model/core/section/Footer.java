package com.smallwonders.model.core.section;


import com.smallwonders.model.core.content.Content;
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
@DiscriminatorValue(SectionType.Values.FOOTER)
public class Footer extends Section {

    public Footer(String title, String description, Collection<Content> contents, Rendering rendering, Category... categories) {
        super(title, description, SectionType.FOOTER, contents, categories);
    }


    public static Footer dummySection() {
        return new Footer("Footer dummy title", "Dummy footer description....", Collections.emptyList(), Rendering.CAROUSAL, Category.ALL);
    }

}
