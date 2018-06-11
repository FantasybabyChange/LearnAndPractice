package singleton;

/**
 * InnerSingleTon class
 * 通过内部类实现单例
 *
 * @author liuxi
 * @date 2018-06-11
 */
public class InnerSingleTon {
    private InnerSingleTon() {
        System.out.println("StaticSingleton is create");
    }

    private static class SingletonHolder {
        private static InnerSingleTon instance = new InnerSingleTon();
    }

    public static InnerSingleTon getInstance() {
        return SingletonHolder.instance;
    }
}
