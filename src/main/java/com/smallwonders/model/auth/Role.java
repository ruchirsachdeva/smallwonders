package com.smallwonders.model.auth;


import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long roleId;
  private String name;

  @ElementCollection
  @CollectionTable(name = "role_type", joinColumns = @JoinColumn(name = "role_id"))
  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private Set<RoleType> type = new HashSet<>(Arrays.asList(RoleType.Admin));

}
