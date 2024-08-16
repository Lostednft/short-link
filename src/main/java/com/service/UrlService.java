package com.service;

import com.model.Url;
import com.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    public UrlRepository urlRepository;


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
}
