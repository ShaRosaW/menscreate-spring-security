package nl.wijnberg.menscreate.domain;

import javax.persistence.*;

@Entity
@Table
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boxId;

    @Column
    private String boxType;


}
