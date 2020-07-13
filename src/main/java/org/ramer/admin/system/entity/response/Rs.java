package org.ramer.admin.system.entity.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;
import javax.annotation.Nonnull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.ramer.admin.system.exception.CommonException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 通用JSON响应.
 *
 * @param <T> the type parameter
 * @author Tang Xiaofeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("通用响应")
@SuppressWarnings("unused")
public class Rs<T> implements Serializable {
  /** 请求处理结果: 成功/失败 */
  @ApiModelProperty(value = "请求处理结果: 成功/失败", example = "true")
  private boolean result = true;

  /** 请求成功时,该值是具体返回内容,否则为空 */
  @ApiModelProperty(value = "请求成功时,该值是具体返回内容,否则为空", example = "{name: '名称',value: '值'}")
  private T data;

  /** {@link ResultCode#desc()} */
  @ApiModelProperty(value = "描述", example = "操作成功")
  private String msg = ResultCode.SUCCESS.desc();

  /** {@link ResultCode#code()} */
  @ApiModelProperty(value = "请求结果CODE", example = "E0001")
  private String code = ResultCode.SUCCESS.code();

  /** 时间戳 */
  @ApiModelProperty(value = "请求响应时间戳", example = "1575435889491")
  private long timestamp = System.currentTimeMillis();

  private static final List<String> excludeUrl = new ArrayList<>();

  static {
    excludeUrl.add("inner");
    excludeUrl.add("outer");
  }

  /**
   * Of rs.
   *
   * @param <R> the type parameter
   * @param result the result
   * @return the rs
   */
  public static <R> Rs<R> of(final boolean result) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setResult(result);
    commonResponse.setData(null);
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param data the data
   * @return the rs
   */
  @SuppressWarnings("unchecked")
  public static <T, R> Rs<R> of(final T data) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setData((R) data);
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <T> the type parameter
   * @param resultCode the result code
   * @return the rs
   */
  // @SuppressWarnings("unchecked")
  @SuppressWarnings("StatementWithEmptyBody")
  public static <T> Rs<T> of(final ResultCode resultCode) {
    Rs<T> commonResponse = new Rs<>();
    // 如果数据为null,处理为空json.暂时不启用
    if (Objects.isNull(resultCode)) {
      // ServletRequestAttributes attributes =
      //     (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      // if (Objects.nonNull(attributes)) {
      //   final HttpServletRequest request = attributes.getRequest();
      //   final String uri = request.getRequestURI();
      //   // 仅前端请求转换null为空json
      //   if (excludeUrl.stream().noneMatch(url -> url.contains(uri))) {
      //     commonResponse.setData((T) json());
      //   }
      // }
    } else {
      commonResponse.setCode(resultCode.code());
      commonResponse.setMsg(resultCode.desc());
    }
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param result the result
   * @param data the data
   * @return the rs
   */
  @SuppressWarnings("unchecked")
  public static <T, R> Rs<R> of(final boolean result, final T data) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setResult(result);
    commonResponse.setData((R) data);
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param data the data
   * @param msg the msg
   * @return the rs
   */
  @SuppressWarnings("unchecked")
  public static <T, R> Rs<R> of(final T data, final String msg) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setData((R) data);
    commonResponse.setMsg(msg);
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <R> the type parameter
   * @param result the result
   * @param msg the msg
   * @return the rs
   */
  public static <R> Rs<R> of(final boolean result, final String msg) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setResult(result);
    commonResponse.setMsg(msg);
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param result the result
   * @param data the data
   * @param msg the msg
   * @return the rs
   */
  @SuppressWarnings("unchecked")
  public static <T, R> Rs<R> of(final boolean result, final T data, final String msg) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setResult(result);
    commonResponse.setData((R) data);
    commonResponse.setMsg(msg);
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param result the result
   * @param data the data
   * @param resultCode the result code
   * @return the rs
   */
  @SuppressWarnings("unchecked")
  public static <T, R> Rs<R> of(final boolean result, final T data, final ResultCode resultCode) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setResult(result);
    commonResponse.setData((R) data);
    commonResponse.setMsg(resultCode.desc());
    commonResponse.setCode(resultCode.code());
    return commonResponse;
  }

  /**
   * Of rs.
   *
   * @param <R> the type parameter
   * @param result the result
   * @param resultCode the result code
   * @return the rs
   */
  public static <R> Rs<R> of(final boolean result, final ResultCode resultCode) {
    Rs<R> commonResponse = new Rs<>();
    commonResponse.setResult(result);
    commonResponse.setMsg(resultCode.desc());
    commonResponse.setCode(resultCode.code());
    return commonResponse;
  }

