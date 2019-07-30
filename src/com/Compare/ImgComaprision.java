package com.Compare;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;
import javax.imageio.ImageIO;

public class ImgComaprision {

    public static void main(String[] args) throws IOException {

        //Try reading the app.properties file which is in the folder resources
        try (InputStream input = ImgComaprision.class.getClassLoader().getResourceAsStream("app.properties"))
        {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value a read the input csv file and write the output csv file
            readWriteFile(prop.getProperty("Input_File_Path"), prop.getProperty("Output_File_Path"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**+
     *
     * @param infilePath - File path and name of the input csv file
     * @param outFilePath - File path and name of the output file to be written
     */
    public static void readWriteFile(String infilePath, String outFilePath)
    {
        BufferedReader reader = null;
        FileWriter fileWriter = null;

        try {
            //read the input file
            reader = new BufferedReader(new FileReader(infilePath));
            fileWriter = new FileWriter(outFilePath);

            //Write the first line which will act as header
            fileWriter.append("image1,image2,similar,elapsed");
            fileWriter.write(System.getProperty( "line.separator" ));

            //Read the first line and ignore it since the first line is a header
            String line = reader.readLine();

            //read the input csv file line by line until a line is null
            while ((line = reader.readLine()) != null) {

                String[] words = line.split(",");

                BufferedImage img1 = ImageIO.read(new File(words[0]));
                BufferedImage img2 = ImageIO.read(new File(words[1]));

                //Start of calling the function to compare the images
                long startTime = System.nanoTime();
                double p = getDiffPercent(img1, img2);
                long endTime = System.nanoTime();

                //Convert the time spent in comparing the images from nano seconds to seconds
                double elapsedTimeInSecond = (double) (endTime - startTime) / 1000000000;

                //Write the out csv file line by line
                fileWriter.append(words[0]);
                fileWriter.append(",");
                fileWriter.append(words[1]);
                fileWriter.append(",");
                fileWriter.append(Double.toString(p/100));
                fileWriter.append(",");
                fileWriter.append(Double.toString(elapsedTimeInSecond));
                fileWriter.write(System.getProperty( "line.separator" ));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    //Close the reader and writer objects
                    reader.close();
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**+
     *
     * @param img1 - The first image
     * @param img2 - The second image
     * @return - The function will return the difference in similarity between two images in percentage
     */
    private static double getDiffPercent(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();
        if (width != width2 || height != height2) {
            //If the dimensions of the image are different then return that the images are different
            return 100;
        }
        else{
            long diff = 0;
            //Iterate over every pixel of the images and compare each pixel by calling method pixelDiff
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
                }
            }
            long maxDiff = 3L * 255 * width * height;

            return 100.0 * diff / maxDiff;
        }
    }

    /**+
     *
     * @param rgb1 - rgb value of pixel of image 1
     * @param rgb2 - rgb value of pixel of image 2
     * @return - the difference in the rgb values of the two pixels
     */
    private static int pixelDiff(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
    }

}
