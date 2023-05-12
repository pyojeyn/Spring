package javafundamentals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import javafundamentals.dto.BoardDetailResponseDto;
import javafundamentals.dto.TestDto;
import javafundamentals.dto.UserInfoResponseDto;
import javafundamentals.utils.MergeJsonNodes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MergeJsonNodeMain {
    public static void main(String[] args) {

        Integer userId = 12;
        Integer boardId = 83;

        try{

            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1).build();

            HttpResponse<String> getUserResponse = client.send(
                    HttpRequest.newBuilder(new URI("http://localhost:9091/getUserInfo/" + userId))
                            .timeout(Duration.ofSeconds(10))
                            .GET().build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonNode userJsonNode = JsonMapper.builder().build().readTree(getUserResponse.body());
            System.out.println("userJsonNode = " + userJsonNode);

            UserInfoResponseDto userDto = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .build().treeToValue(userJsonNode, UserInfoResponseDto.class);

            System.out.println("Name = " + userDto.getName());

            HttpResponse<String> getBoardResponse = client.send(
                    HttpRequest.newBuilder(new URI("http://localhost:9091/getBoardInfo/"+ boardId))
                            .timeout(Duration.ofSeconds(10))
                            .GET().build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonNode boardJsonNode = JsonMapper.builder().build().readTree(getBoardResponse.body());
            System.out.println("boardJsonNode = " + boardJsonNode);

            BoardDetailResponseDto boardDto = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .build().treeToValue(boardJsonNode, BoardDetailResponseDto.class);

            System.out.println("title = " + boardDto.getTitle());
            JsonNode afterMerged =
                    MergeJsonNodes.merge(userJsonNode, boardJsonNode);


            TestDto dto = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .build().treeToValue(afterMerged, TestDto.class);

            System.out.println("dto.getEmailAddress = " + dto.getEmailAddress());

            System.out.println("최종 Node 입니다 = " +  afterMerged);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
