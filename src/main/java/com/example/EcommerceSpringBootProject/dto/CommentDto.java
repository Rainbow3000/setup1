package com.example.EcommerceSpringBootProject.dto;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotNull;
import java.sql.Date;

public class CommentDto extends BaseDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long travelId;
    private Date commentDate;

    private String userCommentName;
    @NotNull
    @Length(max = 100)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getUserCommentName() {
        return userCommentName;
    }

    public void setUserCommentName(String userCommentName) {
        this.userCommentName = userCommentName;
    }
}
