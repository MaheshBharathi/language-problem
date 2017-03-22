package au.com.lf.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by mahesh on 22/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LanguageFinderServiceImplTest {

    @InjectMocks
    private LanguageFinderServiceImpl languageFinderService;

    @Test
    public void findLanguageShouldFindLanguage() {
        String result = languageFinderService.findLanguage("name", "/etc/test_lang_files");

        assertThat(result, is(equalTo("English")));
    }

    @Test
    public void findLanguageShouldFindLanguageIgnoreCase() {
        String result = languageFinderService.findLanguage("NAME", "/etc/test_lang_files");

        assertThat(result, is(equalTo("English")));
    }


    @Test
    public void findLanguageShouldReturnServerErrorWhenNoFileAvailable() {
        String result = languageFinderService.findLanguage("name", "/etc/wrong_location");

        assertThat(result, is(equalTo("Something went wrong at server, Please try again later")));
    }

    @Test
    public void findLanguageShouldReturnMessageWhenInputNotAvailableInAnyOfFiles() {
        String result = languageFinderService.findLanguage("fdsfj", "/etc/test_lang_files");

        assertThat(result, is(equalTo("Sorry! The System could not find language of the word you entered")));
    }
}