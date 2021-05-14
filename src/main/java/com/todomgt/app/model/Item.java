package com.todomgt.app.model;

import com.todomgt.app.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item implements Comparable<Item>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Override
    public int compareTo(Item o) {
        Item that = o;
        return this.getUpdatedAt().compareTo(that.getUpdatedAt());
    }
}
