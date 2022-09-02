package com.svalero.toteco_app_aa2.domain.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.User;

import java.util.List;

public class UserWithPublication {
    @Embedded
    User user;

    @Relation(parentColumn = "id", entityColumn = "user_id")
    List<Publication> publications;

    public UserWithPublication(User user, List<Publication> publications) {
        this.user = user;
        this.publications = publications;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
