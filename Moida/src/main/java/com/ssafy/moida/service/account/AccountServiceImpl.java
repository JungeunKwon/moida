package com.ssafy.moida.service.account;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.component.DeleteS3;
import com.ssafy.moida.component.UploadS3;
import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumAccountException;
import com.ssafy.moida.security.JwtTokenProvider;
import com.ssafy.moida.web.dto.account.AccountResponseDto;
import com.ssafy.moida.web.dto.account.AccountUpdateRequestDto;
import com.ssafy.moida.web.dto.account.RegisterRequestDto;
import com.ssafy.moida.web.dto.account.SignInRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final JwtTokenProvider JwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final UploadS3 uploadS3;
	private final DeleteS3 deleteS3;
	
	@Transactional
	public Long register(RegisterRequestDto requestDto) throws IOException {
		String uploadImgUrl;
		if (requestDto.getUploadFile() == null) {
			ClassPathResource classPathResource = new ClassPathResource("images/empty-profile.png");
			InputStream inputStream = classPathResource.getInputStream();
			File tmpfile = File.createTempFile("tmp", ".jpg");
			try {
				FileUtils.copyInputStreamToFile(inputStream, tmpfile);
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
			uploadImgUrl = uploadS3.uploadFile(tmpfile, "profile/" + requestDto.getEmail());
		} else {
			uploadImgUrl = uploadS3.uploadFile(requestDto.getUploadFile(), "profile/" + requestDto.getEmail());
		}
		requestDto.setProfileImg(uploadImgUrl);
		requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
		return accountRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public Account findByEmail(SignInRequestDto requestDto) {
		return accountRepository.findByEmail(requestDto.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자 없습니다."));
	}

	@Transactional(readOnly = true)
	public Account findById(String id) {
		return accountRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자 없습니다."));
	}

	@Transactional(readOnly = true)
	public String signIn(SignInRequestDto requestDto) {
		Account account = findByEmail(requestDto);
		if (!passwordEncoder.matches(requestDto.getPassword(), account.getPassword()))
			throw new IllegalArgumentException("비밀번호를 틀림");
		return JwtTokenProvider.createToken(String.valueOf(account.getId()), account.getRoles());
	}

	@Transactional(readOnly = true)
	public AccountResponseDto findByAccount() throws NumberFormatException, BaseException {
		Account account = getAccount();
		return AccountResponseDto.builder().id(account.getId()).email(account.getEmail())
				.username(account.getUsername()).nickname(account.getNickname()).gender(account.getGender())
				.phone(account.getPhone()).profileImg(account.getProfileImg()).roles(account.getRoles()).build();
	}

	@Transactional
	public void updateAccount(AccountUpdateRequestDto requestDTO) throws IOException, BaseException {
		Account account = accountRepository.findByEmail(getAccount().getEmail()).get();
		if(requestDTO.getUploadFile()==null) {
			requestDTO.setProfileImg(account.getProfileImg());
			requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
			account.updateAccountInfo(requestDTO.getPassword(), requestDTO.getPhone(), requestDTO.getNickname(), account.getProfileImg());
		}else {
			deleteS3.deleteFile(deleteS3.getFilePath(account.getProfileImg()));
			String uplaodImgUrl = uploadS3.uploadFile(requestDTO.getUploadFile(), "profile/"+ getAccount().getEmail());
			requestDTO.setProfileImg(uplaodImgUrl);
			requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
			account.updateAccountInfo(requestDTO.getPassword(), requestDTO.getPhone(), requestDTO.getNickname(), requestDTO.getProfileImg());
		}
	}

	@Transactional
	public void deleteAccount() throws NumberFormatException, BaseException {
		Account account = getAccount();
		accountRepository.delete(account);
	}

	public Boolean checkEmail(String email) {
		boolean checker;
		if (accountRepository.countByEmail(email) == 0) {
			checker = true;
		} else {
			checker = false;
		}
		return checker;
	}

	public Boolean checkNickname(String nickname) {
		boolean checker;
		if (accountRepository.countByNickname(nickname) == 0) {
			checker = true;
		} else {
			checker = false;
		}

		return checker;
	}
	
	public Account getAccount() throws NumberFormatException, BaseException {
	      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      Account tmp = (Account) authentication.getPrincipal();
	      Account account = accountRepository.findByEmail(tmp.getEmail()).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
	      return account;
	}
	
}
