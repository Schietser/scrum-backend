package com.example.scrumtrial.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document("phones")
@Getter
@Setter
@NoArgsConstructor
public class PhoneEntity {


    @Id
    private String id;
    private String identifier;
    private String code;

    public PhoneEntity(String identifier, String code) {
        this.identifier = identifier;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneEntity)) return false;
        PhoneEntity that = (PhoneEntity) o;
        return Objects.equals(getId(), that.getId()) && getIdentifier().equals(that.getIdentifier()) && getCode().equals(that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdentifier(), getCode());
    }
}
