package javafundamentals.dto;


import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
@ToString
public class BoardDetailResponseDto {


    private String title;

    private String writer;

    private String content;

    private String createdDateTime;

    private List<String> filePath;

    private String categoryName;

    private BigInteger userId;

    private BigInteger boardCategoryId;

    private Integer view;

    private BigInteger boardId;

    private List<BoardFileDto> boardFiles;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardFileDto{
        private String id;
        private String filePath;
        private String originalName;
    }

}
