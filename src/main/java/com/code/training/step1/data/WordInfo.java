package com.code.training.step1.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class WordInfo implements Comparable<WordInfo> {
  private String word;
  private long totalCount;

  public WordInfo(final String word) {
    this.word = word;
    this.totalCount = 0L;
  }

  public void addTotalCount() {
    this.totalCount++;
  }

  @Override
  public int compareTo(final WordInfo other) {
    return Long.compare(this.totalCount, other.totalCount);
  }

  @Override
  public String toString() {
    return "{"+this.word+":"+this.totalCount+"}";
  }
}
