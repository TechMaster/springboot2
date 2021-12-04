package vn.techmaster.demoexception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CloseResourceOnException {
  @Test
  public void closeResourceInTryBlock() {
    FileInputStream inputStream = null;
    try {
      File file = new File("./tmp.txt");
      inputStream = new FileInputStream(file);
      // Đừng close resource trong block try, bởi khi có exception, nó sẽ không được
      // dọn dẹp
      inputStream.close();
    } catch (FileNotFoundException e) {
      log.error("File not found", e);
    } catch (IOException e) {
      log.error("IO Exception", e);
    }
  }

  @Test
  public void closeResourceInFinallyBlock() {
    FileInputStream inputStream = null;
    try {
      File file = new File("./tmp.txt");
      inputStream = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      log.error("File not found", e);
    } finally {
      // Sử dụng finally để dọn dẹp sau khi quăng exception
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          log.error("Cannot close input stream", e);
        }
      }
    }
  }

  @Test
  public void closeResource() {
    File file = new File("./tmp.txt");
    //Sử dụng cú pháp try close resource
    try (FileInputStream inputStream = new FileInputStream(file);) {
      InputStreamReader inputReader = new InputStreamReader(inputStream);
      BufferedReader reader = new BufferedReader(inputReader);
      while(reader.ready()) {
        String line = reader.readLine();
      }

    } catch (FileNotFoundException e) {
      log.error("File not found", e);
    } catch (IOException e) {
      log.error("IO exception", e);
    }
  }
}