  /**
   * 默认执行成功构造器.
   *
   * @param <R> the type parameter
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> ok() {
    return ResponseEntity.ok(Rs.of(true));
  }

  /**
   * Ok response entity.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param data the data
   * @return the response entity
   */
  public static <T, R> ResponseEntity<Rs<R>> ok(final T data) {
    return ResponseEntity.ok(Rs.of(data));
  }

  /**
   * Ok response entity.
   *
   * @param <R> the type parameter
   * @param data the data
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> ok(final ResultCode data) {
    return ResponseEntity.ok(Rs.of(data));
  }

  /**
   * Ok response entity.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param data the data
   * @param resultCode the result code
   * @return the response entity
   */
  public static <T, R> ResponseEntity<Rs<R>> ok(final T data, final ResultCode resultCode) {
    return ResponseEntity.ok(Rs.of(true, data, resultCode));
  }

  /**
   * Ok response entity.
   *
   * @param <T> the type parameter
   * @param <R> the type parameter
   * @param data the data
   * @param msg the msg
   * @return the response entity
   */
  public static <T, R> ResponseEntity<Rs<R>> ok(final T data, final String msg) {
    return ResponseEntity.ok(Rs.of(data, msg));
  }

  /**
   * 成功返回空的{@link Page}对象.
   *
   * @param <R> the type parameter
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<PageImpl<R>>> emptyPage() {
    return ResponseEntity.ok(Rs.of(new PageImpl<>(Collections.emptyList())));
  }

  /**
   * 成功返回空的l{@link List}对象.
   *
   * @param <R> the type parameter
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<List<R>>> emptyList() {
    return ResponseEntity.ok(Rs.of(Collections.emptyList()));
  }

  /**
   * 默认执行失败构造器.
   *
   * @param <R> the type parameter
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> fail() {
    return ResponseEntity.ok(Rs.of(false, ResultCode.ERROR));
  }

  /**
   * Fail response entity.
   *
   * @param <R> the type parameter
   * @param data the data
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> fail(final ResultCode data) {
    return ResponseEntity.ok(Rs.of(false, data));
  }

  /**
   * Fail response entity.
   *
   * @param <R> the type parameter
   * @param msg the msg
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> fail(final String msg) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.of(ResultCode.ERROR.code(), msg)));
  }

  /**
   * Fail response entity.
   *
   * @param <T> the type parameter
   * @param msg the msg
   * @param data the data
   * @return the response entity
   */
  public static <T> ResponseEntity<Rs<T>> fail(final String msg, final T data) {
    return ResponseEntity.ok(Rs.of(false, data, msg));
  }

