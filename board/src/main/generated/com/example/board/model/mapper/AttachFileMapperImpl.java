package com.example.board.model.mapper;

import com.example.board.model.AttachFile;
import com.example.board.model.dto.AttachFileDto;
import com.example.board.model.dto.BoardDto;
import com.example.board.model.dto.DownloadFileDto;
import com.example.board.model.dto.DownloadFileDto.DownloadFileDtoBuilder;
import com.example.board.model.dto.PreviewAttachFileDto;
import com.example.board.model.dto.PreviewAttachFileDto.PreviewAttachFileDtoBuilder;
import com.example.board.model.dto.UploadFileDto;
import java.io.IOException;
import javax.annotation.processing.Generated;
import org.springframework.web.multipart.MultipartFile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-04T05:54:19+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class AttachFileMapperImpl implements AttachFileMapper {

    @Override
    public AttachFile toAttachFile(AttachFileDto attachFileDto) {
        if ( attachFileDto == null ) {
            return null;
        }

        AttachFile attachFile = new AttachFile();

        attachFile.setBoardId( attachFileDto.getBoardId() );
        attachFile.setFilePath( attachFileDto.getFilePath() );

        attachFile.setFileName( attachFileDto.getMultipartFile().getOriginalFilename() );
        attachFile.setFileType( com.example.board.global.enums.EBoard.EFileType.ATTACH.getFileType() );
        attachFile.setFileSize( attachFileDto.getMultipartFile().getSize() );
        attachFile.setFileExtension( com.example.board.global.utils.StringUtil.extractFileExtension(attachFileDto.getMultipartFile().getOriginalFilename()) );
        attachFile.setUploadedAt( java.time.LocalDateTime.now() );
        attachFile.setDeleted( com.example.board.global.enums.EBoard.EDeletionStatus.EXIST.getStatus() );

        return attachFile;
    }

    @Override
    public AttachFile toAttachFile(BoardDto boardDto, String fileName, int fileSize, String filePath, String fileExtension) {
        if ( boardDto == null && fileName == null && filePath == null && fileExtension == null ) {
            return null;
        }

        AttachFile attachFile = new AttachFile();

        if ( boardDto != null ) {
            attachFile.setBoardId( boardDto.getId() );
            attachFile.setId( boardDto.getId() );
        }
        if ( fileName != null ) {
            attachFile.setFileName( fileName );
        }
        if ( filePath != null ) {
            attachFile.setFilePath( filePath );
        }
        if ( fileExtension != null ) {
            attachFile.setFileExtension( fileExtension );
        }
        attachFile.setFileType( com.example.board.global.enums.EBoard.EFileType.BOARD.getFileType() );
        attachFile.setFileSize( (long)fileSize );
        attachFile.setUploadedAt( java.time.LocalDateTime.now() );
        attachFile.setDeleted( com.example.board.global.enums.EBoard.EDeletionStatus.EXIST.getStatus() );

        return attachFile;
    }

    @Override
    public AttachFileDto toAttachFileDto(UploadFileDto uploadFileDto, MultipartFile multipartFile, String filePath) {
        if ( uploadFileDto == null && multipartFile == null && filePath == null ) {
            return null;
        }

        AttachFileDto attachFileDto = new AttachFileDto();

        if ( uploadFileDto != null ) {
            attachFileDto.setBoardId( uploadFileDto.getBoardId() );
        }
        if ( multipartFile != null ) {
            attachFileDto.setMultipartFile( multipartFile );
        }
        if ( filePath != null ) {
            attachFileDto.setFilePath( filePath );
        }

        return attachFileDto;
    }

    @Override
    public DownloadFileDto toDownloadFileDto(AttachFile attachFile) throws IOException {
        if ( attachFile == null ) {
            return null;
        }

        DownloadFileDtoBuilder downloadFileDto = DownloadFileDto.builder();

        downloadFileDto.fileName( attachFile.getFileName() );
        downloadFileDto.fileSize( attachFile.getFileSize() );

        downloadFileDto.file( org.apache.commons.io.FileUtils.readFileToByteArray(new java.io.File(attachFile.getFilePath())) );

        return downloadFileDto.build();
    }

    @Override
    public PreviewAttachFileDto toPreviewAttachFileDto(AttachFile attachFile) {
        if ( attachFile == null ) {
            return null;
        }

        PreviewAttachFileDtoBuilder previewAttachFileDto = PreviewAttachFileDto.builder();

        previewAttachFileDto.attachFileId( attachFile.getId() );
        previewAttachFileDto.boardId( attachFile.getBoardId() );
        previewAttachFileDto.fileName( attachFile.getFileName() );

        return previewAttachFileDto.build();
    }
}
