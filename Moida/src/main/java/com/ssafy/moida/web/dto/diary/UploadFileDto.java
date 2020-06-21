package com.ssafy.moida.web.dto.diary;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileDto {
	private MultipartFile uploadFile;

	@Builder
	public UploadFileDto(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
