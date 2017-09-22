package test;

import java.util.Date;

public class Test1 {

    public static <T> T creat(Class<T> cls) {
        try {
            T t = cls.newInstance();
            System.err.println(t.getClass().getName());

            return cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Date d = creat(Date.class);
        System.out.println(d);
    }


}
