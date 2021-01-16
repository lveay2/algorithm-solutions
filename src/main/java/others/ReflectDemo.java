package others;

/**
 * @author lveay2
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        // 使用类的class属性来获取该类对应的Class对象。可测试用
        Class<Student> c1 = Student.class;
        System.out.println(c1);

        Class<Student> c2 = Student.class;
        System.out.println(c1 == c2);

        System.out.println("-------");
        // 使用对象的getClass()方法，返回该对象所属类对应的Class对象
        Student s = new Student();
        Class<? extends Student> c3 = s.getClass();
        System.out.println(c1 == c3);
        // 使用Class类中的静态方法forName(String className)。灵活性更高，可配置
        System.out.println("-------");
        Class<?> c4 = Class.forName("others.Student");
        System.out.println(c1 == c4);
    }

}
