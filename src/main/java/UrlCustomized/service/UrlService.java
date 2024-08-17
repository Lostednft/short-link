package UrlCustomized.service;

import UrlCustomized.model.Url;
import UrlCustomized.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class  UrlService {

    @Autowired
    public UrlRepository urlRepository;

    public String ShortenerUrl(String longUrl){

        if(!UrlValidate(longUrl))
            throw new IllegalArgumentException("URL dont allowed, try like ex: google.com");

        if(longUrl.isEmpty())
            throw new NullPointerException("Url its required for create short url.");

        String shortUrl = GenerateShortUrl();
        Url url= new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        url.setExpiredAt(LocalDateTime.now().plusDays(30));
        urlRepository.save(url);

        return shortUrl;

    }

    public String GenerateShortUrl(){
        String alphaNums = "QAZXSWECVFRTGBNHYUMKIOLPqazxswedcvfrtgbnhyujmkiolp0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random rand = new Random();
        int count = rand.nextInt(4,8);

        for (int i = 0; i < count; i++) {
            shortUrl.append(alphaNums.charAt(rand.nextInt(alphaNums.length())));
        }

        return shortUrl.toString();
    }

    public void deleteUrlExpired(){

        List<Url> urls = urlRepository.findUrlByExpiredAtBefore(LocalDateTime.now()).orElseThrow();
        urlRepository.deleteAll(urls);
    }

    public boolean UrlValidate(String longUrl){

        String regex = "^(https?://)?[a-zA-Z0-9]+\\.com(\\.br)?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(longUrl);
        return matcher.matches();
    }
}
