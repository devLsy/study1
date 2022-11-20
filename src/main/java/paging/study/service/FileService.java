package paging.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import paging.study.domain.vo.FileVO;
import paging.study.mapper.FileMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    @Value("${file.path}")
    private String fileDir;

    private final FileMapper fileMapper;

    public void saveFile(Long boardSeq, MultipartFile file) throws IOException {

        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        } else {
            log.info("The folder already exists.");
        }

        String orgFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = orgFileName.substring(orgFileName.lastIndexOf("."));
        String saveFileName = uuid + extension;

        String savePath = fileDir + saveFileName;

        FileVO fileVO = new FileVO(boardSeq, orgFileName, saveFileName, savePath);
        // 서버에 물리 파일 저장하는 방법은 이 것말고도 여러개가 있으니 다양하게 해볼 것
        file.transferTo(new File(savePath));
        fileMapper.saveFile(fileVO);
    }

    public void saveFile(Long boardSeq, List<MultipartFile> files) throws IOException {

        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        } else {
            log.info("The folder already exists.");
        }

        for (MultipartFile file : files) {
            String orgFileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String extension = orgFileName.substring(orgFileName.lastIndexOf("."));
            String saveFileName = uuid + extension;

            String savePath = fileDir + saveFileName;

            FileVO fileVO = new FileVO(boardSeq, orgFileName, saveFileName, savePath);
            // 서버에 물리 파일 저장하는 방법은 이 것말고도 여러개가 있으니 다양하게 해볼 것
            file.transferTo(new File(savePath));
            fileMapper.saveFile(fileVO);
        }

    }

    public FileVO findFileDetail(Long fileId) {
        return fileMapper.findFileDetail(fileId);
    }

    public List<FileVO> findFiles() {
        return fileMapper.findFiles();
    }

    public void fileDel(Long fileId) {
        FileVO fileDetail = fileMapper.findFileDetail(fileId);
        String filePath = fileDetail.getFilePath();
        File file = new File(filePath);
        file.delete();
        fileMapper.delFile(fileId);
    }
}
