package com.example.sampleproject.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<AlbumEntity> vinyls;

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<AlbumEntity> getRecords() {
        return vinyls;
    }

    public UserEntity setRecords(List<AlbumEntity> vinyls) {
        this.vinyls = vinyls;
        return this;
    }
}
