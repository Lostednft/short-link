package com.repository;

import com.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, String> {

    Url findByShortUrl (String url);
}
