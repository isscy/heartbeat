package cn.ff.user;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IOTest implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(IOTest.class);


    @Test
    public void test2() throws IOException {
        Path path = Paths.get("/Users/fengfan/doc/hrp", "hrp文档");
        byte[] bytes = Files.readAllBytes(path);
        String content = new String(bytes, StandardCharsets.UTF_8);
//        LOGGER.info(content);
        List<String> str = Files.readAllLines(path);
        int i = 1;
        for (String s : str) {

            LOGGER.info(i + " : " + s);
            i++;
        }

    }


    @Test
    public void test() throws IOException, ClassNotFoundException {

        //LOGGER.warn(System.getProperty("line.separator"));


        /*FileInputStream fis = new FileInputStream("src/test.txt");
        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("src/test.txt")
                )
        );
        int temp = 0;
        while ( (temp = fis.read()) != -1){
            LOGGER.info((char) temp  + "");
        }
        InputStreamReader in = new InputStreamReader(new FileInputStream("src/test.txt"),  StandardCharsets.UTF_8);
        int temp = 0;
        while ( (temp = in.read()) != -1){
            LOGGER.info((char) temp  + "");
        }

        PrintWriter pw = new PrintWriter(new FileWriter("src/test.txt"), true);
        pw.println("🐦");
        pw.print("harry");
        pw.print(" 马上即");
        pw.println();
        */

        String defaultCharsetName = Charset.defaultCharset().displayName();

        System.out.println("defaultCharsetName:" + defaultCharsetName);

        Eve eve = new Eve("a", "小明", "123");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/test.txt"));
        oos.writeObject(eve);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test.txt"));
        Eve eve2 = (Eve) ois.readObject();


    }


    public static void main(String[] args) throws IOException {
        LOGGER.info(System.getProperty("user.dir"));
        Eve eve = new Eve("a", "小明", "123");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/test.txt"));
        oos.writeObject(eve);

    }


    static class Eve implements Serializable {
        private String id;
        private String name;
        private String pwd;

        private Eve() {
        }

        public Eve(String id, String name, String pwd) {
            this.id = id;
            this.name = name;
            this.pwd = pwd;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
}
