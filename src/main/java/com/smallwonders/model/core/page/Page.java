package com.smallwonders.model.core.page;

import com.smallwonders.model.core.section.*;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageId;

    private String title, description;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "page_section",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
//    @JsonIgnore
    private Collection<Section> sections;

    @Enumerated(EnumType.STRING)
    private PageType pageType;

    @Enumerated(EnumType.STRING)
    private Rendering rendering;

    public static Page dummyPage() {
        Page page = new Page();
        page.setSections(Arrays.asList(Header.dummySection(), Event.dummySection(), Footer.dummySection()));
        page.setPageType(PageType.EVENT);
        return page;
    }

    public enum PageType {
        CURRICULUM, EVENT, FAQ, NEWS, BRANCH, FRANCHISE, ADMISSION
    }

}
