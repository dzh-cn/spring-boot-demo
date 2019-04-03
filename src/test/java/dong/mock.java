package dong;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

/**
 * @author: dongzhihua
 * @time: 2019/3/15 10:04:45
 */
public class mock {
    @Before
    public void before() {
        System.out.println("before");
    }
    @Test
    public void test() {
        List<String> list = Mockito.mock(List.class);
        BDDMockito.given(list.get(10)).willReturn("given return!!");
        Mockito.when(list.get(100)).thenReturn("you are right!");
        System.out.println(list.get(10));
        System.out.println(list.get(100));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void test2(String str) {
        System.out.println(str);
    }
}
