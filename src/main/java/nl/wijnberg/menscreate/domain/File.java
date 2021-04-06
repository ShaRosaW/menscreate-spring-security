package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String name;

    @Column
    private String type;

    //Binary Large OBject for large binary dataobject storage, like image
    @JsonIgnore
    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public File(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public File() {

    }

    //
//    public File(String fileId, String name, byte[] image) {
//        this.fileId = fileId;
//        this.name = name;
//        this.image = image;
//    }
//
//    public File() {
//
//    }
//
//    public File(String fileName, String contentType, byte[] bytes) {
//    }
//
//    public Long getFileId() {
//        return fileId;
//    }
//
//    public void setFileId(Long fileId) {
//        this.fileId = fileId;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