  /**
   * 无访问权限,拒绝访问.
   *
   * @param <T> the type parameter
   * @return the response entity
   */
  public static <T> ResponseEntity<Rs<T>> forbidden() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Rs.of(false, ResultCode.API_FORBIDDEN));
  }

  /**
   * 凭证过期.
   *
   * @return the response entity
   */
  public static ResponseEntity<Rs<Object>> secretExpire() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(Rs.of(false, ResultCode.API_CERT_SECRET_EXPIRE));
  }

  /**
   * 凭证为空.
   *
   * @return the response entity
   */
  public static ResponseEntity<Rs<Object>> secretEmpty() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(Rs.of(false, ResultCode.API_CERT_SECRET_EMPTY));
  }

  /**
   * 未认证.
   *
   * @return the response entity
   */
  public static ResponseEntity<Rs<Object>> unauthorized() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(Rs.of(false, ResultCode.API_UNAUTHORIZED));
  }

  /**
   * 操作不允许.
   *
   * @param msg the msg
   * @return the response entity
   */
  public static ResponseEntity<Rs<Object>> notAllowed(final String msg) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_NOT_ALLOWED.desc(msg)));
  }

  /**
   * 参数格式不正确.
   *
   * @param <R> the type parameter
   * @param field the field
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> wrongFormat(final String field) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_PARAM_WRONG_FORMAT.desc(field)));
  }

  /**
   * 参数值不正确.
   *
   * @param <R> the type parameter
   * @param field the field
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> wrongValue(final String field) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_PARAM_WRONG_VALUE.desc(field)));
  }

  /**
   * 参数值不能为空
   *
   * @param <R> the type parameter
   * @param field the field
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> canNotBlank(final String field) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_PARAM_CAN_NOT_BLANK.desc(field)));
  }

  /**
   * 参数值未传递
   *
   * @param <R> the type parameter
   * @param field the field
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> notPresent(final String field) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_PARAM_NOT_PRESENT.desc(field)));
  }

  /**
   * 资源不可用
   *
   * @param <R> the type parameter
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> notFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Rs.of(false, ResultCode.API_NOT_FOUND));
  }

  /**
   * 请求Content-type不支持
   *
   * @param <R> the type parameter
   * @param contentType the content type
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> notSupportContentType(final String contentType) {
    return ResponseEntity.ok(
        Rs.of(false, ResultCode.API_CONTENT_TYPE_NOT_SUPPORT.desc(contentType)));
  }

  /**
   * 请求方式不支持
   *
   * @param <R> the type parameter
   * @param method the method
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> notSupportMethod(final String method) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_METHOD_NOT_SUPPORT.desc(method)));
  }

  /**
   * 返回结果过多
   *
   * @param <R> the type parameter
   * @param method the method
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> tooManyResults(final String method) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_TOO_MANY_RESULTS.desc(method)));
  }

  /**
   * 参数值无效.
   *
   * @param <R> the type parameter
   * @param field the field
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> invalid(final String field) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_PARAM_INVALID.desc(field)));
  }

  /**
   * 数据已存在.
   *
   * @param <R> the type parameter
   * @param data the data
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> exist(final String data) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_DATA_EXIST.desc(data)));
  }

  /**
   * 数据不存在.
   *
   * @param <R> the type parameter
   * @param data the data
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> notExist(final String data) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_DATA_NOT_EXIST.desc(data)));
  }

  /**
   * 参数绑定有误.
   *
   * @param <R> the type parameter
   * @param msg the msg
   * @return the response entity
   */
  public static <R> ResponseEntity<Rs<R>> bindError(final String msg) {
    return ResponseEntity.ok(Rs.of(false, ResultCode.API_PARAM_BIND_ERROR.desc(msg)));
  }

  /**
   * 接口调用判断,如果出现网络异常(如中断),抛出{@link CommonException#of(ResultCode)}.
   *
   * @param <R> the type parameter
   * @param responseEntity 接口返回
   * @return 请求数据实体 r
   * @see #requireNonNull(ResponseEntity, ResultCode) #requireNonNull(ResponseEntity,
   *     ResultCode)#requireNonNull(ResponseEntity, ResultCode)
   */
  public static <R> R requireNonNull(ResponseEntity<Rs<R>> responseEntity) {
    return requireNonNull(responseEntity, ResultCode.ERROR);
  }

  /**
   * 接口调用判断.
   *
   * @param <R> the type parameter
   * @param responseEntity REST接口响应
   * @param resultCode 返回该结果码,当满足以下任一条件时:<br>
   *     1. <code>responseEntity</code>为空<br>
   *     2. <code>responseEntity.body</code>为空<br>
   * @return Rs内的对象 r
   */
  public static <R> R requireNonNull(
      final ResponseEntity<Rs<R>> responseEntity, @Nonnull final ResultCode resultCode) {
    if (Objects.isNull(responseEntity) || !responseEntity.hasBody()) {
      throw CommonException.of(resultCode);
    }
    final Rs<R> body = responseEntity.getBody();
    if (!Objects.requireNonNull(body, resultCode.desc).isResult()) {
      throw CommonException.of(ResultCode.of(body.code, body.msg));
    }
    return body.data;
  }

  /**
   * 判断ResultCode是否=SUCCESS <br>
   * null也会返回true
   *
   * @param resultCode the result code
   * @return the boolean
   */
  public static boolean isSuccess(final ResultCode resultCode) {
    return Objects.isNull(resultCode) || Objects.equals(resultCode.code, ResultCode.SUCCESS.code);
  }

  /**
   * 判断ResultCode是否=ERROR <br>
   * null返回false
   *
   * @param resultCode the result code
   * @return the boolean
   */
  public static boolean isError(final ResultCode resultCode) {
    return Objects.nonNull(resultCode) && Objects.equals(resultCode.code, ResultCode.ERROR.code);
  }

  /**
   * Json json instance.
   *
   * @return the json instance
   */
  public static JsonInstance json() {
    return new JsonInstance();
  }

  /** The type Json instance. */
  public static class JsonInstance extends HashMap<String, Object> implements Serializable {
    @Override
    public @Nonnull JsonInstance put(final String key, final Object val) {
      super.put(key, val);
      return this;
    }
  }

  /** The type Json instance serializer. */
  @Slf4j
  public static class JsonInstanceSerializer implements ObjectSerializer {
    @Override
    public void write(
        JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
      SerializeWriter serializeWriter = serializer.out;
      log.info("write:[{}]", object.toString());
      serializeWriter.write(JSON.toJSONString(object));
    }
  }
}