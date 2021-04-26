package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

//    @Column
    private String name;

//    @Column
    private String type;

    //Binary Large OBject for large binary dataobject storage, like image
//    @JsonIgnore

//    @Column(name = "data", columnDefinition="BLOB")
    @Lob
    private byte[] data;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    public FileDB() {

    }

    public FileDB(String name, String type, byte[] data, User user) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.user = user;
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

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
