package com.example.site_pl_99.configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class MinioBucketInitializer {

    private static final Logger log = LoggerFactory.getLogger(MinioBucketInitializer.class);

    private final MinioClient minioClient;

    @Value("${minio.bucket.name.images}")
    private String imagesBucket;

    @Value("${minio.bucket.name.video}")
    private String videoBucket;

    public MinioBucketInitializer(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostConstruct
    public void initBuckets() {
        createBucketIfNotExists(imagesBucket);
        createBucketIfNotExists(videoBucket);
    }

    private void createBucketIfNotExists(String bucketName) {
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucketName).build()
                );
                log.info("MinIO bucket created: {}", bucketName);
            } else {
                log.info("MinIO bucket already exists: {}", bucketName);
            }
        } catch (Exception e) {
            log.warn("Failed to initialize MinIO bucket '{}': {}. Buckets will be created on first upload.", bucketName, e.getMessage());
        }
    }
}
