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

public class MultiFileMain {
    public static void main(String[] args) throws IOException {
        Map<Object, Object> fileMap = new HashMap<>();
        String fileURL1 = "https://www.cashmerejournal.com/wp-content/uploads/2021/06/1-21.jpeg";
        String fileURL2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFe5uND85QkJXvQnzKxJ7vn_Pyq5HBMErH8w&usqp=CAU";
        String fileURL3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYvVHOMcmdXT9JTxHsdvO58lP5WEPhCtdVMQ&usqp=CAU";

        String boundary = Long.toHexString(System.currentTimeMillis());

        URL url1 = new URL(fileURL1);
        URL url2 = new URL(fileURL2);
        URL url3 = new URL(fileURL3);
        URL[] urls = {url1, url2, url3};
        Path[] paths = new Path[urls.length];

        for(int i = 0; i< urls.length; i++){
            File urlToFile = new File("히히히_" + i + ".jpg");
            FileUtils.copyURLToFile(urls[i], urlToFile);
            paths[i] = urlToFile.toPath();
        }

        fileMap.put("files", paths);
        fileMap.put("stringData", "진짜로");
        fileMap.put("intData", 8400);

        try{
            HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
            HttpResponse<String> response = client.send(
                    HttpRequest.newBuilder(new URI("http://localhost:9091/multiImagesTest"))
                            .timeout(Duration.ofSeconds(10))
                            .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                            .POST(multipartToByte2(fileMap, boundary)).build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonNode jsonNode = JsonMapper.builder().build().readTree(response.body());

            System.out.println("jsonNode= " + jsonNode);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static HttpRequest.BodyPublisher multipartToByte2(Map<Object, Object> map, String boundary) throws IOException {
        List<byte[]> byteArrays = new ArrayList<>();
        for (Map.Entry<Object, Object> data : map.entrySet()) {
            if (data.getValue() instanceof Path[]) {
                Path[] filePaths = (Path[]) data.getValue();
                for (Path path : filePaths) {
                    String mimeType = Files.probeContentType(path);
                    byte[] fileByte = Files.readAllBytes(path);

                    byteArrays.add(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));

                    String filename = path.getFileName().toString();
                    String partHeader = "Content-Disposition: form-data; name=\"" + data.getKey() +
                            "\"; filename=\"" + filename + "\"\r\n" +
                            "Content-Type: " + mimeType + "\r\n\r\n";

                    /** [partHeader]
                     * Content-Disposition: form-data; name="files"; filename="NNN_0.jpg"
                     * Content-Type: image/jpeg
                     *
                     */

                    // byteArray 안에 한 파일당 [헤더 + 파일데이터] 이렇게 따로따로 들어가야 함.
                    byteArrays.add(partHeader.getBytes(StandardCharsets.UTF_8));
                    byteArrays.add(fileByte);
                    byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
                }
            } else {
                byteArrays.add(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));

                String partHeader = "Content-Disposition: form-data; name=\"" + data.getKey() + "\"\r\n\r\n" +
                        data.getValue() + "\r\n";
                byteArrays.add(partHeader.getBytes(StandardCharsets.UTF_8));
            }
        }

        byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

}
