package validator;

import org.hibernate.validator.constraints.Length;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * validator探索
 *
 * @author: dongzhihua
 * @time: 2018/7/20 9:15:16
 */
public class ValidatorHello {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void main(String[] args) {
        Application application = new Application();
        application.applicationName = "京东支付111";
        application.model = new ArrayList<>();
        Param<Application> param = new Param();
        param.param = application;
        Set<ConstraintViolation<Param<Application>>> constraintViolations = validator.validate(param);
        constraintViolations.forEach(e -> System.out.println(String.format("%s: %s", e.getPropertyPath(), e.getMessage())));
//        Set<ConstraintViolation<Application>> validate = validator.validate(param.param, GroupInsert.class);
//        validate.forEach(e -> System.out.println(String.format("%s: %s", e.getPropertyPath(), e.getMessage())));

    }

    static class Param<T> {
        @NotNull @Valid
        T param;
    }


    static class Application {
        @NotNull(groups = GroupUpdate.class)
        private Long applicationId;
        @NotNull(groups = GroupInsert.class)
        @Length(min = 5, max = 6, groups = GroupInsert.class)
        private String applicationName;
        @NotNull
        private Date applyDate;
        @NotNull(groups = GroupQuery.class)
        private ApplicationStatus applicationStatus;
        @NotNull(groups = GroupInsert.class)
        @NotEmpty(groups = GroupInsert.class)
        private List<String> model;
    }

    enum ApplicationStatus {
        one, two;
    }

    interface GroupInsert {
    }

    interface GroupUpdate {
    }

    interface GroupQuery {
    }
}
