package org.ramer.admin.system.entity.domain;

import org.ramer.admin.system.entity.Constant;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/** domain类的父类 */
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "tinyint default 1")
  private Integer state = Constant.STATE_ON;

  @CreationTimestamp
  @Column(columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间'")
  private Date createTime = new Date();

  @UpdateTimestamp
  @Column(
      columnDefinition =
          "datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'")
  private Date updateTime = new Date();
}
