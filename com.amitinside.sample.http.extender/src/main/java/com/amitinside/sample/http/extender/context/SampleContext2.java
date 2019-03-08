package com.amitinside.sample.http.extender.context;

import static org.osgi.service.component.annotations.ServiceScope.BUNDLE;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.context.ServletContextHelper;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardContext;

import com.amitinside.sample.http.extender.session.ApplicationSession;

@Component(service = { ServletContextHelper.class, SampleContext2.class }, scope = BUNDLE)
@HttpWhiteboardContext(name = "SampleContext2", path = "/root2")
public final class SampleContext2 extends ServletContextHelper {
	
	@Reference
    private ApplicationSession applicationSession;

	@Override
    public URL getResource(final String name) {
    	System.out.println("MyResource2 will be retrieved");
        return null;
    }
	
	@Override
    public boolean handleSecurity(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        if (!applicationSession.isSessionValid()) {
            response.sendRedirect("/root1/sample1");
        }
        return true;
    }

}
