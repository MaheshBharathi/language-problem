package au.com.lf.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import au.com.lf.service.LanguageFinderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mahesh on 22/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class FindLanguageControllerTest {

    @InjectMocks
    FindLanguageController controller;

    @Mock
    private LanguageFinderService languageFinderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/findLanguage.jsp"))).thenReturn(requestDispatcher);

        controller.doGet(request, response);

        verifyMethodCalls();
    }

    @Test
    public void doPostShouldReturnValidationErrorWhenInputIsNull() throws ServletException, IOException {
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/findLanguage.jsp"))).thenReturn(requestDispatcher);

        controller.doPost(request, response);

        verifyMethodCalls();
        verify(languageFinderService, never()).findLanguage(any(String.class), any(String.class));
        verify(request, times(1)).setAttribute(eq("response"), eq("Please input valid word(Only one word without any numeric or special characters)"));
    }

    @Test
    public void doPostShouldReturnValidationErrorWhenInputHasSpecialChars() throws ServletException, IOException {
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/findLanguage.jsp"))).thenReturn(requestDispatcher);
        when(request.getParameter(eq("input"))).thenReturn("hell$");

        controller.doPost(request, response);

        verifyMethodCalls();
        verify(languageFinderService, never()).findLanguage(any(String.class), any(String.class));
        verify(request, times(1)).setAttribute(eq("response"), eq("Please input valid word(Only one word without any numeric or special characters)"));
    }

    @Test
    public void doPostShouldReturnLanguageForValidRequest() throws ServletException, IOException {
        when(request.getRequestDispatcher(eq("WEB-INF/jsp/findLanguage.jsp"))).thenReturn(requestDispatcher);
        when(request.getParameter(eq("input"))).thenReturn("today");
        when(languageFinderService.findLanguage(eq("today"), any(String.class))).thenReturn("English");

        controller.doPost(request, response);

        verifyMethodCalls();
        verify(languageFinderService, times(1)).findLanguage(eq("today"), any(String.class));
        verify(request, times(1)).setAttribute(eq("response"), eq("English"));
    }

    private void verifyMethodCalls() throws ServletException, IOException {
        verify(requestDispatcher, times(1)).forward(request, response);
        verify(request, times(1)).getRequestDispatcher("WEB-INF/jsp/findLanguage.jsp");

    }

}