package cn.ff.user;

import javax.script.ScriptEngine;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Security {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*String.class.getClassLoader();
        EchoServer.class.getClassLoader();*/

        URL url = new URL("file:///Users/fengfan/doc/test/SF.jar");
        URLClassLoader loader = new URLClassLoader(new URL[]{url});
        Class<?> clazz = loader.loadClass("cn.ff.user.StackFull");
        Object obj = clazz.newInstance();
        StackFull sf = (StackFull) obj;
        sf.say();

    }
}
