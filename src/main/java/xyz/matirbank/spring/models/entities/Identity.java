package xyz.matirbank.spring.models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import xyz.matirbank.spring.models.Enums.IdentityType;

@Entity
@Table(name = "identity")
public class Identity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
    long user_id;
    
    IdentityType type;
    String path;
    String url;
    Date date_created;
    Date date_updated;
    
    public Identity() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IdentityType getType() {
        return type;
    }

    public void setType(IdentityType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    
}