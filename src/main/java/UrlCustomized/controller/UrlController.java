package UrlCustomized.controller;

import UrlCustomized.model.Url;
import UrlCustomized.repository.UrlRepository;
import UrlCustomized.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/testando/")
public class UrlController {

    private final UrlService urlService;
    private final UrlRepository urlRepository;

    public UrlController(UrlService urlService, UrlRepository urlRepository) {
        this.urlService = urlService;
        this.urlRepository = urlRepository;
    }

    @PostMapping("/shorting")
    public ResponseEntity<Map<String, String>> saveUrl (@RequestBody Map<String, String> url){

        String newUrl = url.get("url");
        String shortedUrl = urlService.ShortenerUrl(newUrl);

        url.put("url", "https://xxxx.com/"+shortedUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);

    }

    @GetMapping("/{url}")
    public ResponseEntity<String> getUrl (@PathVariable String url){

        Url urlShorted = urlRepository.findByShortUrl(url);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlShorted.getLongUrl())).build();
    }
}