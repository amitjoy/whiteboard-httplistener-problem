package com.amitinside.sample.http.extender.session;

import static org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardContextSelect;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardListener;

@Component(service = { ApplicationSession.class, HttpSessionListener.class })
@HttpWhiteboardListener
@HttpWhiteboardContextSelect("(" + HTTP_WHITEBOARD_CONTEXT_NAME + "=SampleContext1)")
public final class ApplicationSession implements HttpSessionListener {

    private final AtomicInteger activeSessions = new AtomicInteger();

    @Override
    public void sessionCreated(final HttpSessionEvent sessionEvent) {
        activeSessions.incrementAndGet();
        System.out.println("Session Listener Triggered. Session Created");
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent sessionEvent) {
        activeSessions.decrementAndGet();
        System.out.println("Session Listener Triggered. Session Destroyed.");
    }

    public boolean isSessionValid() {
        return activeSessions.get() != 0;
    }

}
