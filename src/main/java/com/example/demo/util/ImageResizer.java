//package com.example.demo.util;
//
//import it.quix.framework.util.file.FileUtils;
//import it.quix.framework.util.graphic.CompletedImage;
//import it.quix.framework.util.graphic.GIFEncoder;
//import it.quix.framework.util.graphic.ImageToolkit;
//import it.quix.framework.util.graphic.Quantize;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import javax.imageio.IIOImage;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageWriteParam;
//import javax.imageio.ImageWriter;
//import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
//import javax.imageio.stream.ImageOutputStream;
//import java.awt.*;
//import java.awt.geom.AffineTransform;
//import java.awt.image.*;
//import java.io.*;
//
//
///**
// * Insert the type's description here. Creation date: (12/04/2001 20.27.34)
// *
// * @author: Administrator
// */
//public class ImageResizer {
//
//    private static Log log = LogFactory.getLog(ImageResizer.class);
//
//
//    private static Panel panel = new Panel();
//
//    /**
//     * ImageFormatter constructor comment.
//     */
//    public ImageResizer() {
//        super();
//    }
//
////    public static void main(String[] args) {
////        File src = new File("C:\\claudio\\lavoro\\Clienti\\Emilceramica\\20140526_GestionePannelliCulleCampioni\\esempiMinimali\\803B3R_OnSquare_Sabbia_80x80_@01_XL.jpg");
////        File dest = new File("C:\\claudio\\lavoro\\Clienti\\Emilceramica\\20140526_GestionePannelliCulleCampioni\\esempiMinimali\\803B3R_OnSquare_Sabbia_80x80_@01_XL_Resized.JPG");
////        File src2 = new File("C:\\claudio\\lavoro\\Clienti\\Emilceramica\\20140526_GestionePannelliCulleCampioni\\esempiMinimali\\983B3R_OnSquare_Sabbia_60x120_@01_XL.jpg");
////        File dest2 = new File("C:\\claudio\\lavoro\\Clienti\\Emilceramica\\20140526_GestionePannelliCulleCampioni\\esempiMinimali\\983B3R_OnSquare_Sabbia_60x120_@01_XL_Resized.JPG");
////
////        // File dest2 = new File(
////        // "C:\\Documents and Settings\\emanuela.scionti\\Desktop\\IMG_0799Scaled.JPG");
////        // System.out.println(resize(src, dest, 100, 101, 0.8f));
////        System.out.println(resize(src, dest, 1.33f, 1.0f));
////        System.out.println(resize(src2, dest2, 2.00f, 1.0f));
////        System.out.println("fine");
////
////    }
//
//    /**
//     * Snag the pixels from an image.
//     */
//    private static int[][] getPixels(Image image) throws IOException {
//        int w = image.getWidth(null);
//        int h = image.getHeight(null);
//        int pix[] = new int[w * h];
//        PixelGrabber grabber = new PixelGrabber(image, 0, 0, w, h, pix, 0, w);
//
//        try {
//            if (grabber.grabPixels() != true) {
//                throw new IOException("Grabber returned false: " + grabber.status());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        int pixels[][] = new int[w][h];
//        for (int x = w; x-- > 0; ) {
//            for (int y = h; y-- > 0; ) {
//                pixels[x][y] = pix[y * w + x];
//            }
//        }
//
//        return pixels;
//    }
//
//    private static Image reconvertToImage(int[] palette, int[][] pixels) {
//        int w = pixels.length;
//        int h = pixels[0].length;
//        int pix[] = new int[w * h];
//
//        // convert to RGB
//        for (int x = w; x-- > 0; ) {
//            for (int y = h; y-- > 0; ) {
//                pix[y * w + x] = palette[pixels[x][y]];
//            }
//        }
//        // ritrasformo in immagine
//        Image img = panel.createImage(new MemoryImageSource(w, h, pix, 0, w));
//        return img;
//    }
//
//    /**
//     * Insert the method's description here. Creation date: (12/04/2001 20.32.57)
//     */
//    public static boolean resize(File source, File dest, int maxWidth, int maxHeight, float quality) {
//
//        // determina l'estensione del file sorgente e prova a decodificarla
//        // con il resizeJPG o il resize Gif
//        String extension = FileUtils.getFileExtension(source.getName());
//        boolean gifFirst = true;
//        if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
//            gifFirst = false;
//        }
//
//        // in entrambi i casi se fallisce la conversione tento l'altro decoder
//        // in quanto a volte su internet si trovano files denominati jpg che in
//        // realt�
//        // sono gif o viceversa
//        if (gifFirst) {
//            if (resizeGif(source, dest, maxWidth, maxHeight)) {
//                return true;
//            } else {
//                return resizeJpg(source, dest, maxWidth, maxHeight, quality);
//            }
//        } else {
//            if (resizeJpg(source, dest, maxWidth, maxHeight, quality)) {
//                return true;
//            } else {
//                return resizeGif(source, dest, maxWidth, maxHeight);
//            }
//
//        }
//
//    }
//
//    /**
//     * Insert the method's description here. Creation date: (12/04/2001 20.32.57)
//     */
//    public static boolean resize(File source, File dest, int maxWidth, int maxHeight) {
//
//        return resize(source, dest, maxWidth, maxHeight, 1.0f);
//    }
//
//    /**
//     * Insert the method's description here. Creation date: (12/04/2001 20.32.57)
//     */
//    public static boolean resize(String source, String dest, int maxWidth, int maxHeight, float quality) {
//
//        try {
//            File f1 = new File(source);
//            File f2 = new File(dest);
//
//            return resize(f1, f2, maxWidth, maxHeight, quality);
//        } catch (Exception ex) {
//            return false;
//        }
//
//    }
//
//    /**
//     * Insert the method's description here. Creation date: (12/04/2001 20.32.57)
//     */
//    public static boolean resize(String source, String dest, int maxWidth, int maxHeight) {
//
//        return resize(source, dest, maxWidth, maxHeight, 1.0F);
//    }
//
//    /**
//     * Questo metodo non � completamente testato quindi non si garantisce il funzionamento. Nel caso in cui il fattore di scala risultante sia >=1 (ingrandimento) allora si copia il file sorgente nella destinazione (di fatto non ci si fa nulla) Creation date: (12/04/2001 20.32.57)
//     *
//     * @deprecated
//     */
//    public static boolean resizeGif(File source, File dest, int maxWidth, int maxHeight) {
//
//        try {
//            // carico l'immagine
//            String filename = source.getPath();
//            ImageToolkit it = new ImageToolkit();
//            // attendo che sia terminato il caricamento
//            CompletedImage img = it.load(filename);
//
//            // determino le dimensioni per il resize
//            int initialHeight = img.height;
//            int initialWidth = img.width;
//            int finalHeight = maxHeight;
//            int finalWidth = maxWidth;
//            double scaleX = (double) maxWidth / (double) initialWidth;
//            double scaleY = (double) maxHeight / (double) initialHeight;
//            double scale = Math.min(scaleX, scaleY);
//            // se la scala � > di 1 allora non faccio il resize ma ritorno
//            // l'originale
//            if (scale >= 1) {
//                // copio l'originale in destinazione
//                FileUtils.copy(source, dest);
//                return true;
//            }
//            if (scaleX > scaleY) {
//                // allora devo ridurre di pi� sulla x
//                finalWidth = (int) ((double) initialWidth * scale);
//            } else {
//                finalHeight = (int) ((double) initialHeight * scale);
//            }
//            // eseguo il resize
//            Image image = img.image;
//            Image imgThumb = image.getScaledInstance(finalWidth, finalHeight, Image.SCALE_SMOOTH);
//            // la carico in modo da averla in memoria
//            CompletedImage imgThumbCI = it.load(imgThumb);
//
//            int[][] pixels = getPixels(imgThumbCI.image);
//
//            int[] palette = Quantize.quantizeImage(pixels, 256);
//
//            // ottengo l'immagine
//            Image finalImage = reconvertToImage(palette, pixels);
//
//            writeGifToFile(finalImage, dest);
//
//            return true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//
//    }
//
//    public static void writeGifToFile(Image image, File targetFile) throws FileNotFoundException, AWTException, IOException {
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
//        GIFEncoder ge = new GIFEncoder(image);
//        ge.Write(bos);
//        bos.close();
//    }
//
//    /**
//     * @param source
//     * @param dest
//     * @param scaleFactor
//     * @return
//     * @deprecated Utilizza metodi deprecati
//     */
//    public static boolean resizeGif(File source, File dest, double scaleFactor) {
//
//        try {
//            // carico l'immagine per determinare la dimensione
//            String filename = source.getPath();
//            ImageToolkit it = new ImageToolkit();
//            // attendo che sia terminato il caricamento
//            CompletedImage img = it.load(filename);
//
//            // determino le dimensioni per il resize
//            int initialHeight = img.height;
//            int initialWidth = img.width;
//            int finalHeight = (int) (initialHeight * scaleFactor);
//            int finalWidth = (int) (initialWidth * scaleFactor);
//
//            return resizeGif(source, dest, finalWidth, finalHeight);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * Insert the method's description here. Creation date: (12/04/2001 20.32.57)
//     */
//    public static boolean resizeJpg(File source, File dest, int maxWidth, int maxHeight, float quality) {
//        try {
//            BufferedImage loadImage = loadImage(source);
//            return resizeJpg(loadImage, dest, maxWidth, maxHeight, quality);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static boolean resizeJpg(BufferedImage sourceImg, File dest, int maxWidth, int maxHeight, float quality) {
//        BufferedImage thumbImage;
//        try {
//            Image imgThumb = scaleToSize(maxWidth, maxHeight, sourceImg);
//            int thumbWidth = imgThumb.getWidth(null);
//            int thumbHeight = imgThumb.getHeight(null);
//            thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
//            Graphics2D graphics2D = thumbImage.createGraphics();
//            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            graphics2D.drawImage(imgThumb, 0, 0, thumbWidth, thumbHeight, null);
//            writeJpgToFile(thumbImage, quality, dest);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }
//
//    public static void writeJpgToFile(BufferedImage image, float quality, File targetFile) throws IOException {
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(targetFile);
//            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
//            ImageWriteParam param = new JPEGImageWriteParam(null);
//            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//            param.setCompressionQuality(quality);
//            ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
//
//            writer.setOutput(ios);
//            IIOImage iioImage = new IIOImage(image, null, null);
//            writer.write(null, iioImage, param);
//
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static BufferedImage loadImage(File source) throws IOException, FileNotFoundException {
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        try {
//            fis = new FileInputStream(source);
//            bis = new BufferedInputStream(fis);
//            BufferedImage sourceImg = ImageIO.read(bis);
//            return sourceImg;
//        } finally {
//            fis.close();
//            bis.close();
//        }
//    }
//
//    public static boolean resizeJpg(File source, File dest, double scaleFactor, float quality) {
//        try {
//            BufferedImage sourceImg = loadImage(source);
//            return resizeJpg(sourceImg, dest, scaleFactor, quality);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static boolean resizeJpg(BufferedImage sourceImg, File dest, double scaleFactor, float quality) {
//        int width = 0;
//        int height = 0;
//        int finalWidth = 0;
//        int finalHeight = 0;
//        // determino la dimensione dell'immagine
//        try {
//
//            width = sourceImg.getWidth();
//            height = sourceImg.getHeight();
//            finalWidth = (int) (width * scaleFactor);
//            finalHeight = (int) (height * scaleFactor);
//
//            return resizeJpg(sourceImg, dest, finalWidth, finalHeight, quality);
//
//        } catch (Exception ex) {
//            return false;
//        }
//
//    }
//
//    /**
//     * Insert the method's description here. Creation date: (12/04/2001 20.32.57)
//     */
//    public static boolean resizeJpg(File source, File dest, int maxWidth, int maxHeight) {
//        return resizeJpg(source, dest, maxWidth, maxHeight, 1.0f);
//    }
//
//    private static Image scale(int newWidth, int newHeight, BufferedImage srcImg) {
//        // AffineTransformOp op =
//        // new AffineTransformOp(AffineTransform.getScaleInstance(scale, scale),
//        // AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//        // BufferedImage bi = op.filter(srcImg, null);
//        //
//        // return bi;
//        Image i = srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//        return i;
//    }
//
//    private static Raster scale(double scale, Raster srcImg) {
//        if (scale == 1) {
//            return srcImg;
//        }
//        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(scale, scale), null);
//        return op.filter(srcImg, null);
//    }
//
//    private static Image scaleToSize(int nMaxWidth, int nMaxHeight, BufferedImage imgSrc) {
//        int nHeight = imgSrc.getHeight();
//        int nWidth = imgSrc.getWidth();
//        log.debug("Original image dimension: W=" + nWidth + ", H=" + nHeight);
//        double scaleX = (double) nMaxWidth / (double) nWidth;
//        double scaleY = (double) nMaxHeight / (double) nHeight;
//        log.debug("Width scale=" + scaleX + ", height scale=" + scaleY);
//        double fScale = Math.min(scaleX, scaleY);
//        if (fScale == 1) {
//            log.debug("Minimum scale is equal to one -> return");
//            return imgSrc;
//        }
//        if (scaleX < scaleY) {
//            nMaxHeight = (int) (nHeight * scaleX);
//            log.debug("Width scale smaller than height one -> scale height with width scale: H=" + nMaxHeight);
//        } else {
//            nMaxWidth = (int) (nWidth * scaleY);
//            log.debug("Height scale smaller than width one -> scale width with height scale: W=" + nMaxWidth);
//        }
//        return scale(nMaxWidth, nMaxHeight, imgSrc);
//    }
//
//    private static Raster scaleToSize(int nMaxWidth, int nMaxHeight, Raster imgSrc) {
//        int nHeight = imgSrc.getHeight();
//        int nWidth = imgSrc.getWidth();
//        double scaleX = (double) nMaxWidth / (double) nWidth;
//        double scaleY = (double) nMaxHeight / (double) nHeight;
//        double fScale = Math.min(scaleX, scaleY);
//        return scale(fScale, imgSrc);
//    }
//
//    public static boolean resize(File source, File dest, double scaleFactor, float quality) {
//        // determina l'estensione del file sorgente e prova a decodificarla
//        // con il resizeJPG o il resize Gif
//        String extension = FileUtils.getFileExtension(source.getName());
//        boolean gifFirst = true;
//        if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
//            gifFirst = false;
//        }
//
//        // in entrambi i casi se fallisce la conversione tento l'altro decoder
//        // in quanto a volte su internet si trovano files denominati jpg che in
//        // realt�
//        // sono gif o viceversa
//        if (gifFirst) {
//
//            if (resizeGif(source, dest, scaleFactor)) {
//                return true;
//            } else {
//                return resizeJpg(source, dest, scaleFactor, quality);
//            }
//        } else {
//            if (resizeJpg(source, dest, scaleFactor, quality)) {
//                return true;
//            } else {
//                return resizeGif(source, dest, scaleFactor);
//            }
//
//        }
//
//    }
//
//    /**
//     * ruota l'immagine di 90 gradi in senso orario e la salva nel file di destinazione.
//     *
//     * @param imageFileSource
//     * @param imageFileTarget deve essere jpg jpeg o gif
//     * @throws IOException
//     * @throws AWTException
//     */
//    public static void rotate90Clockwise(File imageFileSource, File imageFileTarget) throws IOException, AWTException {
//        BufferedImage image = ImageIO.read(imageFileSource);
//
//        BufferedImage imageTgt = rotate90Clockwise(image);
//
//        Graphics2D g2D = imageTgt.createGraphics();
//        // AffineTransform at = AffineTransform.getRotateInstance(90.0 * Math.PI / 180.0);
//        AffineTransform at = new AffineTransform(0.0, 1.0, -1.0, 0.0, image.getHeight(), 0);
//        // g2D.transform(at);
//        g2D.drawImage(image, at, null);
//
//        // determina l'estensione del file sorgente e la slava di conseguenza
//        String extension = FileUtils.getFileExtension(imageFileTarget.getName());
//        if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
//            writeJpgToFile(imageTgt, 1.0F, imageFileTarget);
//            return;
//        }
//        if ("gif".equalsIgnoreCase(extension)) {
//            writeGifToFile(imageTgt, imageFileTarget);
//            return;
//        }
//        throw new UnsupportedOperationException("Impossibile salvare nel formato:" + extension + ". Formati supporati jpg, jpeg, gif");
//    }
//
//    /**
//     * ruota di 90 gradi una immagine in senso orario e ritorna una nuova immagine ruotata.
//     *
//     * @param image
//     * @return
//     * @throws IOException
//     */
//    public static BufferedImage rotate90Clockwise(BufferedImage image) throws IOException {
//        BufferedImage imageTgt = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
//
//        Graphics2D g2D = imageTgt.createGraphics();
//        // AffineTransform at = AffineTransform.getRotateInstance(90.0 * Math.PI / 180.0);
//        AffineTransform at = new AffineTransform(0.0, 1.0, -1.0, 0.0, image.getHeight(), 0);
//        // g2D.transform(at);
//        g2D.drawImage(image, at, null);
//        return imageTgt;
//    }
//
//}
//
