package com.ssafy.moida.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeleteS3 {
private final AmazonS3Client amazonS3Client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	public String getFilePath(String filePath) {
		String[] s = filePath.split("/");
		String key = s[5];
		return key;
	}
	public void deleteFile(String key) {
		try {
			amazonS3Client.deleteObject(bucketName, key);
		}
		catch (AmazonServiceException e) {
            e.printStackTrace();
        }
		catch (SdkClientException e) {
            e.printStackTrace();
        }
	}
}
