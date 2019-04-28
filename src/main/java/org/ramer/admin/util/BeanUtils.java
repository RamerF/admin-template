package org.ramer.admin.util;

import java.util.HashSet;
import java.util.Set;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtils {
  private static final DozerBeanMapper DOZER_BEAN_MAPPER = new DozerBeanMapper();
  /** 获取对象属性映射实例. {@link org.springframework.beans.BeanUtils */
  @Deprecated
  public static DozerBeanMapper getDozerBeanMapper() {
    return DOZER_BEAN_MAPPER;
  }
  /** 获取对象属性值为空的属性名 */
  public static String[] getNullPropertyNames(Object obj) {
    final BeanWrapper src = new BeanWrapperImpl(obj);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
    Set<String> nullNames = new HashSet<>();
    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) nullNames.add(pd.getName());
    }
    return nullNames.toArray(new String[0]);
  }
}
