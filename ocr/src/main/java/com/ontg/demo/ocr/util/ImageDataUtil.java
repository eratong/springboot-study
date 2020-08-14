package com.ontg.demo.ocr.util;

import com.counect.unifiedocr.entity.ImageContent;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageDataUtil.class);

    /**
     * 把图片转为opencv的Mat对象，注意图片是转为灰度图
     *
     * @param filePath
     * @return
     */
    public static Mat getOpencvMat(String filePath) {
        Mat image = Imgcodecs.imread(filePath, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        return image;
    }

    /**
     * 把opencv的Mat对象转为base64的字符串
     *
     * @param imageMat
     * @return
     */
    public static String opencvMat2Base64(Mat imageMat) {
        BASE64Encoder encoder = new BASE64Encoder();
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".bmp", imageMat, mob);
        byte[] byteArray = mob.toArray();
        return "data:image/bmp;base64," + encoder.encodeBuffer(byteArray).trim();
    }

    public static String opencvMat2Base64ForTencent(Mat imageMat) {
        byte[] data = null;
        BASE64Encoder encoder = new BASE64Encoder();
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", imageMat, mob);
        byte[] byteArray = mob.toArray();
        return encoder.encode(byteArray).replaceAll("[\\s*\\t\\n\\r]", "");
    }

    public static List<List<Integer>> bmp2List(String rotate, String filePath) throws Exception {
        ImageContent imageContent = getImageContent(filePath);
        int miny = 0;
        int minx = 0;
        int maxWidth = imageContent.getWidth();
        int maxHeight = imageContent.getHeight();
        int[] rect = {minx, miny, maxWidth, maxHeight};
        return getImageDataList(rect, imageContent, rotate);
    }


    public static ImageContent getImageContent(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        // 构建ImageReader
        ImageInputStream iis = null;
        int width = 0;
        int height = 0;
        BufferedImage bi = null;
        try {
            iis = ImageIO.createImageInputStream(file);

            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
//            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("bmp");
            ImageReader reader = readers.next();
            reader.setInput(iis, true);
            // 获取image data
            width = reader.getWidth(0);
            height = reader.getHeight(0);
            ImageReadParam param = reader.getDefaultReadParam();
            // 一个矩形区域，获取这个区域内的图像数据
            Rectangle rect = new Rectangle(0, 0, width, height);
            param.setSourceRegion(rect);
            bi = reader.read(0, param);
        } finally {
            if (null != iis) {
                iis.close();
            }
        }
        ImageContent imageContent = new ImageContent();
        imageContent.setBufferedImage(bi);
        imageContent.setWidth(width);
        imageContent.setHeight(height);
        return imageContent;
    }

    private static List<List<Integer>> getImageDataList(int[] rect,
                                                        ImageContent imageContent,
                                                        String rotate) {

        int minx = rect[0];
        int miny = rect[1];
        int width = rect[2];
        int height = rect[3];
        BufferedImage bi = imageContent.getBufferedImage();

        // 构建数组
        List<List<Integer>> imageData = null;

        if (StringUtils.isEmpty(rotate) || rotate.equals("0")) {
            imageData = new ArrayList(height);
            for (int j = miny; j < height + miny; j++) {
                List<Integer> line = new ArrayList(width);
                for (int i = minx; i < width + minx; i++) {
                    int pixel = bi.getRGB(i, j);
                    if (-1 == pixel) {
                        pixel = 0;//如果是-1，转为0
                    } else {
                        pixel = 1;//如果不是-1，转为1
                    }
                    line.add(pixel);
                }
                imageData.add(line);
            }
        } else if (rotate.equals("90")) {
            imageData = new ArrayList(width);
            for (int j = minx; j < width + minx; j++) {
                List<Integer> line = new ArrayList(height);
                for (int i = height + miny - 1; i >= miny; i--) {
                    int pixel = bi.getRGB(j, i);
                    if (-1 == pixel) {
                        pixel = 0;//如果是-1，转为0
                    } else {
                        pixel = 1;//如果不是-1，转为1
                    }
                    line.add(pixel);
                }
                imageData.add(line);
            }
        } else if (rotate.equals("180")) {
            imageData = new ArrayList(height);
            for (int j = height + miny - 1; j >= miny; j--) {
                List<Integer> line = new ArrayList(width);
                for (int i = width + minx - 1; i >= minx; i--) {
                    int pixel = bi.getRGB(i, j);
                    if (-1 == pixel) {
                        pixel = 0;//如果是-1，转为0
                    } else {
                        pixel = 1;//如果不是-1，转为1
                    }
                    line.add(pixel);
                }
                imageData.add(line);
            }
        } else if (rotate.equals("270")) {
            imageData = new ArrayList(width);
            for (int j = width + minx - 1; j >= minx; j--) {
                List<Integer> line = new ArrayList(height);
                for (int i = miny; i < height + miny; i++) {
                    int pixel = bi.getRGB(j, i);
                    if (-1 == pixel) {
                        pixel = 0;//如果是-1，转为0
                    } else {
                        pixel = 1;//如果不是-1，转为1
                    }
                    line.add(pixel);
                }
                imageData.add(line);
            }
        }
        return imageData;
    }

    /**
     * 把opencv的Mat对象转为二维List<List<Integer>>
     *
     * @param rotate
     * @param imageMat
     * @return
     */
    public static List<List<Integer>> opencvMat2List(String rotate, Mat imageMat) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".bmp", imageMat, mob);
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            logger.error("opencvMat2List error:", e);
        }
        ImageContent imageContent = new ImageContent();
        imageContent.setBufferedImage(bufImage);
        imageContent.setHeight(bufImage.getHeight());
        imageContent.setWidth(bufImage.getWidth());
        int[] rect = {0, 0, bufImage.getWidth(), bufImage.getHeight()};
        return getImageDataList(rect, imageContent, rotate);
    }
}