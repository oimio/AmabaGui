package ch.amaba.server.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {

	public static final String IMAGE_UPLOAD_DIRECTORY = "/data/dvt/eone/img/";

	public static void main(String args[]) {
		final File dir = new File("c:/out");
		final File[] files = dir.listFiles();
		for (final File file : files) {
			System.out.println(file);
			try {
				ImageUtils.resize(file, new File(file.getAbsolutePath() + "_resized.jpg"), 640, 0.9f);
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static final Object IN = new Object();

	public static void deleteThumbAndFullImage(String fileName) {
		final File full = new File(ImageUtils.IMAGE_UPLOAD_DIRECTORY + fileName + ".jpg");
		if (full.exists()) {
			full.delete();
		}
		final File thumb = new File(ImageUtils.IMAGE_UPLOAD_DIRECTORY + fileName + "_thumb.jpg");
		if (thumb.exists()) {
			thumb.delete();
		}
	}

	public static byte[] resize(byte[] originalBytes, int newWidth, float quality) throws IOException {
		byte[] resized = null;
		ByteArrayOutputStream out = null;
		synchronized (ImageUtils.IN) {
			if ((quality < 0) || (quality > 1)) {
				throw new IllegalArgumentException("Quality has to be between 0 and 1");
			}

			final ImageIcon ii = new ImageIcon(originalBytes);
			final Image i = ii.getImage();
			Image resizedImage = null;

			final int iWidth = i.getWidth(null);
			final int iHeight = i.getHeight(null);

			if (iWidth > iHeight) {
				resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
			} else {
				resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
			}

			// This code ensures that all the pixels in the image are loaded.
			final Image temp = new ImageIcon(resizedImage).getImage();

			// Create the buffered image.
			BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// Copy image to buffered image.
			final Graphics g = bufferedImage.createGraphics();

			// Clear background and paint the image.
			g.setColor(Color.white);
			g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
			g.drawImage(temp, 0, 0, null);
			g.dispose();

			// Soften.
			final float softenFactor = 0.05f;
			final float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
			final Kernel kernel = new Kernel(3, 3, softenArray);
			final ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			bufferedImage = cOp.filter(bufferedImage, null);

			// Write the jpeg to a file.

			try {
				out = new ByteArrayOutputStream();

				// Encodes image as a JPEG data stream
				final JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

				final JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);

				param.setQuality(quality, true);

				encoder.setJPEGEncodeParam(param);
				encoder.encode(bufferedImage);
			} finally {
				IOUtils.closeQuietly(out);
			}
		}
		resized = out.toByteArray();
		return resized;
	}

	public static void resize(File originalFile, File resizedFile, int newWidth, float quality) throws IOException {
		synchronized (ImageUtils.IN) {
			if ((quality < 0) || (quality > 1)) {
				throw new IllegalArgumentException("Quality has to be between 0 and 1");
			}

			final ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
			final Image i = ii.getImage();
			Image resizedImage = null;

			final int iWidth = i.getWidth(null);
			final int iHeight = i.getHeight(null);

			if (iWidth > iHeight) {
				resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
			} else {
				resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
			}

			// This code ensures that all the pixels in the image are loaded.
			final Image temp = new ImageIcon(resizedImage).getImage();

			// Create the buffered image.
			BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// Copy image to buffered image.
			final Graphics g = bufferedImage.createGraphics();

			// Clear background and paint the image.
			g.setColor(Color.white);
			g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
			g.drawImage(temp, 0, 0, null);
			g.dispose();

			// Soften.
			final float softenFactor = 0.05f;
			final float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
			final Kernel kernel = new Kernel(3, 3, softenArray);
			final ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			bufferedImage = cOp.filter(bufferedImage, null);

			// Write the jpeg to a file.
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(resizedFile);

				// Encodes image as a JPEG data stream
				final JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

				final JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);

				param.setQuality(quality, true);

				encoder.setJPEGEncodeParam(param);
				encoder.encode(bufferedImage);
			} finally {
				IOUtils.closeQuietly(out);
			}
		}
	}

}
