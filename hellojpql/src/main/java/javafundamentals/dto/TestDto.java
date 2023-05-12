package javafundamentals.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@ToString
public class TestDto {
    private BigInteger id;

    private String name;

    private String nickName;

    private String mobileNumber;

    private String roleName;

    private String createdDateTime;

    private String emailAddress;

    private String title;

    private String writer;

    private String content;

    private List<String> filePath;

    private String categoryName;

    private BigInteger userId;

    private BigInteger boardCategoryId;

    private Integer view;

    private BigInteger boardId;



}
