/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-8
 * Time : 上午11:09
 * ---------------------------------
 */
package org.wukm.boot;

import com.jfinal.core.Const;
import com.jfinal.kit.FileKit;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.server.IServer;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.nio.file.Paths;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-8
 * Time : 上午11:09
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class JettyServer implements IServer{
    private String webAppDir;
    private int port;
    private String context;
    private boolean running = false;
    private org.eclipse.jetty.server.Server server;
    private WebAppContext webApp;
    private String dataDir;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JettyServer(String webAppDir, int port, String context,String dataDir) {
        this(webAppDir,port,context);
        this.dataDir = dataDir;

    }

    public JettyServer(String webAppDir, int port, String context) {
        if (webAppDir == null) {
            throw new IllegalStateException("Invalid webAppDir of web server: " + webAppDir);
        }
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port of web server: " + port);
        }
        if (StrKit.isBlank(context)) {
            throw new IllegalStateException("Invalid context of web server: " + context);
        }

        this.webAppDir = webAppDir;
        this.port = port;
        this.context = context;
        // this.scanIntervalSeconds = scanIntervalSeconds;
    }

    public void start() {
        if (!running) {
            try {
                running = true;
                doStart();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                LogKit.error(e.getMessage(), e);
            }
        }
    }

    public void stop() {
        if (running) {
            try {server.stop();} catch (Exception e) {LogKit.error(e.getMessage(), e);}
            running = false;
        }
    }

    private void doStart() {
        if (!available(port)) {
            throw new IllegalStateException("port: " + port + " already in use!");
        }

        deleteSessionData();

        System.out.println("Starting JFinal " + Const.JFINAL_VERSION);
        server = new org.eclipse.jetty.server.Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        server.addConnector(connector);
        webApp = new WebAppContext();
        webApp.setThrowUnavailableOnStartupException(true);	// 在启动过程中允许抛出异常终止启动并退出 JVM
        webApp.setContextPath(context);
        webApp.setResourceBase(webAppDir);	// webApp.setWar(webAppDir);
        webApp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        webApp.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");	// webApp.setInitParams(Collections.singletonMap("org.mortbay.jetty.servlet.Default.useFileMappedBuffer", "false"));
        persistSession(webApp);

        server.setHandler(webApp);
        // changeClassLoader(webApp);

        // configureScanner
//		if (scanIntervalSeconds > 0) {
//			Scanner scanner = new Scanner(PathKit.getRootClassPath(), scanIntervalSeconds) {
//				public void onChange() {
//					try {
//						System.err.println("\nLoading changes ......");
//						webApp.stop();
//						JFinalClassLoader loader = new JFinalClassLoader(webApp, getClassPath());
//						webApp.setClassLoader(loader);
//						webApp.start();
//						System.err.println("Loading complete.");
//					} catch (Exception e) {
//						System.err.println("Error reconfiguring/restarting webapp after change in watched files");
//						LogKit.error(e.getMessage(), e);
//					}
//				}
//			};
//			System.out.println("Starting scanner at interval of " + scanIntervalSeconds + " seconds.");
//			scanner.start();
//		}

        try {
            System.out.println("Starting web server on port: " + port);
            server.start();
            System.out.println("Starting Complete. Welcome To The JFinal World :)");
            server.join();
        } catch (Exception e) {
            LogKit.error(e.getMessage(), e);
            System.exit(100);
        }
        return;
    }

//	private void changeClassLoader(WebAppContext webApp) {
//		try {
//			String classPath = getClassPath();
//			JFinalClassLoader jfcl = new JFinalClassLoader(webApp, classPath);
//			// jfcl.addClassPath(classPath);
//			webApp.setClassLoader(jfcl);
//		} catch (IOException e) {
//			LogKit.error(e.getMessage(), e);
//		}
//	}

//	private String getClassPath() {
//		return System.getProperty("java.class.path");
//	}

    private void deleteSessionData() {
        try {
            FileKit.delete(new File(getStoreDir()));
        }
        catch (Exception e) {
            LogKit.logNothing(e);
        }
    }

    private String getStoreDir() {
        String storeDir;
        if(StrKit.isBlank(this.dataDir)) {
            storeDir = PathKit.getWebRootPath() + "/../../session_data" + context;
            if ("\\".equals(File.separator)) {
                storeDir = storeDir.replaceAll("/", "\\\\");
            }
        } else {
            File dataDir = Paths.get(this.dataDir).toFile();
            if(dataDir.exists() && dataDir.isDirectory()){
                storeDir = Paths.get(this.dataDir,"session_data").toAbsolutePath().toString();
            } else {
                storeDir = PathKit.getWebRootPath() + "/../../session_data" + context;
                if ("\\".equals(File.separator)) {
                    storeDir = storeDir.replaceAll("/", "\\\\");
                }
            }
        }
        logger.info("storeDir:{}", storeDir);
        return storeDir;
    }

    private void persistSession(WebAppContext webApp) {
        String storeDir = getStoreDir();

        SessionManager sm = webApp.getSessionHandler().getSessionManager();
        if (sm instanceof HashSessionManager) {
            ((HashSessionManager)sm).setStoreDirectory(new File(storeDir));
            return ;
        }

        HashSessionManager hsm = new HashSessionManager();
        hsm.setStoreDirectory(new File(storeDir));
        SessionHandler sh = new SessionHandler();
        sh.setSessionManager(hsm);
        webApp.setSessionHandler(sh);
    }

    private static boolean available(int port) {
        if (port <= 0) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            LogKit.logNothing(e);
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    // should not be thrown, just detect port available.
                    LogKit.logNothing(e);
                }
            }
        }
        return false;
    }
}
