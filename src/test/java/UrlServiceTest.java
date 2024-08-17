import UrlCustomized.model.Url;
import UrlCustomized.repository.UrlRepository;
import UrlCustomized.service.UrlService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @Mock
    private UrlService urlService;

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private Url url;

    @BeforeEach
    public void setup(){

        url.setLongUrl("https://google.com.br");
        url.setShortUrl("4xYPo3");
        url.setExpiredAt(LocalDateTime.now().plusSeconds(600));
    }

    @DisplayName("Testing GenerateShortUrl Method")
    @Test
    public void givenUrl_whenGenerateNewUrl_thenReturnShortUrl(){

        //given
        BDDMockito.given(urlService.GenerateShortUrl()).willReturn(("K9aWc9"));

        //when
        String teste = urlService.GenerateShortUrl();

        //then
        Assertions.assertThat(teste).isNotNull();
        Assertions.assertThat(teste).isAlphanumeric();
        Assertions.assertThat(teste).contains("K9aWc9");

    }

    @DisplayName("Testing ShortenerUrl Method")
    @Test
    public void givenLongUrl_whenSaveUrlObject_thenReturnUrlObject(){

        //given
        BDDMockito.given(urlService.ShortenerUrl(url.getLongUrl()))
                .willReturn("4xYPo3");

        //when
        String shortUrlTemp = urlService.ShortenerUrl(url.getLongUrl());

        //then
        Assertions.assertThat(shortUrlTemp).isNotNull();
        Assertions.assertThat(shortUrlTemp).isEqualTo(url.getShortUrl());
    }

    @DisplayName("Testing deleteUrlExpired Method")
    @Test
    public void givenUrlObjectSaved_whenDeleteUrlExpired_thenUrlExpiredDeleted(){

        //given
        url.setExpiredAt(LocalDateTime.now().minusHours(2L));
        urlRepository.save(url);

        //when
        urlService.deleteUrlExpired();
        List<Url> all = urlRepository.findAll();

        //then
        Assertions.assertThat(all).isNullOrEmpty();
    }

    @DisplayName("Testing ValidateUrl Method")
    @Test
    public void givenLongUrl_whenValidateUrl_thenReturnBoolean(){

        //given
        BDDMockito.given(urlService.UrlValidate("https://google.com")).willReturn(true);
        BDDMockito.given(urlService.UrlValidate("https://google.com.br")).willReturn(true);
        BDDMockito.given(urlService.UrlValidate("google.com")).willReturn(true);
        BDDMockito.given(urlService.UrlValidate("google")).willReturn(false);

        //when
        boolean httpsGoogleComBr = urlService.UrlValidate("https://google.com.br");
        boolean googleCom = urlService.UrlValidate("google.com");
        boolean google = urlService.UrlValidate("google");
        boolean httpsGoogleCom = urlService.UrlValidate("https://google.com");

        //then
        Assertions.assertThat(httpsGoogleCom).isEqualTo(true);
        Assertions.assertThat(httpsGoogleComBr).isEqualTo(true);
        Assertions.assertThat(googleCom).isEqualTo(true);
        Assertions.assertThat(google).isEqualTo(false);

    }
}
