package UrlCustomized.repository;

import UrlCustomized.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, String> {

    Url findByShortUrl (String url);

    Optional<List<Url>> findUrlByExpiredAtBefore(LocalDateTime dateTime);
}
