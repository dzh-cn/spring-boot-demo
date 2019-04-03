package dong.stadyJunit;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIn;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

/**
 * @author: dongzhihua
 * @time: 2019/3/15 11:18:19
 */
public class ParamJunit {

    @BeforeEach
    public void before() {
//        System.out.println("before");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "c"})
    public void stringTest(String c) {
        System.out.println(c);
    }

    @ParameterizedTest
    @EnumSource(value = Ttype.class, names = {"accept", "refuse"})
    public void testEnum(Ttype t) {
        Assert.assertTrue(t == Ttype.accept || t == Ttype.refuse);
    }

    @ParameterizedTest
    @MethodSource("strGenerator")
    public void testMethodParam(int age, String name) {
        System.out.println(age + name);
        MatcherAssert.assertThat(age, Is.is(new IsIn<>(new Integer[]{18, 20})));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/person.csv")
    public void csvTest(int age, String name) {
        System.out.println(age + name);
    }

    static Stream<Arguments> strGenerator() {
        return Stream.of(Arguments.of(18, "lily"), Arguments.of(19, "lucy"));
    }

    enum Ttype {
        accept, refuse
    }

}
