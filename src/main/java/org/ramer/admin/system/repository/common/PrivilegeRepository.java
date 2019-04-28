package org.ramer.admin.system.repository.common;

import org.ramer.admin.system.entity.domain.common.Privilege;
import org.ramer.admin.system.repository.BaseRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends BaseRepository<Privilege, Long> {

  @Query(
      "select p from org.ramer.admin.system.entity.domain.common.Manager m inner join m.roleses r inner join r.privileges p where m.id= :managerId and p.state= :state")
  List<Privilege> findByManager(@Param("managerId") long managerId, @Param("state") int state);

  @Query(
      "select p from org.ramer.admin.system.entity.domain.common.Roles r inner join r.privileges p where r.id= :rolesId and p.state= :state")
  List<Privilege> findByRoles(@Param("rolesId") Long roleId, @Param("state") int state);
}
