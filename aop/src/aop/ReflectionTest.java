package aop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Created by mac on 2018/3/31.
 */
public class ReflectionTest {
    public static void main(String[] args){

        //read class name from command line args or uer input
        String name;
        if(args.length > 0) name = args[0];
        else{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date):");
            name = in.next();
        }
        try{
            //类信息
            //这一句可能发生ClassNotFoundException异常
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("Class " + name);
            if(supercl != null && supercl != Object.class)System.out.print(" extend " + supercl.getName());
            System.out.print("\n{\n");
            //类的构造方法信息
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");


        }catch (ClassNotFoundException e){

            e.printStackTrace();
            //System.out.println("gg");
        }
        System.exit(0);
    }

    public static void printConstructors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();
        for(Constructor c : constructors){
            String name = c.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers + " ");
            System.out.print(name + "(");

            //print parameter types
            Class[] paramTypes = c.getParameterTypes();
            for(int j = 0;j<paramTypes.length;j++){

                if(j>0)System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");

        }
    }

    public static void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();
        for(Method method :methods){
            Class retType = method.getReturnType();
            String name = method.getName();
            System.out.print("  ");

            String modifiers = Modifier.toString(method.getModifiers());
            if(modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " ");
            System.out.print(name + "(");

            Class[] paramTypes = method.getParameterTypes();
            for(int j = 0;j<paramTypes.length;j++){

                if(j>0)System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");


        }

    }
    public static void printFields(Class cl){
        
        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            String modifiers = Modifier.toString(field.getModifiers());
            String name = field.getName();
            System.out.print("  ");
            if(modifiers.length()>0) System.out.print(modifiers + " ");
            Class type = field.getType();

            System.out.println(type.getName() + " " + name + ";");

        }
    }

}
