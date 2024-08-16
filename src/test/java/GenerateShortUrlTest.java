import com.model.Url;
import com.service.UrlService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GenerateShortUrlTest {

    @Mock
    private UrlService urlService;

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
}
