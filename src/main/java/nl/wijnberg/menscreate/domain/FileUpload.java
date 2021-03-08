package nl.wijnberg.menscreate.domain;

import javax.persistence.*;
import java.io.File;

@Entity
@Table
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

//    @Column
//    private String info;

    @Column
    File file;

    public FileUpload(Long fileId, File file) {
        this.fileId = fileId;
        this.file = file;
    }

    public FileUpload() {

    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
