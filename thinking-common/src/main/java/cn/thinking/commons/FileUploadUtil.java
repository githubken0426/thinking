package cn.thinking.commons;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class FileUploadUtil {
    public static String saveFile(MultipartFile multipartFile, String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        String fileName = path + File.separator + UUID.randomUUID() + ".png";
        try (BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(fileName))) {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                buffer.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 读取文件
     */
    public ByteBuffer readFile(String filePath) {
        try (FileChannel channel = new FileInputStream(filePath).getChannel();) {
            MappedByteBuffer mBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            return mBuffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存文件
     */
    public void saveFile(ByteBuffer buffer, String filePath) {
        try (FileChannel foChannel = new FileOutputStream(filePath).getChannel();) {
            foChannel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
