package org.freeshr.terminology.launch;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;


public class TRSessionListener implements HttpSessionListener {

    private int sessionCount = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionCount++;
        HttpSession session = se.getSession();
        System.out.println(String.format("Session created [%s]. Total sessions = %s", System.currentTimeMillis(), sessionCount));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionCount--;
        System.out.println(String.format("Session destroyed [%s]. Total sessions = %s", System.currentTimeMillis(), sessionCount));
    }
}
