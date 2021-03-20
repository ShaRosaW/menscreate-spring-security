package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class File {

    @Id
//    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue
    private Long fileId;

//    @Column
//    private String info;

    @Column
    private String name;

    //Binary Large OBject for large binary dataobject storage, like image
    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    byte[] image;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public File(Long fileId, String name) {
        this.fileId = fileId;
        this.name = name;
    }

    public File() {

    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String file) {
        this.name = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
