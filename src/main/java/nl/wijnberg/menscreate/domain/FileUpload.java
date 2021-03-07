package nl.wijnberg.menscreate.domain;

import javax.persistence.*;
import java.io.File;

@Entity
@Table
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
//    private String info;

    @Column
    File file;

    public FileUpload(Long id, File file) {
        this.id = id;
        this.file = file;
    }

    public FileUpload() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
