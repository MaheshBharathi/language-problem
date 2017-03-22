package au.com.lf.controller;

import au.com.lf.service.LanguageFinderService;
import au.com.lf.service.LanguageFinderServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mahesh on 22/03/17.
 */
public class FindLanguageController extends HttpServlet {

    private static final String VIEW = "WEB-INF/jsp/findLanguage.jsp";
    private static final String FILE_LOCATION = "/etc/lang_files";

    private static final String INVALID_REQUEST = "Please input valid word(Only one word without any numeric or special characters)";

    //TODO: Not a good practice. I am using tomcat for this demo. Tomcat doesn't support CDI out of the box. Use some library to enable Dependency Injection/CDI
    LanguageFinderService languageFinderService = new LanguageFinderServiceImpl();


    /**
     * This method is invoked when user hits language finder url.
     * Forwards to jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    /**
     * This method is invoked when user hit find language.
     * If request is valid calls languageFinderService to find language
     * Returns a validation error message for invalid requests
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(isValidRequest(request)) {
            request.setAttribute("response", languageFinderService.findLanguage(StringUtils.trim(request.getParameter("input")), FILE_LOCATION));
        }

        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    private boolean isValidRequest(HttpServletRequest request) {
        String input = request.getParameter("input");

        if(StringUtils.isBlank(input) || !StringUtils.trim(input).matches("[a-zA-Z]+")) {
            request.setAttribute("response", INVALID_REQUEST);
            return false;
        }
        return true;
    }
}
