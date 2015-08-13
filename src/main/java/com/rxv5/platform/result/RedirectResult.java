package com.rxv5.platform.result;

import static javax.servlet.http.HttpServletResponse.SC_FOUND;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class RedirectResult implements Result {

	private static final long serialVersionUID = 6316947346435301270L;

	private static final Logger LOG = LoggerFactory.getLogger(RedirectResult.class);

	protected boolean prependServletContext = true;

	protected ActionMapper actionMapper;

	protected int statusCode = SC_FOUND;

	@Inject
	public void setActionMapper(ActionMapper mapper) {
		this.actionMapper = mapper;
	}

	public void setStatusCode(int code) {
		this.statusCode = code;
	}

	/**
	 * Sets whether or not to prepend the servlet context path to the redirected URL.
	 *
	 * @param prependServletContext <tt>true</tt> to prepend the location with the servlet context path,
	 *                              <tt>false</tt> otherwise.
	 */
	public void setPrependServletContext(boolean prependServletContext) {
		this.prependServletContext = prependServletContext;
	}

	/**
	 * Redirects to the location specified by calling {@link HttpServletResponse#sendRedirect(String)}.
	 *
	 * @param finalLocation the location to redirect to.
	 * @param invocation    an encapsulation of the action execution state.
	 * @throws Exception if an error occurs when redirecting.
	 */
	public void execute(ActionInvocation invocation) throws Exception {
		String finalLocation = (String) ActionContext.getContext().get("location");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		if (isPathUrl(finalLocation)) {
			if (!finalLocation.startsWith("/")) {
				ActionMapping mapping = actionMapper.getMapping(request, Dispatcher.getInstance()
						.getConfigurationManager());
				String namespace = null;
				if (mapping != null) {
					namespace = mapping.getNamespace();
				}
				if ((namespace != null) && (namespace.length() > 0) && (!"/".equals(namespace))) {
					finalLocation = namespace + "/" + finalLocation;
				} else {
					finalLocation = "/" + finalLocation;
				}
			}

			// if the URL's are relative to the servlet context, append the servlet context path
			if (prependServletContext && (request.getContextPath() != null) && (request.getContextPath().length() > 0)) {
				finalLocation = request.getContextPath() + finalLocation;
			}

			finalLocation = response.encodeRedirectURL(finalLocation);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Redirecting to finalLocation " + finalLocation);
		}

		sendRedirect(response, finalLocation);
	}

	/**
	 * Sends the redirection.  Can be overridden to customize how the redirect is handled (i.e. to use a different
	 * status code)
	 *
	 * @param response The response
	 * @param finalLocation The location URI
	 * @throws IOException
	 */
	protected void sendRedirect(HttpServletResponse response, String finalLocation) throws IOException {
		if (SC_FOUND == statusCode) {
			response.sendRedirect(finalLocation);
		} else {
			response.setStatus(statusCode);
			response.setHeader("Location", finalLocation);
			response.getWriter().write(finalLocation);
			response.getWriter().close();
		}

	}

	private static boolean isPathUrl(String url) {
		// filter out "http:", "https:", "mailto:", "file:", "ftp:"
		// since the only valid places for : in URL's is before the path specification
		// either before the port, or after the protocol
		return (url.indexOf(':') == -1);
	}
}
