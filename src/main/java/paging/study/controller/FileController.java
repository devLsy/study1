package paging.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import paging.study.domain.vo.FileVO;
import paging.study.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @GetMapping("/file/upload")
    public String uploadForm() {
        return "/file/fileUpload";
    }

    @PostMapping("/file/upload")
//    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "files", required = false) List<MultipartFile> files, Model model) throws IOException {
//        if (!file.isEmpty()) {
//            fileService.saveFile(file);
//        }
//
//        if (!files.isEmpty()) {
//            fileService.saveFile(files);
//        }
//        return "redirect:/file/list";
//    }

    @GetMapping("/file/{fileId}/download")
    public ResponseEntity<Resource> download(@PathVariable(value = "fileId", required = true) Long fileId) throws IOException{
        FileVO fileVO = fileService.findFileDetail(fileId);
        Path path = Paths.get(fileVO.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileVO.getOrgFileName() + "\"")
                .body(resource);
    }

    @PostMapping("/file/del")
    public String  fileDel(@RequestParam("fileId") Long fileId) {
        fileService.fileDel(fileId);
        return "redirect:/file/list";
    }

    @GetMapping("/file/list")
    public String  findFiles(Model model) {
        List<FileVO> files = fileService.findFiles();
        model.addAttribute("list", files);
        return "file/fileList";
    }
}
