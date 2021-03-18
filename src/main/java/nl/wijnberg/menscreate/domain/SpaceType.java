package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.wijnberg.menscreate.domain.enums.EBoxType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "space_type")
public class SpaceType {
    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    @Column(columnDefinition = "serial")
    private long spacetypeId;

    @Enumerated(EnumType.STRING)
    private ESpaceType name;

    @JsonIgnore
    @OneToMany (mappedBy = "space_type")
    private Set<Space> spaces;

    public long getSpacetypeId() {
        return spacetypeId;
    }

    public Set<Space> getSpaces() {
        return spaces;
    }

    public ESpaceType getName() {
        return name;
    }

    public void setName(ESpaceType name) {
        this.name = name;
    }
}
