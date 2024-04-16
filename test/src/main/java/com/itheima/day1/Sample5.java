package com.itheima.day1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Sample5 {
    interface Lambda extends Serializable {
        int calculate(int a, int b);
    }

    static class Server {
        public static void main(String[] args) throws IOException {
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("server start...");
            while (true) {
                Socket s = ss.accept();
                Thread.ofVirtual().start(() -> {
                    try {
                        ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                        Lambda lambda = (Lambda) is.readObject();
                        int a = ThreadLocalRandom.current().nextInt(10);
                        int b = ThreadLocalRandom.current().nextInt(10);
                        System.out.printf("%s %d op %d = %d%n", s.getRemoteSocketAddress().toString(), a, b, lambda.calculate(a, b));
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    // Server 虚拟机端必须有 Client0 这个类，相当于把实现绑定在了服务器端
    static class Client0 {
        int add(int a, int b) {
            return a + b;
        }
    }
    // Server 虚拟机端只需有 Lambda 接口定义，实现与服务器无关
    static class Client1 {
        public static void main(String[] args) throws IOException {
            try(Socket s = new Socket("127.0.0.1", 8080)){
                Lambda lambda = (a, b) -> a + b;
                ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                os.writeObject(lambda);
                os.flush();
            }
        }
    }
    static class Client2 {
        public static void main(String[] args) throws IOException {
            try(Socket s = new Socket("127.0.0.1", 8080)){
                Lambda lambda = (a, b) -> a - b;
                ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                os.writeObject(lambda);
                os.flush();
            }
        }
    }
    static class Client3 {
        public static void main(String[] args) throws IOException {
            try(Socket s = new Socket("127.0.0.1", 8080)){
                Lambda lambda = (a, b) -> a * b;
                ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                os.writeObject(lambda);
                os.flush();
            }
        }
    }
}
