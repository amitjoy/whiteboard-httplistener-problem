package com.amitinside.sample.http.extender.servlet;

import static org.osgi.service.component.annotations.ServiceScope.PROTOTYPE;
import static org.osgi.service.http.HttpContext.REMOTE_USER;
import static org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardContextSelect;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletName;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

@Component(service = Servlet.class, scope = PROTOTYPE)
@HttpWhiteboardServletName("MyServlet1")
@HttpWhiteboardServletPattern("/sample1/*")
@HttpWhiteboardContextSelect("(" + HTTP_WHITEBOARD_CONTEXT_NAME + "=SampleContext1)")
public final class SampleServlet1 extends HttpServlet {

    private static final long serialVersionUID = -3927850336353311916L;

    @Reference(service = LoggerFactory.class)
    private Logger logger;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        logger.debug("[SampleServlet] Processing GET Request: {}", request.getParameterMap());
        response.getWriter().print("Servlet1 Response");
        createSession(request, response);
    }
    
    private void createSession(final HttpServletRequest httpRequest, final HttpServletResponse response) {
            final HttpSession session = httpRequest.getSession();
            session.setAttribute(REMOTE_USER, "MyUser");
    }
}
