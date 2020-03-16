package com.wzz.neko.model;

import javax.persistence.*;

@Table(name = "novel_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "book_count")
    private Long bookCount;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return book_count
     */
    public Long getBookCount() {
        return bookCount;
    }

    /**
     * @param bookCount
     */
    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }
}