package com.amitinside.sample.http.extender.context;

import static org.osgi.service.component.annotations.ServiceScope.BUNDLE;

import java.net.URL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.context.ServletContextHelper;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardContext;

@Component(service = { ServletContextHelper.class, SampleContext1.class }, scope = BUNDLE)
@HttpWhiteboardContext(name = "SampleContext1", path = "/root1")
public final class SampleContext1 extends ServletContextHelper {

    @Override
    public URL getResource(final String name) {
    	System.out.println("MyResource2 will be retrieved");
        return null;
    }

}
