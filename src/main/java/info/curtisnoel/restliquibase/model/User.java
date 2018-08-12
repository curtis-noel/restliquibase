package info.curtisnoel.restliquibase.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Data
@Entity
@Table (name = "user", schema = "public")
public class User {
    @Id
    private String id;
    private String name;
}