package com.academy.awards.entitys;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity( name = "Movie")
@Table( name = "movie")
public class Movie {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "movie_sequence"
    )
    @Column(
            name = "movieId"
    )
    private Long movieId;

    private String  year;
    private Integer rate;

    @Column(nullable=false)
    private Long categoryId;   // table: category_movie

    @Fetch(FetchMode.JOIN)
    @ManyToOne(optional=false)
    @JoinColumn(name="categoryId",referencedColumnName="categoryId", insertable =  false, updatable = false )
    private CategoryMovie categoryMovie;


    @Column(length = 1000)
    private String  nominee;

    @Column(length = 500)
    private String  additionalInfo;
    private String  won;

    private Date dateInsert;
    private Date dateUpdate;

}