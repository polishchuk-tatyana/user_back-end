package com.user.userapp.domain;

import com.user.userapp.domain.enums.PhotoSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import jakarta.persistence.*;

@Entity
@Table(name = "photo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "bitmap", nullable = false)
    private byte[] bitmap;

    @Column(name = "bitmap_content_type", length = 250)
    private String bitmapContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private PhotoSize size;

    public Photo(byte[] bitmap, String bitmapContentType, PhotoSize size) {
        this.bitmap = bitmap;
        this.bitmapContentType = bitmapContentType;
        this.size = size;
    }

    public Photo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBitmapContentType() {
        return bitmapContentType;
    }

    public void setBitmapContentType(String bitmapContentType) {
        this.bitmapContentType = bitmapContentType;
    }

    public PhotoSize getSize() {
        return size;
    }

    public void setSize(PhotoSize size) {
        this.size = size;
    }
}