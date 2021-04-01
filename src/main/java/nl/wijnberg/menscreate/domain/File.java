package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class File {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private Long fileId;

//    @Column
//    private String info;

    @Column
    private String name;

    //Binary Large OBject for large binary dataobject storage, like image
    @JsonIgnore
    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public File(Long fileId, String name, byte[] image) {
        this.fileId = fileId;
        this.name = name;
        this.image = image;
    }

    public File() {

    }

    public File(String fileName, String contentType, byte[] bytes) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
