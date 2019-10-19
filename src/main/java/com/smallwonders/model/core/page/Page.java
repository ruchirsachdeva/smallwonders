package com.smallwonders.model.core.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallwonders.model.core.section.*;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
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

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Header headerSection;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "page_body",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
//    @JsonIgnore
    private Collection<Section> bodySections;


    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Footer footerSection;

    @Enumerated(EnumType.STRING)
    private PageType pageType;



    @Enumerated(EnumType.STRING)
    private Rendering rendering;

    public static Page dummyPage() {
        Page page = new Page();
        page.setHeaderSection(Header.dummySection());
        page.setBodySections(Arrays.asList(Event.dummySection()));
        page.setFooterSection(Footer.dummySection());
        page.setPageType(PageType.EVENT);
        return page;
    }

    public enum PageType {
        CURRICULUM, EVENT, FAQ, NEWS, BRANCH, FRANCHISE, ADMISSION
    }

}
