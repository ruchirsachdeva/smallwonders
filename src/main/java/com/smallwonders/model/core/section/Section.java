package com.smallwonders.model.core.section;

import com.smallwonders.model.core.content.Content;
import com.smallwonders.model.core.page.PageType;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

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

    @Column(name = "TYPE", nullable = false, columnDefinition = " varchar(255) default 'EVENT'")
    @Enumerated(EnumType.STRING)
    @ApiParam
    private PageType type;

//    @ManyToMany(mappedBy = "bodySections", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Collection<Page> pages;

    @ElementCollection
    @CollectionTable(name = "section_category", joinColumns = @JoinColumn(name = "section_id"))
    @Column(name = "categories")
    @Enumerated(EnumType.STRING)
    private Set<Category> categories;

    public Section(String title, String description, PageType pageType, Collection<Content> contents, Category... categories) {
        this.title = title;
        this.description = description;
        this.contents = contents;
        this.categories = new HashSet<>(Arrays.asList(categories));
        this.type = pageType;
    }


    public Section(String title, String description, PageType pageType, Collection<Content> contents, Rendering rendering, Category... categories) {
        this(title, description, pageType, contents, categories);
        this.rendering = rendering;
    }

    public static Section of(PageType t) {
        return new Section("title", "description", t, Collections.emptyList(), Rendering.CAROUSAL, Category.SCHOOL);
    }


    public enum Category {
        SCHOOL, COACHING, OFFICE, STUDY, SPORTS, CULTURAL, ALL
    }


    public void addContent(Content content) {
        contents.add(content);
        content.getSections().add(this);
    }

    public void addContents(List<Content> contents) {
        contents.forEach(this::addContent);
    }

    public void removeContent(Content content) {
        contents.remove(content);
        content.getSections().remove(this);
    }


    public void removeContents(List<Content> contents) {
        contents.forEach(this::removeContent);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }


    public void addCategories(List<Category> categories) {
        categories.forEach(this::addCategory);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public void removeCategories(List<Category> categories) {
        categories.forEach(this::removeCategory);
    }


}
