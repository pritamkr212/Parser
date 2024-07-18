package com.parser.Parser.parser.fileParserService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileParserService<T> {
    List<T> parseFile(MultipartFile file) throws IOException;
}
