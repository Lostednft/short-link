package com.service;

import com.model.Url;
import com.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class  UrlService {

    @Autowired
    public UrlRepository urlRepository;

    private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

    public String ShortedUrl(String longUrl){

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
}
