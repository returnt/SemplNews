package returnt.models;

public class News {

    private String title = "News";
    private String description = "description";
    private String url = null;
    private String published = null;
    private String updated = null;
    private String author = null;
    private int hours = 0;
    private Boolean descVisible = false;


    public News(String _title, String _description, String _url, String _published, String _updated, String _author, int _hours, Boolean _descVisible) {
        title = _title;
        description = _description;
        url = _url;
        published = _published;
        updated = _updated;
        author = _author;
        hours = _hours;
        descVisible = _descVisible;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getUrl(){
        return url;
    }

    public String getPublished(){
        return published;
    }

    public String getUpdated(){
        return updated;
    }

    public String getAuthor(){
        return author;
    }

    public String getHours(){
        return hours + "";
    }

    public Boolean getDescVisible(){
        return descVisible;
    }
}