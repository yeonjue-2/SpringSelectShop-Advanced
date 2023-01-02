package com.example.springselectshop.controller;

import com.example.springselectshop.dto.FolderRequest;
import com.example.springselectshop.entity.Folder;
import com.example.springselectshop.entity.Product;
import com.example.springselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {
    private final FolderService folderService;

    @PostMapping("/folders")
    public List<Folder> addFolders(@RequestBody FolderRequest requestDto, HttpServletRequest request) {
        List<String> folderNames = requestDto.getFolderNames();
        return folderService.addFolders(folderNames, request);
    }

    // 회원이 등록한 모든 폴더 조회
    @GetMapping("/folders")
    public List<Folder> getFolders(
            HttpServletRequest request
    ) {
        return folderService.getFolders(request);
    }

    // 회원이 등록한 폴더 내 모든 상품 조회
    @GetMapping("/folders/{folderId}/products")
    public Page<Product> getProductsInFolder(
            @PathVariable Long folderId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc,
            HttpServletRequest request
    ) {
        return folderService.getProductsInFolder(
                folderId,
                page-1,
                size,
                sortBy,
                isAsc,
                request
        );
    }
}
