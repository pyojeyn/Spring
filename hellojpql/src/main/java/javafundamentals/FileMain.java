package javafundamentals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileMain {
    public static void main(String[] args) throws IOException {
        Map<Object, Object> fileMap = new HashMap<>();
        String fileURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDXOURi1HpRbGoThku1JANcxEyK9Y8MD6Uqw&usqp=CAU";

        String boundary = Long.toHexString(System.currentTimeMillis());
        System.out.println("boundary = " + boundary);

        URL url = new URL(fileURL);

        File urlToFile = new File("Billi.jpg");

        FileUtils.copyURLToFile(url, urlToFile); // 이거 안하면 FILE 에 내용물 없어서 NoSuchFileException 에러남.

        Path finalPath = urlToFile.toPath();
        fileMap.put("file", finalPath);
        System.out.println("file=" + finalPath);

        try{
            HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
            HttpResponse<String> response = client.send(
                    HttpRequest.newBuilder(new URI("http://localhost:9091/imageTest"))
                            .timeout(Duration.ofSeconds(10))
                            .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                            .POST(multipartToByte(fileMap, boundary)).build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonNode jsonNode = JsonMapper.builder().build().readTree(response.body());

            System.out.println("jsonNode = " + jsonNode);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static HttpRequest.BodyPublisher multipartToByte(Map<Object, Object> map, String boundary) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<byte[]> byteArrays = new ArrayList<>();
        for(Map.Entry<Object, Object> data : map.entrySet()){
            stringBuilder.setLength(0);
            stringBuilder.append("--").append(boundary).append("\r\n");

            if (data.getValue() instanceof Path){
                Path filePath = (Path) data.getValue();
                String mimeType = Files.probeContentType(filePath);
                byte[] fileByte = Files.readAllBytes(filePath);

                stringBuilder.append("Content-Disposition: form-data; name=").append("\"")
                        .append(data.getKey()).append("\"").append("; filename= ").append("\"")
                        .append(data.getValue()).append("\"").append("\r\n").append("Content-Type: ")
                        .append(mimeType).append("\r\n").append("\r\n");

                byteArrays.add(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                byteArrays.add(fileByte);
                byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));

                System.out.println("stringBuilder= " + stringBuilder);

            }else{
                stringBuilder.append("Content-Disposition: form-data; name=").append("\"")
                        .append(data.getKey()).append("\"").append(";").append("\r\n").append("\r\n")
                        .append(data.getValue()).append("\r\n");

                byteArrays.add(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            }
        }
        stringBuilder.setLength(0);
        stringBuilder.append("--").append(boundary).append("--");
        System.out.println("stringBuilder : " + stringBuilder);
        byteArrays.add(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));

        System.out.println("byteArrays : " + byteArrays);
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }


}
