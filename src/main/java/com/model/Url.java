package com.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_tb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String longUrl;
    private String shortUrl;
    private LocalDateTime expiredAt;

    public Url(String urlLong, String urlShort, LocalDateTime expiredAt) {
        this.longUrl = urlLong;
        this.shortUrl = urlShort;
        this.expiredAt = expiredAt;
    }
}
