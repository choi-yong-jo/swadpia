package kr.co.swadpia.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.Tuple;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import jakarta.xml.bind.DatatypeConverter;
import kr.co.swadpia.common.constant.ResultCode;
import kr.co.swadpia.common.dto.ResponseDTO;
import kr.co.swadpia.member.dto.MemberDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class CommonUtilService {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtilService.class);


    @Getter
    private final String minioUrl;
    private final String bucket;

    private final String imgUrl = System.getenv("IMG_URL");
    private MinioClient minioClient;

    // 테스트
    public CommonUtilService() {

        String minioAddress = System.getenv("IMG_SERVER");
        this.minioUrl = System.getenv("IMG_URL");
        String minioKey = System.getenv("IMG_SERVER_KEY");
        String minioSecret = System.getenv("IMG_SERVER_SECRET");
        this.bucket = System.getenv("BUCKET");
        logger.info("IMG_SERVER : " + minioAddress);
        logger.info("IMG_URL : " + this.minioUrl);
        logger.info("IMG_SERVER_KEY : " + minioKey);
        logger.info("IMG_SERVER_SECRET : " + minioSecret);

        try {
            minioClient = new MinioClient(minioAddress, minioKey, minioSecret);
        } catch (InvalidEndpointException e) {
            log.error("InvalidEndpointException");
            log.error(e.getLocalizedMessage());

        } catch (InvalidPortException e) {
            log.error("InvalidPortException");
            log.error(e.getLocalizedMessage());
        }

    }

    /***
     * 객체를 json 으로 변환 하는 함수.
     * @param obj
     * @return
     */
    public String toJson(Object obj) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;

    }

    /***
     * Base64 image data 스트링을 inputStream 으로 변환
     * @param data - 이미지 파일 base64 인코딩 데이터
     * @return
     */
    public InputStream convertBase64ToStream(String data) {
        String base64Image = data.split(",")[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
        return new ByteArrayInputStream(imageBytes);
    }

    /***
     * Base64 image data 스트링을 BufferedImage 으로 변환
     * @param data - 이미지 f base64 인코딩 데이터
     * @return
     */
    public BufferedImage convertBase64ToBufferedImage(String data) throws IOException {
        String base64Image = data.split(",")[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);

        BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));

        return bufImg;
    }


    /***
     * Base64 데이터에서 확장자 추출
     * @param data - 이미지 파일 base64 인코딩 데이터
     * @return
     */
    public String getExtensionFromData(String data) {
        String extension = data.split(",")[0];
        String pattern = "(.+)(jpeg|jpg|png|gif)(.+)";

        return extension.replaceAll(pattern, "$2");
    }

    /***
     * Base64 데이터에서 컨텐츠타입 추출
     * @param data
     * @return
     */
    public String getContentsTypeFromData(String data) {
        String contentsType = data.split(",")[0];
        contentsType = contentsType.replace("data:", "");
        contentsType = contentsType.replace(";base64", "");
        return contentsType;
    }

    /***
     * 이미지 비율로 따져서 잘라내는 함수.
     * width , height 두 값의 비율로 중앙으로 크롭 시킨다.
     * @param src 소스 이미지
     * @param width 이후 리사이징 처리될 가로 길이
     * @param height 이후 리사이징 처리될 가로 길이
     * @return
     */
    public BufferedImage cropImage(BufferedImage src, int width, int height) {
//        logger.debug("cropImage : " + String.valueOf(width) + " , " + String.valueOf(height));
//        logger.debug("src.getWidth()   : " + String.valueOf( (float) src.getWidth() ));
//        logger.debug("src.getHeight()  : " + String.valueOf(  (float) src.getHeight()));
//        logger.debug("src.getWidth() / src.getHeight()  : " + String.valueOf( (float) src.getWidth() / (float) src.getHeight()));
//        logger.debug("width / height " + String.valueOf((float) ((width / height)*10000)));

        if (((float) src.getWidth() / (float) src.getHeight()) > ((float) width / (float) height)) {
            // 세로 길이  기준으로 가로 길이를 크롭

            int newWidth = (src.getHeight() * width) / height;
//            logger.debug("newWidth : " + String.valueOf(newWidth));
            int rectX = (src.getWidth() - newWidth) / 2;
//            logger.debug("rectX : " + String.valueOf(rectX));
            Rectangle rect = new Rectangle();
            rect.setBounds(rectX, 0, newWidth, src.getHeight());
            BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
            return dest;
        } else if (((float) src.getWidth() / (float) src.getHeight()) < ((float) width / (float) height)) {
            // 가로 길이 기준으로 세로 길이 크롭

            int newHeight = (src.getWidth() * height) / width;
            int rectY = (src.getHeight() - newHeight) / 2;
            Rectangle rect = new Rectangle();
            rect.setBounds(0, rectY, src.getWidth(), newHeight);
            BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
            return dest;

        } else {
            // 비율 동일. 크롭 취소.
            return src;
        }


    }

    /***
     * minio 로 업로드 함수.
     * @param data base64 인코딩 이미지 데이터
     * @param filename 저장할 파일이름
     * @param bucket 저장할 폴더(버킷)
     * @return
     */
    public Boolean uploadFileFromBase64(String data, String filename, String bucket, Boolean resize, int width,
        int height) {

        try {

            int pos = filename.lastIndexOf(".");
            String ext = filename.substring(pos + 1);

            String contentsType = data.split(",")[0];
            contentsType = contentsType.replace("data:", "");
            contentsType = contentsType.replace(";base64", "");
//            boolean isExist = minioClient.bucketExists(bucket);
//            if (isExist) {
//
//            } else {
//                minioClient.makeBucket(bucket);
//            }
//            minioClient.removeObject(bucket, filename);
            InputStream uploadImageStream;

            if (resize) {

                BufferedImage image = Thumbnails.of(cropImage(convertBase64ToBufferedImage(data), width, height))
                    .size(width, height).asBufferedImage();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, ext, os);
                uploadImageStream = new ByteArrayInputStream(os.toByteArray());
//                uploadImageStream = (InputStream) ImageIO.createImageInputStream(image);
            } else {
                uploadImageStream = convertBase64ToStream(data);
            }

            minioClient.putObject(bucket, filename, uploadImageStream, contentsType);
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }
        return true;

    }

    public String moveTempToEventFolder(String sourceData, String code) {
        //logger.debug("call moveTempToEventFolder");
        String result = "";
        try {

            final String regex = "(" + imgUrl + "temp/[A-Za-z0-9]+.(jpg|jpeg|png|gif))";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sourceData);
            int matchCount = 0;

            while (matcher.find()) {
                matchCount++;
                //logger.debug("TEMP URL ::::::::::::::: " + matcher.group());
                String imgSrc = matcher.group();
                String filename = imgSrc.replace(imgUrl + "temp/", "");

                boolean isExist = minioClient.bucketExists(code.toLowerCase());
                if (isExist) {

                } else {
                    minioClient.makeBucket(code.toLowerCase());
                }

                minioClient.copyObject("temp", filename, code.toLowerCase());
                minioClient.removeObject("temp", filename);


            }
            result = sourceData.replaceAll(imgUrl + "temp/", imgUrl + code.toLowerCase() + "/");
            //logger.debug("RESULT ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n" + result);

        } catch (Exception ex) {
            result = sourceData;
            // logger.debug("call moveTempToEventFolder error");
            logger.debug(ex.getLocalizedMessage());

        }

        return result;
    }

    public Boolean uploadFile(InputStream data, String contentsType, String path, String filename, String bucket,
        Boolean resize, int maxSize) {

        try {

            int pos = filename.lastIndexOf(".");
            String ext = filename.substring(pos + 1);

//            boolean isExist = minioClient.bucketExists(bucket);
//            if (isExist) {
//
//            } else {
//                minioClient.makeBucket(bucket);
//            }
//            minioClient.removeObject(this.bucket, bucket + "/" + path + "/" + filename);
            InputStream uploadImageStream;

            if (resize) {

                Image originalImage = ImageIO.read(data);
                int originWidth = originalImage.getWidth(null);
                int originHeight = originalImage.getHeight(null);
                if (originWidth > originHeight) {
                    int newWidth = maxSize;
                    if (originWidth > newWidth) {
                        int newHeight = (originHeight * newWidth) / originWidth;

                        Image resizeImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics graphics = newImage.getGraphics();
                        graphics.drawImage(resizeImage, 0, 0, null);
                        graphics.dispose();
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(newImage, ext, os);
                        uploadImageStream = new ByteArrayInputStream(os.toByteArray());


                    } else {
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write((RenderedImage) originalImage, ext, os);
                        uploadImageStream = new ByteArrayInputStream(os.toByteArray());

                    }
                } else if (originWidth < originHeight) {
                    int newHeight = maxSize;
                    if (originHeight > newHeight) {

                        int newWidth = (originWidth * newHeight) / originHeight;

                        Image resizeImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics graphics = newImage.getGraphics();
                        graphics.drawImage(resizeImage, 0, 0, null);
                        graphics.dispose();
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(newImage, ext, os);
                        uploadImageStream = new ByteArrayInputStream(os.toByteArray());


                    } else {

                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write((RenderedImage) originalImage, ext, os);
                        uploadImageStream = new ByteArrayInputStream(os.toByteArray());
                    }
                } else {
                    int newHeight = maxSize;
                    int newWidth = maxSize;
                    if (originHeight > newHeight) {

                        Image resizeImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics graphics = newImage.getGraphics();
                        graphics.drawImage(resizeImage, 0, 0, null);
                        graphics.dispose();
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(newImage, ext, os);
                        uploadImageStream = new ByteArrayInputStream(os.toByteArray());
                    } else {

                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write((RenderedImage) originalImage, ext, os);
                        uploadImageStream = new ByteArrayInputStream(os.toByteArray());
                    }

                }

            } else {
                uploadImageStream = data;
            }
            minioClient.putObject(this.bucket, bucket + "/" + path + "/" + filename, uploadImageStream, contentsType);
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }
        return true;

    }

    public Boolean uploadFileOriginal(InputStream data, String contentsType, String path, String filename,
        String bucket) {

        try {

            int pos = filename.lastIndexOf(".");
            String ext = filename.substring(pos + 1);

//            boolean isExist = minioClient.bucketExists(bucket);
//            if (isExist) {
//
//            } else {
//                minioClient.makeBucket(bucket);
//            }
            minioClient.removeObject(this.bucket, bucket + "/" + path + "/" + filename);
            InputStream uploadImageStream;
            uploadImageStream = data;

            minioClient
                .putObject(this.bucket, bucket + "/" + path + "/" + filename, uploadImageStream, contentsType);
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * (DockerSwarm)  클러스터 환경일때 도커 인스턴스의 index 를 가져운다
     *
     * @return
     */
    public Integer getServerIndex() {
        String serverIndex = System.getenv("SERVER_IDX");
        if (serverIndex == null) {
            return 1;
        } else {
            return Integer.valueOf(serverIndex);
        }
    }

    /***
     * 서버 hostname 가져오기
     * @return
     */
    public String getHostName() {
        String hostname = System.getenv("HOSTNAME");
        if (hostname != null) {
            return hostname;
        }

        String lineStr = "";
        try {
            Process process = Runtime.getRuntime().exec("hostname");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((lineStr = br.readLine()) != null) {
                hostname = lineStr;
            }
        } catch (IOException e) {
            e.printStackTrace();
            hostname = "";
        }
        return hostname;
    }

    public ResponseDTO selectObject(Object obj) {
        ResponseDTO dto = new ResponseDTO();
        if (obj == null) {
            dto.setResultCode(ResultCode.EMPTY.getName());
            dto.setMsg(ResultCode.EMPTY.getValue());
        } else {
            dto.setResultCode(ResultCode.SUCCESS.getName());
            dto.setMsg(ResultCode.SUCCESS.getValue());
            dto.setRes(obj);
        }

        return dto;
    }

    public ResponseDTO setMemberDetail(String[] column, java.util.List<Tuple> tuples) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (tuples.isEmpty()) {
            responseDTO.setResultCode(ResultCode.NOT_FOUND_INFO.getName());
            responseDTO.setMsg(ResultCode.NOT_FOUND_INFO.getValue());
        } else if (column.length != tuples.get(0).size()) {
            responseDTO.setResultCode(ResultCode.NOT_VALIDATED_COLUMN_COUNT.getName());
            responseDTO.setMsg(ResultCode.NOT_VALIDATED_COLUMN_COUNT.getValue());
        } else {
            String[] rows = tuples.toString().split("],");
            List<Map> list = new ArrayList<>(rows.length);
            for(int i=0; i< rows.length; i++) {
                Map<String, Object> map = new HashMap<>();
                String[] cols = rows[i]
                        .replace("[","")
                        .replace("]","")
                        .split(",");

                for (int j=0; j<cols.length; j++) {
                    map.put(column[j], cols[j]);
                }
                list.add(map);
            }
            responseDTO.setResultCode(ResultCode.SUCCESS.getName());
            responseDTO.setMsg(ResultCode.SUCCESS.getValue());
            responseDTO.setRes(list);
        }

        return responseDTO;
    }

}
