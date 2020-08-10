package com.code.training.step1.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSearchServiceImpl implements FileSearchService {
  public List<File> getAllFile(final String parentPath, final String filterWord) {
    List<File> fileList = new ArrayList<>();
    addAllFileToList(fileList, parentPath, filterWord);
    return fileList;
  }

  private void addAllFileToList(final List<File> fileList, final String path, final String filterWord) {
    File file = this.getFile(path);
    if (file == null) {
      return;
    }

    if (file.isFile()) {
      if (filterWord == null) {
        fileList.add(file);
        return;
      }
      if (filterWord.equals(file.getName().substring(file.getName().lastIndexOf('.')+1))) {
        fileList.add(file);
      }
    } else {
      File[] dir = file.listFiles();
      assert dir != null;
      Arrays.stream(dir).forEach(target ->
          addAllFileToList(fileList, file.getPath()+File.separator+target.getName(), filterWord));
    }
  }

  private File getFile(final String filePath) {
    try {
      return new File(filePath);
    } catch (Exception e) {
      return null;
    }
  }
}
