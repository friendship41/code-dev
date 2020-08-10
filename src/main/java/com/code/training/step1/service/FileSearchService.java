package com.code.training.step1.service;

import java.io.File;
import java.util.List;

public interface FileSearchService {
  List<File> getAllFile(String parentPath, String filterWord);
}
