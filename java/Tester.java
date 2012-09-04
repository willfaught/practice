package com.willfaught;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester
{
    private static void invoke(Method method, Object object)
    {
        try
        {
            method.invoke(object);
        }
        catch (IllegalAccessException e)
        {
            String name = method.getDeclaringClass().getName() + "." + method.getName();
            Assert.log("Error: Cannot invoke " + name + ": " + e);
        }
        catch (InvocationTargetException e)
        {
            Throwable t = e.getCause();
            if (t == null)
            {
                Assert.fail("unexpected=null exception");
            }
            else
            {
                Assert.fail(t);
            }
        }
        catch (Exception e)
        {
            Assert.fail(e);
        }
    }

    public static void main(String[] args)
    {
        List<String> argsList = Arrays.asList(args);
        boolean verbose = argsList.contains("--verbose");
        Assert.verbose(verbose);
        Class<?>[] classes = new Class<?>[]
        {
                ArraysTest.class, ArraySortTest.class, BinarySearchTreeTest.class, CharArrayTest.class, HeapTest.class,
                ListTest.class
        };
        for (Class<?> klass : classes)
        {
            List<Method> befores = new ArrayList<Method>();
            List<Method> tests = new ArrayList<Method>();
            List<Method> afters = new ArrayList<Method>();
            Method[] methods = klass.getDeclaredMethods();
            for (Method method : methods)
            {
                if (method.getAnnotation(Before.class) != null)
                {
                    befores.add(method);
                    continue;
                }
                if (method.getAnnotation(Test.class) != null)
                {
                    tests.add(method);
                    continue;
                }
                if (method.getAnnotation(After.class) != null)
                {
                    afters.add(method);
                    continue;
                }
            }
            Object object = null;
            try
            {
                object = klass.newInstance();
            }
            catch (Exception e)
            {
                Assert.log("Error: Cannot instantiate " + klass.getName() + ": " + e);
                continue;
            }
            Assert.suite(klass.getName());
            for (Method method : befores)
            {
                Assert.test(method.getName());
                invoke(method, object);
            }
            for (Method method : tests)
            {
                Assert.test(method.getName());
                invoke(method, object);
            }
            for (Method method : afters)
            {
                Assert.test(method.getName());
                invoke(method, object);
            }
        }
        int failures = Assert.failures();
        if (failures > 0 || verbose || argsList.contains("--summary"))
        {
            int assertions = Assert.assertions();
            String s = failures + " of " + assertions + " assertion" + (assertions == 1 ? "" : "s") + " failed";
            Assert.log(s);
        }
    }
}