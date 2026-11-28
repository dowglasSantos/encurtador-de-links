package com.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_linkentity")
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @URL(message = "A URL informada não é válida")
    @Column(name = "userLink", nullable = false)
    private String userLink;

    @Column(name = "shortenedLink", nullable = false, unique = true)
    private String shortenedLink;
}
