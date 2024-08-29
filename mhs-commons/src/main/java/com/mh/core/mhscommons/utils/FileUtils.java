package com.mh.core.mhscommons.utils;

import com.google.common.io.Files;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.Files.createDirectories;
import static org.apache.commons.io.FileUtils.copyURLToFile;


@Log4j2
public class FileUtils {

    private FileUtils() {
    }

    public static File downloadFile(String fileUrl) throws IOException {
        final URL url = new URL(fileUrl);
        File file = new File(baseFolder() + TimeUtils.getCurrentTimeLong() + "-video.mp4");
        copyURLToFile(url, file);
        return file;
    }

    public static File copyToImageFile(String fileUrl) throws IOException {
        return copyToImageFile(fileUrl, getFileName(fileUrl));
    }

    public static File createEmptyFile(String fileUrl) throws IOException {
        final URL url = new URL(fileUrl);
        createDirector(url.getPath());
        final String path = "upload-dir/" + url.getPath();
        return new File(path);
    }

    public static File createEmptyFileFromPath(String path) throws IOException {
        createDirector(path);
        final String localPath = "upload-dir/" + path;
        return new File(localPath);
    }

    private static void createDirector(String path) throws IOException {
        final String[] subFolders = path.split("/");
        StringBuilder builder = new StringBuilder();
        for (String subFolder : subFolders) {
            if (!subFolder.contains(".")) builder.append("/").append(subFolder);
        }
        String joined = builder.toString();
        createDirectories(Paths.get("upload-dir" + joined));
    }

    public static File createEmptyFileName(String fileName) throws IOException {
        try {
            final URL url = new URL(fileName);
            createDirector(url.getPath());
            final String path = "upload-dir/" + url.getPath();
            return new File(path);
        } catch (Exception e) {
            return new File("upload-dir/" + fileName);
        }
    }

    public static File copyToImageFile(String fileUrl, String filename) throws IOException {
        final URL url = new URL(fileUrl);
        File file = new File(baseFolder() + filename.concat(".").concat(getExtensionByGuava(fileUrl)));
        copyURLToFile(url, file);
        return file;
    }

    public static File createEmptyImg() {
        return new File(baseFolder() + TimeUtils.getCurrentTimeLong() + ".png");
    }

    public static File createEmptyCsv() {
        return new File(baseFolder() + TimeUtils.getCurrentTimeLong() + ".csv");
    }

    public static File createEmptyVideo() {
        return new File(baseFolder() + TimeUtils.getCurrentTimeLong() + ".mp4");
    }

    public static File copyToVideoFile(String fileUrl) throws IOException {
        final URL url = new URL(fileUrl);
        File file = new File(baseFolder() + getFileName(fileUrl).concat(".").concat(getExtensionByGuava(fileUrl)));
        copyURLToFile(url, file);
        return file;
    }

    private static String baseFolder() {
        final String path = "upload-dir/" + new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
        try {
            createDirectories(Paths.get(path));
        } catch (IOException e) {
            log.error("[ERROR][CREATE-FOLDER] cause: ", e);
        }
        return path;
    }

    public static File copyToTextFile(String data) {
        final String path = baseFolder() + TimeUtils.getCurrentTimeLong() + ".txt";
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(data);
        } catch (IOException e) {
            log.error("[ERROR][WRIT-FILE] cause: ", e);
        }
        return new File(path);
    }

    public static String getFileName(String url) {
        return Files.getNameWithoutExtension(url);
    }

    public static String getExtensionByGuava(String filename) {
        return Files.getFileExtension(filename);
    }
}