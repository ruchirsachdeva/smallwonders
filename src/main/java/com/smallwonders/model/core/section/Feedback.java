package com.smallwonders.model.core.section;

import com.smallwonders.model.auth.User;
import com.smallwonders.model.core.content.Content;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @OneToOne
    private Content content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Feedback(Content content, User user) {
        this.user = user;
        this.content = content;
    }
}
