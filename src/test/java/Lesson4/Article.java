package Lesson4;

public class Article
{
    private String title;
    private Integer commentCount;
    private String photoLink;
    private String link;
//hotkey alt+insert getter,setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setCommentCount(String commentCount) {
        commentCount = commentCount.replace("(","");
        commentCount = commentCount.replace(")","");
        this.commentCount = Integer.parseInt(commentCount);
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
