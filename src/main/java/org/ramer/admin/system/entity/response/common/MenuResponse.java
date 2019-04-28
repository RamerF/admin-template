package org.ramer.admin.system.entity.response.common;

import org.ramer.admin.system.entity.pojo.common.MenuPoJo;
import org.ramer.admin.util.BeanUtils;
import java.util.List;
import lombok.*;

/** @author ramer */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class MenuResponse {
  private Long id;
  private String name;
  private String url;
  private Boolean leaf;
  private String icon;
  private Long pId;
  private List<MenuResponse> children;

  public static MenuResponse of(MenuPoJo menuPoJo) {
    MenuResponse response = new MenuResponse();
    BeanUtils.getDozerBeanMapper().map(menuPoJo, response);
    return response;
  }
}
