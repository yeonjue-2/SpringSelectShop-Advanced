package com.example.springselectshop.service;

import com.example.springselectshop.entity.Folder;
import com.example.springselectshop.entity.Product;
import com.example.springselectshop.entity.User;
import com.example.springselectshop.jwt.JwtUtil;
import com.example.springselectshop.repository.FolderRepository;
import com.example.springselectshop.repository.ProductRepository;
import com.example.springselectshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final JwtUtil jwtUtil;

    public List<Folder> addFolders(List<String> folderNames, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

            List<Folder> folders = new ArrayList<>();

            for (String folderName : folderNames) {
                if (!isExistFolderName(folderName, existFolderList)) {
                    Folder folder = new Folder(folderName, user);
                    folders.add(folder);
                }
            }

            return folderRepository.saveAll(folders);
        } else {
            return null;
        }
    }

    // 로그인한 회원이 등록된 모든 폴더 조회
    public List<Folder> getFolders(HttpServletRequest request) {

        // 사용자의 정보를 가져온다
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            return folderRepository.findAllByUser(user);

        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsInFolder(Long folderId, int page, int size, String sortBy, boolean isAsc, HttpServletRequest request) {

        // 페이징 처리
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            return productRepository.findAllByUserIdAndFolderList_Id(user.getId(), folderId, pageable);

        } else {
            return null;
        }
    }

    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        // 기존 폴더 리스트에서 folder name 이 있는지?
        for (Folder existFolder : existFolderList) {
            if (existFolder.getName().equals(folderName)) {
                return true;
            }
        }
        return false;
    }
}
