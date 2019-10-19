package com.smallwonders.model.core.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smallwonders.model.auth.User;
import com.smallwonders.model.core.section.Section;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    private String title, description;

    private ContentType type;

    @Lob
    @Basic
    private byte[] data;

    @ManyToMany(mappedBy = "contents", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Section> sections;

    private Date created;
    private Date updated;
    private Date deleted;

    private boolean visibleToPublic;

    public Content(String title, String description, ContentType type, byte[] data, Set<Section> sections, Date created, Date updated, Date deleted, boolean visibleToPublic) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.data = data;
        this.sections = sections;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.visibleToPublic = visibleToPublic;
    }

    public Content(String title, String description, ContentType type, byte[] data, Set<Section> sections, boolean visibleToPublic) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.data = data;
        this.sections = sections;
        this.created = new Date();
        this.updated = new Date();
        this.visibleToPublic = visibleToPublic;
    }
}
