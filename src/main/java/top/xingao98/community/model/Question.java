package top.xingao98.community.model;

/**
 * Created by xinGao 2020/3/13
 */

public class Question {
    private Integer id;
    private String title;
    private String detail;
    private String tag;
    private Integer viewCount;
    private Integer readCount;
    private Integer commentCount;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer stars;
    private Long gmtCreate;
    private Long gmtModified;
    private String createrId;

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", tag='" + tag + '\'' +
                ", viewCount=" + viewCount +
                ", readCount=" + readCount +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", dislikeCount=" + dislikeCount +
                ", stars=" + stars +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", createrId='" + createrId + '\'' +
                '}';
    }
}
