import org.junit.Test;

/**
 * @author psl
 */
public class TestDemo1 {
    @Test
    public void fun1(){
        String s = null;
        int a = Integer.parseInt(s);
        System.out.println(a);
    }
    @Test
    public void fun2(){
        String s = "";
    }
	@Test
    public void fun3(){
        System.out.println("你好");
    }
}
