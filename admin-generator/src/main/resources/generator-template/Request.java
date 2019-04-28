package ${basePath}.entity.request${subDir};

import ${basePath}.entity.domain${subDir}.${name};
import ${basePath}.system.entity.request.AbstractEntityRequest;
import ${basePath}.system.entity.domain.AbstractEntity;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Date;
import java.util.List;
import lombok.*;

/**
 * ${description}.
 *
 * @author ramer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ${name}Request extends AbstractEntityRequest {
${fieldList}
}
