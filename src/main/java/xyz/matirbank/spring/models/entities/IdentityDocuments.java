package xyz.matirbank.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import xyz.matirbank.spring.models.Enums.IdentityType;

@Entity
@Table(name = "identity_document")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentityDocuments extends BaseEntity implements Serializable {
    
    long user_id;
    IdentityType type;
    String path;
    String url;
    
    public IdentityDocuments() {}

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

}