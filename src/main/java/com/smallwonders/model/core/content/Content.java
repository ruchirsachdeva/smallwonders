package com.smallwonders.model.core.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smallwonders.model.core.section.Section;
import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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
    @JsonIgnore
    private byte[] data;

    @ManyToMany(mappedBy = "contents", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<Section> sections;

    @JsonIgnore
    private Date created, updated, deleted;

    private boolean visibleToPublic;

    public Content(String title, String description, ContentType type, String base64, Set<Section> sections, boolean visibleToPublic) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.sections = sections;
        this.created = new Date();
        this.updated = new Date();
        this.visibleToPublic = visibleToPublic;
        this.setBase64Data(base64);
    }

    @JsonProperty
    public String getBase64Data() {
        return Base64.encodeBase64String(this.data);
    }

    @JsonProperty
    public void setBase64Data(String base64) {
        byte[] dataByte = Base64.decodeBase64(base64);
        this.setData(dataByte);
    }


    public void addSection(Section section) {
        sections.add(section);
        section.getContents().add(this);
    }

    public void removeSection(Section section) {
        sections.remove(section);
        section.getContents().remove(this);
    }

    public void addSections(List<Section> sections) {
        sections.forEach(this::addSection);
    }

    public void removeSections(List<Section> sections) {
        sections.forEach(this::removeSection);
    }

    @PrePersist
    public void prePersist() {
        created = new Date();
        updated = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updated = new Date();
    }

}
