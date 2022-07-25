package com.academy.awards.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity( name = "CategoryMovie")
@Table( name = "category_movie")
public class CategoryMovie {

        @Id
        @SequenceGenerator(
                name = "category_sequence",
                sequenceName = "category_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "category_sequence"
        )
        @Column(
                name = "categoryId"
        )
        private Long categoryId;
        private String  description;
}
