package utilTest;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test0 {
//
//        StringBuilder sb = new StringBuilder();
//        for (List<Integer> line : imageData) {
//            for (Integer num : line) {
//                sb.append(num);
//            }
//        }
//        System.out.println(sb.toString());
//        //二进制转化为图片
//        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\2.jpg"));) {
//            fileOutputStream.write(sb.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    @Test
    public void mm(){
        int[][] n=new int[3][];
        for (int i = 0; i < 3; i++) {
            n[i]=new int[2];
            for (int j = 0; j < 2; j++) {
                n[i][j]=1;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(n[i][j]);
            }
        }
    }

}
class DrawRect {

    /**
     * @param matrix 矩阵bai
     * @param filedir 文件路径。如,d:\\test.jpg
     * @throws IOException
     */
    public static void createMatrixImage(int[][] matrix, String filedir) throws IOException {
        int cx = matrix.length;
        int cy = matrix[0].length;
        //填充矩形高宽
        int cz = 10;
        //生成图的宽度
        int width = cx * cz;
        //生成图的高度
        int height = cy * cz;

        OutputStream output = new FileOutputStream(new File(filedir));
        BufferedImage bufImg = new BufferedImage(width, height,    BufferedImage.TYPE_INT_RGB);
        Graphics2D gs = bufImg.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.clearRect(0, 0, width, height);

        gs.setColor(Color.BLACK);
        for (int i = 0; i < cx; i++) {
            for (int j = 0; j < cy; j++) {
                //1绘制填充黑矩形
                if(matrix[j][i]==1){
                    gs.drawRect(i*cz, j*cz, cz, cz);
                    gs.fillRect(i*cz, j*cz, cz, cz);
                }
            }
        }
        gs.dispose();
        bufImg.flush();
        //输出文件
        ImageIO.write(bufImg, "jpeg", output);

    }

    public static void main(String[] args) throws Exception {
        //测试
        int[][] matrix = {
                { 0, 1, 1, 0, 1,1},
                { 0, 0, 1, 0, 1,1 },
                { 0, 1, 0, 0, 0 ,1},
                { 1, 0, 1, 1, 1 ,0},
                { 1, 0, 0, 1, 0 ,1},
                { 0, 0, 1, 0, 1 ,1}};

        DrawRect.createMatrixImage(matrix, "d:\\test.jpg");
    }

}