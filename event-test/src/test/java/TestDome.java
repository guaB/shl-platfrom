import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/3
 */
public class TestDome {


    @Test
    public void  verify_test(){
        List mock = mock(List.class);
        mock.add(1);
        mock.clear();
        verify(mock).add(1);
        verify(mock).clear();
    }

    @Test
    public void when_thenReturn(){
        Iterator mock = mock(Iterator.class);
        when(mock.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = mock.next() + " " + mock.next() + " " + mock.next();

        assertEquals("hello world world",result);

    }
}
