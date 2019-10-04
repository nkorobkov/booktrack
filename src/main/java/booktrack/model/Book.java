package booktrack.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="book")
public class Book implements Serializable {


    @Id()
    private Long id;
    private String authorfamily1;
    private String authorname1;
    private String authorsurname1;
    private String role1;
    private String pseudonim1;
    private String authorfamily2;
    private String authorname2;
    private String authorSurname2;
    private String role2;
    private String pseudonim2;
    private String authorfamily3;
    private String authorname3;
    private String authorsurname3;
    private String role3;
    private String pseudonim3;
    private String authorfamily4;
    private String authorname4;
    private String authorsurname4;
    private String role4;
    private String pseudonim4;
    private String series1;
    private String series2;
    private String series3;
    private String series4;
    private String title;
    private String lowertitle;
    private String extension;
    private String version;
    private String filesize;
    private String md5;
    private String path;
    private String language;
    private String pages;
    private String isbn;
    private String year;
    private String publisher;
    private String descr;
    private ZonedDateTime timeAdded;
    private ZonedDateTime timelastmodified;
    private String authorfamilyrus;
    private String authornamerus;
    private String authorsurnamerus;
    @Column(name = "boolean")
    private Boolean bool;
    private String unkn1;
    private String unkn2;
}
