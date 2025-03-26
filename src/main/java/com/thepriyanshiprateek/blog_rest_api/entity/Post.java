package com.thepriyanshiprateek.blog_rest_api.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Data
//no need for Getter n Setter method
@AllArgsConstructor
//no need for all arguments constructor
@NoArgsConstructor
//no need for no argument constructor
@Entity
@Table(
        name = "posts",
        uniqueConstraints = @UniqueConstraint(columnNames = "title")
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name="title" , nullable = false)
    private String title;
    @Column(name="content" , nullable = false)
    private String content;
    @Column(name="description" , nullable = false)
    private String description;
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();
}
