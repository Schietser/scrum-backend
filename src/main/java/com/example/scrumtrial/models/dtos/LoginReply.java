package com.example.scrumtrial.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Objects;
//import lombok.experimental.Accessors;

//@Accessors(fluent = true)
@Getter
@Setter
@NoArgsConstructor
public class LoginReply {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String sessionToken;
    private Boolean success = false;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String error;

    // TODO: MAP ALL LOGINREPLY CREATIONS TO THIS CONSTRUCTOR
    // SUCCESS BEING NULL IS LOGICALLY PROBLEMATIC
    public LoginReply(boolean success){
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginReply)) return false;
        LoginReply that = (LoginReply) o;
        return Objects.equals(getSessionToken(), that.getSessionToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionToken());
    }
}
