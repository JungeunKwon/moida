package com.ssafy.moida.component;

import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetS3PresignedURL {
	
	private final AmazonS3 s3Client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	public String  getPresignedURL(String serial, String fileName) {
		String directory = serial;
		
		Date expiration = new Date();
		Long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60*30; //1遺�
		expiration.setTime(expTimeMillis);
		
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, (directory+"/"+fileName))
				.withMethod(HttpMethod.PUT)
				.withExpiration(expiration);
		
		generatePresignedUrlRequest.addRequestParameter(Headers.S3_CANNED_ACL,
				CannedAccessControlList.PublicRead.toString());
		
		URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
		
		return url.toExternalForm();
		
	}
	
}
