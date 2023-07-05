package com.pms.PMS.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import java.util.Date;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "PROJECTS")
public class ProjectDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @Column(name="intro")
    private String intro;

    @Column(name="owner", nullable = false)
    private String owner_name;

    @Column(name="status")
    private int status;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="start")
    private Date start;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="end")
    private Date end;

    @Column(name="numberOfProjectMembers")
    private int numberOfProjectMembers;

    @ManyToMany(mappedBy = "projectDtls", fetch = FetchType.LAZY)
    private Set<UserDtls> userDtls;

    public ProjectDtls() {
    }

    public ProjectDtls(Long id, String name, String intro, String owner_name, int status, Date start, Date end, int numberOfProjectMembers) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.owner_name = owner_name;
        this.status = status;
        this.start = start;
        this.end = end;
        this.numberOfProjectMembers = numberOfProjectMembers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getNumberOfProjectMembers() {
        return numberOfProjectMembers;
    }

    public void setNumberOfProjectMembers(int numberOfProjectMembers) {
        this.numberOfProjectMembers = numberOfProjectMembers;
    }
}