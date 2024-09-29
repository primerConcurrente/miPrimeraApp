package io.primerconcurrente.mi_primeraapp.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import io.primerconcurrente.mi_primeraapp.service.SensorTempertauraService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the nombre value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = SensorTempertauraNombreUnique.SensorTempertauraNombreUniqueValidator.class
)
public @interface SensorTempertauraNombreUnique {

    String message() default "{Exists.sensorTempertaura.nombre}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SensorTempertauraNombreUniqueValidator implements ConstraintValidator<SensorTempertauraNombreUnique, String> {

        private final SensorTempertauraService sensorTempertauraService;
        private final HttpServletRequest request;

        public SensorTempertauraNombreUniqueValidator(
                final SensorTempertauraService sensorTempertauraService,
                final HttpServletRequest request) {
            this.sensorTempertauraService = sensorTempertauraService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(sensorTempertauraService.get(Long.parseLong(currentId)).getNombre())) {
                // value hasn't changed
                return true;
            }
            return !sensorTempertauraService.nombreExists(value);
        }

    }

}
