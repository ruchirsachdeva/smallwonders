package com.smallwonders.model.core.section;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.content.ContentType;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

//@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@DiscriminatorValue(SectionType.Values.EVENT)
public class Event extends Section {

    public Event(String title, String description, Collection<Content> contents, Category... categories) {
        super(title, description, SectionType.EVENT, contents, categories);
    }

    public static Section dummySection() {
        Content item1 = new Content("Event content title 1", "Event content description...1....more--", ContentType.TEXT, null, null, true);
        Content item2 = new Content("Event content title 2", "Event content description...2.......more--", ContentType.TEXT, null, null,  true);
        Content item3 = new Content("Event content title 3", "Event content description...3........more--", ContentType.TEXT, null, null, true);
        return new Event("Event dummy title", "Dummy event description....", Arrays.asList(item1, item2, item3), Category.ALL, Category.SCHOOL);

    }
}
