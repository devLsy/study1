package paging.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import paging.study.domain.vo.FileVO;

import java.util.List;

@Mapper
public interface FileMapper {

    void saveFile(FileVO fileVO);

    FileVO findFileDetail(Long fileId);

    List<FileVO> findFiles();

    void delFile(Long fileId);
}
