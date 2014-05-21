package org.freeshr.terminology.launch;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.valueOf;
import static java.lang.System.getenv;

public class Main {

    public static void main(String[] args) throws LifecycleException, ServletException, IOException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(valueOf(getenv("SERVER_PORT")));
        tomcat.setBaseDir(getenv("BASE_DIR"));
        tomcat.addWebapp(getenv("CONTEXT_PATH"), new File(getenv("WAR_DIRECTORY")).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
