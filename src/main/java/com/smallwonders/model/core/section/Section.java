package com.smallwonders.model.core.section;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.section.form.Admission;
import com.smallwonders.model.core.section.form.Franchisee;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@NoArgsConstructor

// saving section will now make entry in table of type
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        property = "type",
//        visible = true
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Curriculum.class, name = SectionType.Values.CURRICULUM),
//        @JsonSubTypes.Type(value = Event.class, name = SectionType.Values.EVENT),
//        @JsonSubTypes.Type(value = Footer.class, name = SectionType.Values.FOOTER),
//        @JsonSubTypes.Type(value = Faq.class, name = SectionType.Values.FAQ),
//        @JsonSubTypes.Type(value = Header.class, name = SectionType.Values.HEADER),
//        @JsonSubTypes.Type(value = Admission.class, name = SectionType.Values.ADMISSION),
//        @JsonSubTypes.Type(value = Franchisee.class, name = SectionType.Values.FRANCHISEE)
//})
//@DiscriminatorColumn(name="TYPE",
//        discriminatorType=DiscriminatorType.STRING
//)
@DiscriminatorFormula("case when TYPE is not null then TYPE else EVENT end")
public class Section {


    @Id
//    @GenericGenerator(
//            name = "sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "4"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
//            }
//    )
//    @GeneratedValue(generator = "sequence-generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    private String title, description;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "section_content",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private Collection<Content> contents;


    @Enumerated(EnumType.STRING)
    private Rendering rendering;

    @Column(name = "TYPE", nullable = false, unique = true, columnDefinition = "varchar2 default " + SectionType.Values.EVENT)
    @Enumerated(EnumType.STRING)
    @ApiParam
    private SectionType type;

//    @ManyToMany(mappedBy = "bodySections", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Collection<Page> pages;

    @ElementCollection
    @CollectionTable(name = "section_category", joinColumns = @JoinColumn(name = "section_id"))
    @Column(name = "categories")
    @Enumerated(EnumType.STRING)
    private Set<Category> categories;

    public Section(String title, String description, SectionType sectionType, Collection<Content> contents, Category... categories) {
        this.title = title;
        this.description = description;
        this.contents = contents;
        this.categories = new HashSet<>(Arrays.asList(categories));
        this.type = sectionType;
    }


    public Section(String title, String description, SectionType sectionType, Collection<Content> contents, Rendering rendering, Category... categories) {
        this(title, description, sectionType, contents, categories);
        this.rendering = rendering;
    }

    public static Section of(SectionType t) {
        return new Section("title", "description", t, Collections.emptyList(), Rendering.CAROUSAL, Category.SCHOOL);
    }

    public enum Category {
        SCHOOL, COACHING, OFFICE, STUDY, SPORTS, CULTURAL, ALL;
    }


}
