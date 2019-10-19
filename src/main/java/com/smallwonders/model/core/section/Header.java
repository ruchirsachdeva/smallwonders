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
@DiscriminatorValue(SectionType.Values.HEADER)
public class Header extends Section {

    @Enumerated(EnumType.STRING)
    private Rendering rendering;

    public Header(String title, String description, Collection<Content> contents, Rendering rendering, Category... categories) {
        super(title, description, SectionType.HEADER, contents, categories);
        this.rendering = rendering;
    }

    public static Header dummySection() {
        return new Header("Header dummy title", "Dummy header description....", Collections.emptyList(), Rendering.CAROUSAL, Category.ALL) {
        };
    }
}
