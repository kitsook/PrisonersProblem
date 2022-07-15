package net.clarenceho.prisoners;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;

public class PrisonerProblem {
  private final int size;
  private final int threshold;

  private ArrayList<Integer> boxes;

  public PrisonerProblem(int size) {
    if (size % 2 == 1) {
      throw new InvalidParameterException("Size must be even");
    }
    this.size = size;
    threshold = size / 2;

    initBoxes();
  }

  public boolean prisonersWin() {
    // for each prisoner
    for (int i = 0; i < size; i++) {
      int trial = 0;
      int boxToOpen = i;

      // open boxes and follow the number
      while (true) {
        trial++;
        if (trial > threshold) {
          return false;
        }

        if (boxes.get(boxToOpen) != i) {
          boxToOpen = boxes.get(boxToOpen);
        } else {
          break;
        }
      }
    }
    return true;
  }

  private void initBoxes() {
    boxes = new ArrayList<>(this.size);
    for (int i = 0; i < size; i++) {
      boxes.add(i);
    }
    Collections.shuffle(boxes);
  }
}
