package org.freeshr.terminology.launch;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.deploy.FilterDef;
import org.apache.catalina.deploy.FilterMap;
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
        Context context = tomcat.addWebapp(getenv("CONTEXT_PATH"), new File(getenv("WAR_DIRECTORY")).getAbsolutePath());
        context.setUseHttpOnly(false);
        /*below code has no effect, the web.xml of openmrs file overrides this, irrespective of whether session-config is set or not */
        //context.setSessionTimeout(5);

        //context.addApplicationListener(TRSessionListener.class.getName());
        addSessionInvalidationFilter(context);

        tomcat.start();
        tomcat.getServer().await();
    }

    private static void addSessionInvalidationFilter(Context context) {
        FilterDef sessionInvalidatorFilter = new FilterDef();
        sessionInvalidatorFilter.setFilterName(TerminologyResourceFilter.class.getSimpleName());
        sessionInvalidatorFilter.setFilterClass(TerminologyResourceFilter.class.getName());
        context.addFilterDef(sessionInvalidatorFilter);

        FilterMap filter1mapping = new FilterMap();
        filter1mapping.setFilterName(TerminologyResourceFilter.class.getSimpleName());
        filter1mapping.addURLPattern("/ws/rest/v1/tr/*");
        filter1mapping.addURLPattern("/ws/atomfeed/*");
        context.addFilterMap(filter1mapping);
    }
}
