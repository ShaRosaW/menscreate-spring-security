package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.BoxType;
import nl.wijnberg.menscreate.domain.enums.EBoxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxTypeRepository extends JpaRepository<BoxType, Long> {
    BoxType findByName(EBoxType name);
}
