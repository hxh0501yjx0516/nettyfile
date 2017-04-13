package com.example.server.component;

import com.example.server.util.FileTransferProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
@Service
public class FileTransferServer {

    private static Logger log = Logger.getLogger(FileTransferServer.class);

    @PostConstruct
    public static void bind() throws Exception {
        Thread t = new Thread(new Runnable() {
            public void run() {
                init();
                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                try {
                    ServerBootstrap b = new ServerBootstrap();
                    b.group(bossGroup, workerGroup)
                            .channel(NioServerSocketChannel.class)
                            .option(ChannelOption.SO_BACKLOG, 1024)
                            .childHandler(new FileChannelInitializer());

//            log.info("bind port:" + port);

                    ChannelFuture f = b.bind(10012).sync();
                    System.out.println("文件服务器启动成功！端口号：" + 10012);
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        });
        t.start();


    }

    private static void init() {
        try {
            //请把加载属性文件放在 加载日志配置的上面，因为读取日志输出的目录配置在 属性文件里面
            FileTransferProperties.load("classpath:application.properties");

            System.setProperty("WORKDIR", FileTransferProperties.getString("WORKDIR", "/"));

//            PropertyConfigurator.configure(new FileSystemResourceLoader().getResource(
//                    "classpath:log4j.xml").getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
